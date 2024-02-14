package skripsi.magfira.ambulanceapp.features.order.presentation.screens.customer

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import com.pusher.client.connection.ConnectionEventListener
import com.pusher.client.connection.ConnectionState
import com.pusher.client.connection.ConnectionStateChange
import kotlinx.coroutines.flow.first
import skripsi.magfira.ambulanceapp.datastore.DataStorePreferences
import skripsi.magfira.ambulanceapp.features.common.presentation.components.AppBarHome
import skripsi.magfira.ambulanceapp.features.common.presentation.components.LoadingDialog
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.AllBooking
import skripsi.magfira.ambulanceapp.features.order.presentation.components.MapViewCustomer
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.DriversData
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.OrderBooking
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.pusher.BookingEventData
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.pusher.OrderBookingEvent
import skripsi.magfira.ambulanceapp.features.order.presentation.components.CardDetailOrderCustomer
import skripsi.magfira.ambulanceapp.features.order.presentation.components.CardEditLocationCustomer
import skripsi.magfira.ambulanceapp.features.order.presentation.components.CardMainCustomer
import skripsi.magfira.ambulanceapp.features.order.presentation.components.CardOrderingCustomer
import skripsi.magfira.ambulanceapp.features.order.presentation.view_models.OrderViewModel
import skripsi.magfira.ambulanceapp.navigation.ScreenRouter
import skripsi.magfira.ambulanceapp.util.MessageUtils.MSG_DO_NOT_HAS_LOCATION_PERMISSION
import skripsi.magfira.ambulanceapp.util.MessageUtils.MSG_NONE_AMBULANCE_ACTIVE
import skripsi.magfira.ambulanceapp.util.MessageUtils.MSG_UNAUTHORIZED
import skripsi.magfira.ambulanceapp.util.NetworkUtils
import skripsi.magfira.ambulanceapp.util.OpenChat
import skripsi.magfira.ambulanceapp.util.locationUpdate
import skripsi.magfira.ambulanceapp.util.parseLatLngFromString
import skripsi.magfira.ambulanceapp.util.requestAllPermissions
import skripsi.magfira.ambulanceapp.util.stopLocationUpdate
import javax.inject.Inject

class HomeCustomerScreen(
    private val viewModel: OrderViewModel,
    private val navController: NavHostController?
) {
    private val TAG = "HomeCustomerScreen"
    private val ORDERING_FLOW = listOf("Main", "Change Location", "Detail", "Ordering")
    private val ORDERING_STATUS = listOf("dalam proses", "diterima")

    private var isLocationServiceInitialized = false

    @Inject
    lateinit var dataStorePreferences: DataStorePreferences

    @Inject
    lateinit var pusher: Pusher
    private var currentBookingId = ""

    @Composable
    fun MainScreen() {
        val context = LocalContext.current

        dataStorePreferences = DataStorePreferences(context)

        var orderingStack by remember { mutableStateOf(listOf(ORDERING_FLOW[0])) }
        var driversOn by remember { mutableStateOf(listOf<DriversData>()) }
        var getAllBooking by remember { mutableStateOf<AllBooking?>(null) }

        var isOrderAccepted by remember { mutableStateOf(false) }
        var myUserId by remember { mutableStateOf("") }

        // Check if in ordering
        val filteredData =
            getAllBooking?.data?.data?.filter { it.customer_id.toString() == myUserId }

        if (requestAllPermissions(context = context)) {
            if (!isLocationServiceInitialized) {
                viewModel.InitializeLocation(context = context)
                locationUpdate()

                isLocationServiceInitialized = true
            } else {
                stopLocationUpdate()
            }
        } else {
            // Not granted
            Toast.makeText(context, MSG_DO_NOT_HAS_LOCATION_PERMISSION, Toast.LENGTH_SHORT).show()
        }

        LaunchedEffect(context) {
            connectPusher() // init pusher
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            MapViewCustomer(
                viewModel = viewModel,
                driversOnData = driversOn,
                bookingData = filteredData,
                context = context
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
            ) {
                AppBarHome(
                    iconSettingClick = {
                        navController?.navigate(ScreenRouter.CustomerProfile.route)
                    }
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(Color.Transparent),
                )

                if (filteredData?.isNotEmpty() == true) {

                    parseLatLngFromString(filteredData.get(0).lokasi_jemput)?.let {
                        viewModel.updateMyLocation(it)
                        stopLocationUpdate()
                    }
                    LaunchedEffect(filteredData) {
                        orderingStack = orderingStack + ORDERING_FLOW[3]
                    }
                }

                Log.d(TAG, "Ordering Stack: $orderingStack")
                when (orderingStack.lastOrNull()) {
                    ORDERING_FLOW[0] -> {
                        if (requestAllPermissions(context = context)) {
                            viewModel.editableLocation(false)
                        } else {
                            // Not granted
                            Toast.makeText(
                                context,
                                MSG_DO_NOT_HAS_LOCATION_PERMISSION,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        CardMainCustomer(
                            driversOnData = driversOn,
                            toEditLocation = {
                                orderingStack = orderingStack + ORDERING_FLOW[1]
                            },
                            toOrderDetail = {
                                if (driversOn.isNotEmpty()) {
                                    orderingStack = orderingStack + ORDERING_FLOW[2]
                                } else {
                                    Toast.makeText(
                                        context,
                                        MSG_NONE_AMBULANCE_ACTIVE,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        )
                    }

                    ORDERING_FLOW[1] -> {
                        if (requestAllPermissions(context = context)) {
                            viewModel.editableLocation(true)
                        } else {
                            // Not granted
                            Toast.makeText(
                                context,
                                MSG_DO_NOT_HAS_LOCATION_PERMISSION,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        CardEditLocationCustomer(
                            iconBackClick = {
                                locationUpdate() // Update location
                                orderingStack = orderingStack.dropLast(1) + ORDERING_FLOW[0]
                            },
                            iconSaveLocation = {
                                stopLocationUpdate() // Stop location
                                orderingStack = orderingStack.dropLast(1) + ORDERING_FLOW[0]
                            },
                        )
                    }

                    ORDERING_FLOW[2] -> {
                        if (requestAllPermissions(context = context)) {
                            viewModel.editableLocation(false)
                        } else {
                            // Not granted
                            Toast.makeText(
                                context,
                                MSG_DO_NOT_HAS_LOCATION_PERMISSION,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        CardDetailOrderCustomer(
                            viewModel = viewModel,
                            dataStorePreferences = dataStorePreferences,
                            context = context,
                            driversOn = driversOn,
                            iconBackClick = {
                                orderingStack = orderingStack.dropLast(1) + ORDERING_FLOW[0]
                            },
                            toOrdering = {
                                orderingStack = orderingStack + ORDERING_FLOW[3]
                            }
                        )
                    }

                    ORDERING_FLOW[3] -> {
                        if (requestAllPermissions(context = context)) {
                            viewModel.editableLocation(false)
                        } else {
                            // Not granted
                            Toast.makeText(
                                context,
                                MSG_DO_NOT_HAS_LOCATION_PERMISSION,
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        filteredData?.let {
                            if (it.isNotEmpty()) {
                                CardOrderingCustomer(
                                    viewModel,
                                    context,
                                    it[0],
                                    toMainOrder = {
                                        viewModel.cancelBooking(filteredData.get(0).id.toString())
                                        orderingStack = listOf(ORDERING_FLOW[0])
                                    },
                                    toChat = {
                                        OpenChat.openWhatsAppChat(context, filteredData.get(0).driver.no_telp, "")
                                    }
                                )
                            }
                        }
                    }

                    else -> {
                        // Default case
                    }
                }
            }
        }

        // Check Login
        LaunchedEffect(true) {
            val isLogin = dataStorePreferences.getUserIsLogin.first()
            val token = dataStorePreferences.getUserToken.first()

            if (isLogin == true) {
                viewModel.token = token ?: ""
                myUserId = dataStorePreferences.getUserId.first()!!

            } else {
                // Not Login
                Toast.makeText(context, MSG_UNAUTHORIZED, Toast.LENGTH_SHORT).show()

                // Unauthorized
                navController?.navigate(ScreenRouter.Auth.route) {
                    popUpTo(ScreenRouter.Customer.route) {
                        inclusive = false
                    }
                }
            }
        }

        // Get all booking on
        LaunchedEffect(true) {
            viewModel.getAllBooking()
        }

        // Get driveres on
        LaunchedEffect(driversOn) {
            viewModel.driversOn()
        }

        // Observe ViewModel
        ViewModelObserver(
            viewModel,
            onDriversUpdated = { updatedDrivers ->
                driversOn = updatedDrivers
            },
            onBookingUpdate = { updatedBooking ->
                getAllBooking = updatedBooking
            },
            context,
        )
    }

    fun connectPusher() {
        // Initialize Pusher
        val pusherOptions = PusherOptions().setCluster(NetworkUtils.APP_CLUSTER)
        pusher = Pusher(NetworkUtils.APP_KEY, pusherOptions)

        pusher.connect(object : ConnectionEventListener {
            override fun onConnectionStateChange(change: ConnectionStateChange) {
                Log.d(
                    TAG,
                    "Pusher: State changed from ${change.previousState} to ${change.currentState}"
                )
            }

            override fun onError(
                message: String,
                code: String?,
                e: Exception
            ) {
                if (code != null) {
                    Log.d(
                        TAG,
                        "Pusher: There was a problem connecting! code ($code), message ($message), exception($e)"
                    )
                } else {
                    Log.d(
                        TAG,
                        "Pusher: There was a problem connecting! code is null, message ($message), exception($e)"
                    )
                }
            }
        }, ConnectionState.ALL)

        val channelBookingCreated = pusher.subscribe("boking-created")
        val channelBookingUpdated = pusher.subscribe("boking-status-updated${currentBookingId}")
        val channelMessage = pusher.subscribe("chat-room")
        val channelDriverLocation = pusher.subscribe("location-driver")

        channelBookingCreated.bind("App\\Events\\BokingCreated") { event ->
            Log.d(TAG, "Pusher: BokingStatusUpdated: Received event with data: ${event}")
            try {
                val gson = Gson()
                val bookingEvent = gson.fromJson(event.toString(), OrderBookingEvent::class.java)
                val bookingEventData = gson.fromJson(bookingEvent.data, BookingEventData::class.java)

                Log.d(TAG, "Pusher: BokingStatusUpdated: After make it in model: ${bookingEventData}")

            } catch (e: Exception) {
                Log.d(TAG, "Pusher: BokingCreated: Error: ${e.localizedMessage}")
            }

        }
        channelBookingUpdated.bind("App\\Events\\BokingStatusUpdated") { event ->
            Log.d(TAG, "Pusher: BokingStatusUpdated: Received event with data: ${event}")
            try {
                val gson = Gson()
                val bookingEvent = gson.fromJson(event.toString(), OrderBookingEvent::class.java)
                val bookingEventData = gson.fromJson(bookingEvent.data, BookingEventData::class.java)

                Log.d(TAG, "Pusher: BokingStatusUpdated: After make it in model: ${bookingEventData}")

                if (currentBookingId == bookingEventData.booking.id.toString()) {
                    // Get all booking on
                    viewModel.getAllBooking()
                }

            } catch (e: Exception) {
                Log.d(TAG, "Pusher: BokingStatusUpdated: Error: ${e.localizedMessage}")
            }
        }
        channelMessage.bind("App\\Events\\MessageSent") { event ->
            Log.d(TAG, "Pusher: MessageSent: Received event with data: $event")
        }
        channelDriverLocation.bind("App\\Events\\LokasiDriver") { event ->
            Log.d(TAG, "Pusher:DriverLocation: Received event with data: $event")
        }

    }

    @Composable
    fun ViewModelObserver(
        viewModel: OrderViewModel,
        onDriversUpdated: (List<DriversData>) -> Unit,
        onBookingUpdate: (AllBooking) -> Unit,
        context: Context,
    ) {
        val allBookingState = viewModel.stateAllBooking
        val driversOnState = viewModel.stateDriversOn
        val cancelBookingState = viewModel.stateCancelBooking
        val orderBookingState = viewModel.stateOrderBooking

        when {
            allBookingState.isLoading -> {

                LoadingDialog(onDismissRequest = {
                    // Handle dismiss action if needed
                })

            }

            allBookingState.data != null -> {
                val bookingData = allBookingState.data
                onBookingUpdate(bookingData!!)

                LaunchedEffect(bookingData) {
                    // Set bookingId
                    if (bookingData.data.data.isNotEmpty()) {
                        currentBookingId = bookingData.data.data.get(0).id.toString()
                    }
                    connectPusher() // Init pusher
                }

            }

            allBookingState.error.isNotEmpty() -> {
                val errorMessage = allBookingState.error
                Log.d(TAG, "ViewModelObserver: $errorMessage")
            }

            else -> {
                // Initial state or other cases
            }
        }

        when {
            driversOnState.isLoading -> {

                LoadingDialog(onDismissRequest = {
                    // Handle dismiss action if needed
                })

            }

            driversOnState.data != null -> {
                val driversData = driversOnState.data
                onDriversUpdated(driversData!!.data)
            }

            driversOnState.error.isNotEmpty() -> {
                val errorMessage = driversOnState.error
                Log.d(TAG, "ViewModelObserver: $errorMessage")
            }

            else -> {
                // Initial state or other cases
            }
        }

        when {
            orderBookingState.isLoading -> {

                LoadingDialog(onDismissRequest = {
                    // Handle dismiss action if needed
                })

            }

            orderBookingState.data != null -> {
                val orderBookingData = orderBookingState.data

                LaunchedEffect(orderBookingData) {
                    viewModel.getAllBooking()

                    // Set pusher
                    currentBookingId = orderBookingData?.data?.id.toString()
                    connectPusher() // Init pusher

                }
            }

            orderBookingState.error.isNotEmpty() -> {
                val errorMessage = orderBookingState.error
                Log.d(TAG, "ViewModelObserver: $errorMessage")
            }

            else -> {
                // Initial state or other cases
            }
        }

        when {
            cancelBookingState.isLoading -> {}

            cancelBookingState.data != null -> {
                val cancelBookingData = cancelBookingState.data

                LaunchedEffect(cancelBookingData) {
                    viewModel.getAllBooking() // Get new data
                }

            }

            cancelBookingState.error.isNotEmpty() -> {
                val errorMessage = cancelBookingState.error
                Log.d(TAG, "ViewModelObserver: $errorMessage")
            }

            else -> {
                // Initial state or other cases
            }
        }

    }

}

//@Preview(showBackground = true)
//@Composable
//fun HomeCustomerScreenPreview() {
//    HomeCustomerScreen(null, null).MainScreen()
//}
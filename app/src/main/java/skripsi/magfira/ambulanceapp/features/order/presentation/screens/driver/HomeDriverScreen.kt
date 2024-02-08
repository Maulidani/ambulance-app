package skripsi.magfira.ambulanceapp.features.order.presentation.screens.driver

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import com.pusher.client.connection.ConnectionEventListener
import com.pusher.client.connection.ConnectionState
import com.pusher.client.connection.ConnectionStateChange
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import skripsi.magfira.ambulanceapp.datastore.DataStorePreferences
import skripsi.magfira.ambulanceapp.features.common.presentation.components.AppBarHome
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.AllBooking
import skripsi.magfira.ambulanceapp.features.order.presentation.components.CardActivationDriver
import skripsi.magfira.ambulanceapp.features.order.presentation.components.CardOrderingDriver
import skripsi.magfira.ambulanceapp.features.order.presentation.components.MapViewDefault
import skripsi.magfira.ambulanceapp.features.order.presentation.components.MapViewDriver
import skripsi.magfira.ambulanceapp.features.order.presentation.view_models.OrderViewModel
import skripsi.magfira.ambulanceapp.navigation.ScreenRouter
import skripsi.magfira.ambulanceapp.util.MessageUtils
import skripsi.magfira.ambulanceapp.util.NetworkUtils
import skripsi.magfira.ambulanceapp.util.locationUpdate
import skripsi.magfira.ambulanceapp.util.requestAllPermissions
import javax.inject.Inject

class HomeDriverScreen(
    private val viewModel: OrderViewModel?,
    private val navController: NavHostController?
) {
    private val TAG = "HomeDriverScreen"
    private val ORDERING_FLOW = listOf("Main", "dalam proses", "diterima")

    private var isLocationServiceInitialized = false

    @Inject
    lateinit var dataStorePreferences: DataStorePreferences

    @Inject
    lateinit var pusher: Pusher

    @Composable
    fun MainScreen() {
        val context = LocalContext.current

        dataStorePreferences = DataStorePreferences(context)

        val scope = rememberCoroutineScope()
        var cardStatusAccountVisible by remember { mutableStateOf(true) }
        var getAllBooking by remember { mutableStateOf<AllBooking?>(null) }
        var myUserId by remember { mutableStateOf("") }

        if (requestAllPermissions(context = context)) {
            if (!isLocationServiceInitialized) {
                viewModel?.InitializeLocation(context = context)
                locationUpdate()

                isLocationServiceInitialized = true
            }
        } else {
            // Not granted
            Toast.makeText(
                context,
                MessageUtils.MSG_DO_NOT_HAS_LOCATION_PERMISSION,
                Toast.LENGTH_SHORT
            ).show()
        }

        LaunchedEffect(context) {
            connectPusher() // Init pusher
        }

        if (viewModel != null) {
            // Check Login
            LaunchedEffect(true) {
                val isLogin = dataStorePreferences.getUserIsLogin.first()
                val token = dataStorePreferences.getUserToken.first()

                if (isLogin == true) {
                    viewModel.token = token ?: ""
                    myUserId = dataStorePreferences.getUserId.first()!!

                } else {
                    // Not Login
                    Toast.makeText(context, MessageUtils.MSG_UNAUTHORIZED, Toast.LENGTH_SHORT)
                        .show()

                    // Unauthorized
                    navController?.navigate(ScreenRouter.Auth.route) {
                        popUpTo(ScreenRouter.Driver.route) {
                            inclusive = false
                        }
                    }
                }
            }

            // Get all booking on
            LaunchedEffect(true) {
                viewModel.getAllBooking()
            }

            // Observe ViewModel
            ViewModelObserver(
                viewModel,
                onBookingUpdate = { updatedBooking ->
                    getAllBooking = updatedBooking
                },
                context,
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (viewModel != null) {
                if (getAllBooking != null) {
                    MapViewDriver(
                        viewModel,
                        getAllBooking!!,
                        myUserId,
                        context
                    )
                } else {
                    MapViewDefault(viewModel, context)
                }
            } else {
                // Loading
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
            ) {
                AppBarHome(
                    iconSettingClick = {
                        navController?.navigate(ScreenRouter.DriverProfile.route)
                    }
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(Color.Transparent),
                )

                LaunchedEffect(viewModel) {
                    // Start a coroutine
                    scope.launch {
                        // Delay for 2 seconds
                        delay(3000)
                        // After 2 seconds, set the card visibility to false
                        cardStatusAccountVisible = false
                    }
                }

                // Only show the card if it's visible
                if (cardStatusAccountVisible) {
                    CardActivationDriver()
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Check if in ordering
                val filteredData =
                    getAllBooking?.data?.data?.filter { it.driver_id.toString() == myUserId }
                if (filteredData?.isNotEmpty() == true) {
                    locationUpdate()

                    CardOrderingDriver(
                        viewModel,
                        filteredData.get(0),
                        context
                    )
                }
            }
        }

    }

    private fun connectPusher() {
        // Initialize Pusher
        val pusherOptions = PusherOptions().setCluster(NetworkUtils.APP_CLUSTER)
        val pusher = Pusher(NetworkUtils.APP_KEY, pusherOptions)

        pusher.connect(object : ConnectionEventListener {
            override fun onConnectionStateChange(change: ConnectionStateChange) {
                Log.d(
                    TAG,
                    "Pusher: State changed from ${change.previousState} to ${change.currentState}"
                )
            }

            override fun onError(
                message: String,
                code: String,
                e: Exception
            ) {
                Log.d(
                    TAG,
                    "Pusher: There was a problem connecting! code ($code), message ($message), exception($e)"
                )
            }
        }, ConnectionState.ALL)

        val channel = pusher.subscribe("my-channel")
        channel.bind("my-event") { event ->
            Log.d(TAG, "Pusher: Received event with data: $event")
        }
    }

    @Composable
    fun ViewModelObserver(
        viewModel: OrderViewModel,
        onBookingUpdate: (AllBooking) -> Unit,
        context: Context,
    ) {
        val allBookingState = viewModel.stateAllBooking
        val acceptBookingState = viewModel.stateAcceptBooking
        val cancelBookingState = viewModel.stateCancelBooking

        when {
            allBookingState.isLoading -> {}

            allBookingState.data != null -> {
                val driversData = allBookingState.data
                onBookingUpdate(driversData!!)

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
            acceptBookingState.isLoading -> {}

            acceptBookingState.data != null -> {
                val acceptBookingData = acceptBookingState.data

                LaunchedEffect(acceptBookingData) {
                    viewModel.getAllBooking() // Get new data
                }

            }

            acceptBookingState.error.isNotEmpty() -> {
                val errorMessage = acceptBookingState.error
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

@Preview(showBackground = true)
@Composable
fun HomeDriverScreenPreview() {
    HomeDriverScreen(null, null).MainScreen()
}
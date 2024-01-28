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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.first
import skripsi.magfira.ambulanceapp.datastore.DataStorePreferences
import skripsi.magfira.ambulanceapp.features.common.presentation.components.AppBarHome
import skripsi.magfira.ambulanceapp.features.common.presentation.components.MapView
import skripsi.magfira.ambulanceapp.features.order.domain.model.DriversOnData
import skripsi.magfira.ambulanceapp.features.order.presentation.components.CardDetailOrderCustomer
import skripsi.magfira.ambulanceapp.features.order.presentation.components.CardEditLocationCustomer
import skripsi.magfira.ambulanceapp.features.order.presentation.components.CardMainCustomer
import skripsi.magfira.ambulanceapp.features.order.presentation.components.CardOrderingCustomer
import skripsi.magfira.ambulanceapp.features.order.presentation.view_models.OrderViewModel
import skripsi.magfira.ambulanceapp.navigation.ScreenRouter
import skripsi.magfira.ambulanceapp.util.MessageUtils.MSG_DO_NOT_HAS_LOCATION_PERMISSION
import skripsi.magfira.ambulanceapp.util.MessageUtils.MSG_NONE_AMBULANCE_ACTIVE
import skripsi.magfira.ambulanceapp.util.MessageUtils.MSG_UNAUTHORIZED
import skripsi.magfira.ambulanceapp.util.MessageUtils.MSG_WAITING_DRIVER_AMBULANCE
import skripsi.magfira.ambulanceapp.util.locationUpdate
import skripsi.magfira.ambulanceapp.util.requestAllPermissions
import skripsi.magfira.ambulanceapp.util.stopLocationUpdate
import javax.inject.Inject

class HomeCustomerScreen(
    private val viewModel: OrderViewModel,
    private val navController: NavHostController?
) {
    private val TAG = "HomeCustomerScreen"
    private val ORDERING_FLOW = listOf("Main", "Change Location", "Detail", "Ordering")

    @Inject
    lateinit var dataStorePreferences: DataStorePreferences

    @Composable
    fun MainScreen() {
        val context = LocalContext.current

        dataStorePreferences = DataStorePreferences(context)

        if (requestAllPermissions(context = context)) {
            viewModel.initializeLocation(context = context)
        } else {
            // Not granted
            Toast.makeText(context, MSG_DO_NOT_HAS_LOCATION_PERMISSION, Toast.LENGTH_SHORT).show()
        }

        var orderingStack by rememberSaveable { mutableStateOf(listOf(ORDERING_FLOW[0])) }
        var driversOn by remember { mutableStateOf(listOf<DriversOnData>()) }
        var isOrderAccepted by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            MapView(
                viewModel = viewModel,
                driversOnData = driversOn,
                isOrderAccepted = isOrderAccepted,
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
                Log.d(TAG, "Ordering Stack: $orderingStack")
                when (orderingStack.lastOrNull()) {
                    ORDERING_FLOW[0] -> {
                        if (requestAllPermissions(context = context)) {
                            locationUpdate()
                            viewModel.editableLocation(false)
                        } else {
                            // Not granted
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
                            stopLocationUpdate()
                            viewModel.editableLocation(true)
                        } else {
                            // Not granted
                            Toast.makeText(context, MSG_DO_NOT_HAS_LOCATION_PERMISSION, Toast.LENGTH_SHORT).show()
                        }
                        CardEditLocationCustomer(
                            iconBackClick = {
                                orderingStack = orderingStack.dropLast(1) + ORDERING_FLOW[0]
                            },
                        )
                    }

                    ORDERING_FLOW[2] -> {
                        if (requestAllPermissions(context = context)) {
                            stopLocationUpdate()
                            viewModel.editableLocation(false)
                        } else {
                            // Not granted
                            Toast.makeText(context, MSG_DO_NOT_HAS_LOCATION_PERMISSION, Toast.LENGTH_SHORT).show()
                        }
                        CardDetailOrderCustomer(
                            viewModel = viewModel,
                            dataStorePreferences = dataStorePreferences,
                            context = context,
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
                            stopLocationUpdate()
                            viewModel.editableLocation(false)
                        } else {
                            // Not granted
                            Toast.makeText(context, MSG_DO_NOT_HAS_LOCATION_PERMISSION, Toast.LENGTH_SHORT).show()
                        }

                        CardOrderingCustomer(
                            viewModel,
                            context,
                            isOrderAccepted,
                            toMainOrder = {
                                orderingStack = listOf(ORDERING_FLOW[0])
                            }
                        )
                    }

                    else -> {
                        // Default case
                    }
                }
            }
        }

        // Check Login
        LaunchedEffect(true) {
            val isLogin = dataStorePreferences.getIsLogin.first()
            val token = dataStorePreferences.getToken.first()

            if (isLogin == true) {
                viewModel.token = token ?: ""

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
            context,
        )
    }

    @Composable
    fun ViewModelObserver(
        viewModel: OrderViewModel,
        onDriversUpdated: (List<DriversOnData>) -> Unit,
        context: Context,
    ) {
        val driversOnState = viewModel.stateDriversOn

        when {
            driversOnState.isLoading -> {}

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

    }
}

//@Preview(showBackground = true)
//@Composable
//fun HomeCustomerScreenPreview() {
//    HomeCustomerScreen(null, null).MainScreen()
//}
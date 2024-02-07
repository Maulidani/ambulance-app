package skripsi.magfira.ambulanceapp.features.order.presentation.screens.yayasan

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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.first
import skripsi.magfira.ambulanceapp.datastore.DataStorePreferences
import skripsi.magfira.ambulanceapp.features.common.presentation.components.AppBarHome
import skripsi.magfira.ambulanceapp.features.order.presentation.components.MapViewYayasan
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.DriversData
import skripsi.magfira.ambulanceapp.features.order.presentation.components.CardMainYayasan
import skripsi.magfira.ambulanceapp.features.order.presentation.view_models.OrderViewModel
import skripsi.magfira.ambulanceapp.navigation.ScreenRouter
import skripsi.magfira.ambulanceapp.util.MessageUtils
import skripsi.magfira.ambulanceapp.util.locationUpdate
import skripsi.magfira.ambulanceapp.util.requestAllPermissions
import skripsi.magfira.ambulanceapp.util.stopLocationUpdate
import javax.inject.Inject

class HomeYayasanScreen(
    private val viewModel: OrderViewModel?,
    private val navController: NavHostController?
) {
    private val TAG = "HomeYayasanScreen"
    private var isLocationServiceInitialized = false

    @Inject
    lateinit var dataStorePreferences: DataStorePreferences

    @Composable
    fun MainScreen() {

        val context = LocalContext.current

        dataStorePreferences = DataStorePreferences(context)

        if (requestAllPermissions(context = context)) {
            if (!isLocationServiceInitialized) {
                viewModel?.InitializeLocation(context = context)
                locationUpdate()

                isLocationServiceInitialized = true
            } else {
                stopLocationUpdate()
            }
        } else {
            // Not granted
            Toast.makeText(context, MessageUtils.MSG_DO_NOT_HAS_LOCATION_PERMISSION, Toast.LENGTH_SHORT).show()
        }

        var driversOn by remember { mutableStateOf(listOf<DriversData>()) }

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (viewModel != null) {
                MapViewYayasan(
                    viewModel = viewModel,
                    driversOnData = driversOn,
                    context = context
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
            ) {
                AppBarHome(
                    iconSettingClick = {
                        navController?.navigate(ScreenRouter.YayasanProfile.route)
                    }
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(Color.Transparent),
                )
                CardMainYayasan(
                    driversOnData = driversOn,
                )
            }
        }

        if (viewModel != null) {
            // Check Login
            LaunchedEffect(true) {
                val isLogin = dataStorePreferences.getUserIsLogin.first()
                val token = dataStorePreferences.getUserToken.first()

                if (isLogin == true) {
                    viewModel.token = token ?: ""

                } else {
                    // Not Login
                    Toast.makeText(context, MessageUtils.MSG_UNAUTHORIZED, Toast.LENGTH_SHORT).show()

                    // Unauthorized
                    navController?.navigate(ScreenRouter.Auth.route) {
                        popUpTo(ScreenRouter.Yayasan.route) {
                            inclusive = false
                        }
                    }
                }
            }

            // Get driveres on
            LaunchedEffect(driversOn) {
                viewModel.driversYayasanOn()
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
    }

    @Composable
    fun ViewModelObserver(
        viewModel: OrderViewModel,
        onDriversUpdated: (List<DriversData>) -> Unit,
        context: Context,
    ) {
        val driversOnState = viewModel.stateDriversYayasanOn

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

@Preview(showBackground = true)
@Composable
fun HomeYayasanScreenPreview() {
    HomeYayasanScreen(null, null).MainScreen()
}
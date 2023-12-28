package skripsi.magfira.ambulanceapp.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import skripsi.magfira.ambulanceapp.features.auth.presentation.screens.LoginScreen
import skripsi.magfira.ambulanceapp.features.auth.presentation.screens.SplashScreen
import skripsi.magfira.ambulanceapp.features.auth.presentation.screens.customer.ProfileAccountCustomerScreen
import skripsi.magfira.ambulanceapp.features.auth.presentation.screens.customer.ProfileCustomerScreen
import skripsi.magfira.ambulanceapp.features.auth.presentation.screens.customer.RegisterAccountCustomerScreen
import skripsi.magfira.ambulanceapp.features.auth.presentation.screens.customer.RegisterCustomerScreen
import skripsi.magfira.ambulanceapp.features.auth.presentation.screens.driver.ProfileAccountDriverScreen
import skripsi.magfira.ambulanceapp.features.auth.presentation.screens.driver.ProfileDriverScreen
import skripsi.magfira.ambulanceapp.features.auth.presentation.screens.yayasan.DriversYayasanScreen
import skripsi.magfira.ambulanceapp.features.auth.presentation.screens.yayasan.ProfileAccountYayasanScreen
import skripsi.magfira.ambulanceapp.features.auth.presentation.screens.yayasan.ProfileYayasanScreen
import skripsi.magfira.ambulanceapp.features.auth.presentation.screens.yayasan.RegisterAccountYayasanScreen
import skripsi.magfira.ambulanceapp.features.auth.presentation.screens.yayasan.RegisterYayasanScreen
import skripsi.magfira.ambulanceapp.features.auth.presentation.view_models.AuthViewModel
import skripsi.magfira.ambulanceapp.features.order.presentation.screens.customer.HomeCustomerScreen
import skripsi.magfira.ambulanceapp.features.order.presentation.screens.driver.HomeDriverScreen
import skripsi.magfira.ambulanceapp.features.order.presentation.screens.driver.HomeYayasanScreen
import skripsi.magfira.ambulanceapp.ui.theme.AmbulanceAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AmbulanceAppTheme {
                SetBarColor(color = MaterialTheme.colorScheme.background)
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = ScreenRouter.Auth.route) {
                    navigation(
                        startDestination = ScreenRouter.AuthSplashScreen.route,
                        route = ScreenRouter.Auth.route
                    ) {
                        var viewModel: AuthViewModel? = null

                        composable(ScreenRouter.AuthSplashScreen.route) {
                            viewModel = hiltViewModel()
                            SplashScreen(viewModel!!, navController).MainScreen()
                        }
                        composable(
                            route = ScreenRouter.AuthLogin.route,
                            enterTransition = { fadeIn() },
                            exitTransition = { fadeOut() },
                        ) {
                            LoginScreen(viewModel!!, navController).MainScreen()
                        }
                        composable(
                            route = ScreenRouter.AuthRegisterCustomer.route,
                            enterTransition = { fadeIn() },
                            exitTransition = { fadeOut() },
                        ) {
                            RegisterCustomerScreen(viewModel!!, navController).MainScreen()
                        }
                        composable(
                            route = ScreenRouter.AuthRegisterAccountCustomer.route,
                            enterTransition = { fadeIn() },
                            exitTransition = { fadeOut() },
                        ) {
                            RegisterAccountCustomerScreen(viewModel!!, navController).MainScreen()
                        }
                        composable(
                            route = ScreenRouter.AuthRegisterYayasan.route,
                            enterTransition = { fadeIn() },
                            exitTransition = { fadeOut() },
                        ) {
                            RegisterYayasanScreen(viewModel!!, navController).MainScreen()
                        }
                        composable(
                            route = ScreenRouter.AuthRegisterAccountYayasan.route,
                            enterTransition = { fadeIn() },
                            exitTransition = { fadeOut() },
                        ) {
                            RegisterAccountYayasanScreen(viewModel!!, navController).MainScreen()
                        }
                    }
                    navigation(
                        startDestination = ScreenRouter.CustomerMain.route,
                        route = ScreenRouter.Customer.route,
                    ) {
//                        var viewModel: ViewModel? = null

                        composable(ScreenRouter.CustomerMain.route) {
                            HomeCustomerScreen(null, navController).MainScreen()
                        }
                        composable(ScreenRouter.CustomerProfile.route) {
                            ProfileCustomerScreen(null, navController).MainScreen()
                        }
                        composable(ScreenRouter.CustomerAccount.route) {
                            ProfileAccountCustomerScreen(null, navController).MainScreen()
                        }
                    }
                    navigation(
                        startDestination = ScreenRouter.DriverMain.route,
                        route = ScreenRouter.Driver.route,
                    ) {
//                        var viewModel: ViewModel? = null

                        composable(ScreenRouter.DriverMain.route) {
                            HomeDriverScreen(null, navController).MainScreen()
                        }
                        composable(ScreenRouter.DriverProfile.route) {
                            ProfileDriverScreen(null, navController).MainScreen()
                        }
                        composable(ScreenRouter.DriverAccount.route) {
                            ProfileAccountDriverScreen(null, navController).MainScreen()
                        }
                    }
                    navigation(
                        startDestination = ScreenRouter.YayasanMain.route,
                        route = ScreenRouter.Yayasan.route,
                    ) {
//                        var viewModel: ViewModel? = null
                        val viewModel = null

                        composable(ScreenRouter.YayasanMain.route) {
                            HomeYayasanScreen(null, navController).MainScreen()
                        }
                        composable(ScreenRouter.YayasanProfile.route) {
                            ProfileYayasanScreen(null, navController).MainScreen()
                        }
                        composable(ScreenRouter.YayasanAccount.route) {
                            ProfileAccountYayasanScreen(null, navController).MainScreen()
                        }
                        composable(ScreenRouter.YayasanDrivers.route) {
                            DriversYayasanScreen(null, navController).MainScreen()
                        }
                        composable(ScreenRouter.YayasanDriverAccount.route) {
                            ProfileAccountDriverScreen(null, navController).MainScreen()
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun SetBarColor(color: Color) {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = color
            )
        }
    }

}

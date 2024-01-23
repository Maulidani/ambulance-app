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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
import skripsi.magfira.ambulanceapp.features.auth.presentation.view_models.LoginViewModel
import skripsi.magfira.ambulanceapp.features.auth.presentation.view_models.RegisterCustomerViewModel
import skripsi.magfira.ambulanceapp.features.auth.presentation.view_models.RegisterYayasanViewModel
import skripsi.magfira.ambulanceapp.features.order.presentation.screens.customer.HomeCustomerScreen
import skripsi.magfira.ambulanceapp.features.order.presentation.screens.driver.HomeDriverScreen
import skripsi.magfira.ambulanceapp.features.order.presentation.screens.yayasan.HomeYayasanScreen
import skripsi.magfira.ambulanceapp.features.order.presentation.view_models.OrderViewModel
import skripsi.magfira.ambulanceapp.ui.theme.AmbulanceAppTheme
import skripsi.magfira.ambulanceapp.util.NetworkUtils.NAME_KEY_ADDRESS
import skripsi.magfira.ambulanceapp.util.NetworkUtils.NAME_KEY_EMAIL
import skripsi.magfira.ambulanceapp.util.NetworkUtils.NAME_KEY_FILE_URI
import skripsi.magfira.ambulanceapp.util.NetworkUtils.NAME_KEY_NAME
import skripsi.magfira.ambulanceapp.util.NetworkUtils.NAME_KEY_PHONE

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
                        composable(ScreenRouter.AuthSplashScreen.route) {
//                            val viewModel:  = hiltViewModel()
                            SplashScreen(null, navController).MainScreen()
                        }
                        composable(
                            route = ScreenRouter.AuthLogin.route,
                            enterTransition = { fadeIn() },
                            exitTransition = { fadeOut() },
                        ) {
                            val viewModel: LoginViewModel = hiltViewModel()
                            LoginScreen(viewModel, navController).MainScreen()
                        }
                        composable(
                            route = ScreenRouter.AuthRegisterCustomer.route,
                            enterTransition = { fadeIn() },
                            exitTransition = { fadeOut() },
                        ) {
                            val viewModel: RegisterCustomerViewModel = hiltViewModel()
                            RegisterCustomerScreen(viewModel, navController).MainScreen()
                        }
                        composable(
                            route = ScreenRouter.AuthRegisterAccountCustomer.route,
                            arguments = listOf(
                                navArgument(name = NAME_KEY_NAME) {
                                    type = NavType.StringType
                                },
                                navArgument(name = NAME_KEY_EMAIL) {
                                    type = NavType.StringType
                                },
                                navArgument(name = NAME_KEY_PHONE) {
                                    type = NavType.StringType
                                },
                                navArgument(name = NAME_KEY_FILE_URI) {
                                    type = NavType.StringType
                                },
                            ),
                            enterTransition = { fadeIn() },
                            exitTransition = { fadeOut() },
                        ) {navBackStackEntry ->
                            val arguments = navBackStackEntry.arguments?.run {
                                mapOf(
                                    NAME_KEY_NAME to getString(NAME_KEY_NAME).orEmpty(),
                                    NAME_KEY_EMAIL to getString(NAME_KEY_EMAIL).orEmpty(),
                                    NAME_KEY_PHONE to getString(NAME_KEY_PHONE).orEmpty(),
                                    NAME_KEY_FILE_URI to getString(NAME_KEY_FILE_URI).orEmpty()
                                )
                            } ?: emptyMap()
                            val viewModel: RegisterCustomerViewModel = hiltViewModel()

                            RegisterAccountCustomerScreen(viewModel, navController).MainScreen(arguments)
                        }
                        composable(
                            route = ScreenRouter.AuthRegisterYayasan.route,
                            enterTransition = { fadeIn() },
                            exitTransition = { fadeOut() },
                        ) {
                            RegisterYayasanScreen(null, navController).MainScreen()
                        }
                        composable(
                            route = ScreenRouter.AuthRegisterAccountYayasan.route,
                            arguments = listOf(
                                navArgument(name = NAME_KEY_NAME) {
                                    type = NavType.StringType
                                },
                                navArgument(name = NAME_KEY_EMAIL) {
                                    type = NavType.StringType
                                },
                                navArgument(name = NAME_KEY_PHONE) {
                                    type = NavType.StringType
                                },
                                navArgument(name = NAME_KEY_ADDRESS) {
                                    type = NavType.StringType
                                },
                                navArgument(name = NAME_KEY_FILE_URI) {
                                    type = NavType.StringType
                                },
                            ),
                            enterTransition = { fadeIn() },
                            exitTransition = { fadeOut() },
                        ) {navBackStackEntry ->
                            val arguments = navBackStackEntry.arguments?.run {
                                mapOf(
                                    NAME_KEY_NAME to getString(NAME_KEY_NAME).orEmpty(),
                                    NAME_KEY_EMAIL to getString(NAME_KEY_EMAIL).orEmpty(),
                                    NAME_KEY_PHONE to getString(NAME_KEY_PHONE).orEmpty(),
                                    NAME_KEY_ADDRESS to getString(NAME_KEY_ADDRESS).orEmpty(),
                                    NAME_KEY_FILE_URI to getString(NAME_KEY_FILE_URI).orEmpty()
                                )
                            } ?: emptyMap()
                            val viewModel: RegisterYayasanViewModel = hiltViewModel()

                            RegisterAccountYayasanScreen(viewModel, navController).MainScreen(arguments)
                        }
                    }
                    navigation(
                        startDestination = ScreenRouter.CustomerHome.route,
                        route = ScreenRouter.Customer.route,
                    ) {
                        composable(ScreenRouter.CustomerHome.route) {
                            val viewModel: OrderViewModel = hiltViewModel()
                            HomeCustomerScreen(viewModel, navController).MainScreen()
                        }
                        composable(ScreenRouter.CustomerProfile.route) {
                            ProfileCustomerScreen(null, navController).MainScreen()
                        }
                        composable(ScreenRouter.CustomerAccount.route) {
                            ProfileAccountCustomerScreen(null, navController).MainScreen()
                        }
                    }
                    navigation(
                        startDestination = ScreenRouter.DriverHome.route,
                        route = ScreenRouter.Driver.route,
                    ) {
                        composable(ScreenRouter.DriverHome.route) {
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
                        startDestination = ScreenRouter.YayasanHome.route,
                        route = ScreenRouter.Yayasan.route,
                    ) {
                        composable(ScreenRouter.YayasanHome.route) {
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

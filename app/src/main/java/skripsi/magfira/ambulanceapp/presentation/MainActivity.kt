package skripsi.magfira.ambulanceapp.presentation

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
import skripsi.magfira.ambulanceapp.presentation.auth.screens.LoginScreen
import skripsi.magfira.ambulanceapp.presentation.auth.screens.customer.RegisterAccountCustomerScreen
import skripsi.magfira.ambulanceapp.presentation.auth.screens.yayasan.RegisterAccountYayasanScreen
import skripsi.magfira.ambulanceapp.presentation.auth.screens.customer.RegisterCustomerScreen
import skripsi.magfira.ambulanceapp.presentation.auth.screens.yayasan.RegisterYayasanScreen
import skripsi.magfira.ambulanceapp.presentation.auth.screens.SplashScreen
import skripsi.magfira.ambulanceapp.presentation.auth.view_model.AuthViewModel
import skripsi.magfira.ambulanceapp.presentation.main.customer.MainCustomerScreen
import skripsi.magfira.ambulanceapp.presentation.main.driver.MainDriverScreen
import skripsi.magfira.ambulanceapp.presentation.main.yayasan.MainYayasanScreen
import skripsi.magfira.ambulanceapp.utils.ui.theme.AmbulanceAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AmbulanceAppTheme {
                SetBarColor(color = MaterialTheme.colorScheme.background)
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = ScreenRoute.Auth.route) {
                    navigation(
                        startDestination = ScreenRoute.AuthSplashScreen.route,
                        route = ScreenRoute.Auth.route
                    ) {
                        var viewModel: AuthViewModel? = null

                        composable(ScreenRoute.AuthSplashScreen.route) {
                            viewModel = hiltViewModel()
                            SplashScreen(viewModel!!, navController).MainScreen()
                        }
                        composable(
                            route = ScreenRoute.AuthLogin.route,
                            enterTransition = { fadeIn() },
                            exitTransition = { fadeOut() },
                        ) {
                            LoginScreen(viewModel!!, navController).MainScreen()
                        }
                        composable(
                            route = ScreenRoute.AuthRegisterCustomer.route,
                            enterTransition = { fadeIn() },
                            exitTransition = { fadeOut() },
                        ) {
                            RegisterCustomerScreen(viewModel!!, navController).MainScreen()
                        }
                        composable(
                            route = ScreenRoute.AuthRegisterAccountCustomer.route,
                            enterTransition = { fadeIn() },
                            exitTransition = { fadeOut() },
                        ) {
                            RegisterAccountCustomerScreen(viewModel!!, navController).MainScreen()
                        }
                        composable(
                            route = ScreenRoute.AuthRegisterYayasan.route,
                            enterTransition = { fadeIn() },
                            exitTransition = { fadeOut() },
                        ) {
                            RegisterYayasanScreen(viewModel!!, navController).MainScreen()
                        }
                        composable(
                            route = ScreenRoute.AuthRegisterAccountYayasan.route,
                            enterTransition = { fadeIn() },
                            exitTransition = { fadeOut() },
                        ) {
                            RegisterAccountYayasanScreen(viewModel!!, navController).MainScreen()
                        }
                    }
                    navigation(
                        startDestination = ScreenRoute.CustomerMain.route,
                        route = ScreenRoute.Customer.route,
                    ) {
//                        var viewModel: ViewModel? = null

                        composable(ScreenRoute.CustomerMain.route) {
                            MainCustomerScreen(null, navController).MainScreen()
                        }
                        composable(ScreenRoute.CustomerProfile.route) {

                        }
                        composable(ScreenRoute.CustomerAccount.route) {

                        }
                    }
                    navigation(
                        startDestination = ScreenRoute.DriverMain.route,
                        route = ScreenRoute.Driver.route,
                    ) {
//                        var viewModel: ViewModel? = null

                        composable(ScreenRoute.DriverMain.route) {
                            MainDriverScreen(null, navController).MainScreen()
                        }
                        composable(ScreenRoute.DriverProfile.route) {

                        }
                        composable(ScreenRoute.DriverAccount.route) {

                        }
                    }
                    navigation(
                        startDestination = ScreenRoute.YayasanMain.route,
                        route = ScreenRoute.Yayasan.route,
                    ) {
//                        var viewModel: ViewModel? = null
                        val viewModel = null

                        composable(ScreenRoute.YayasanMain.route) {
                            MainYayasanScreen(null, navController).MainScreen()
                        }
                        composable(ScreenRoute.YayasanProfile.route) {

                        }
                        composable(ScreenRoute.YayasanAccount.route) {

                        }
                        composable(ScreenRoute.YayasanDrivers.route) {

                        }
                        composable(ScreenRoute.YayasanDriverAccount.route) {

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

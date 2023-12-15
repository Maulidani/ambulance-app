package skripsi.magfira.ambulanceapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import skripsi.magfira.ambulanceapp.presentation.auth.screens.LoginScreen
import skripsi.magfira.ambulanceapp.presentation.auth.screens.SplashScreen
import skripsi.magfira.ambulanceapp.presentation.auth.view_model.AuthViewModel
import skripsi.magfira.ambulanceapp.utils.ui.theme.AmbulanceAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AmbulanceAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screen.Auth.route) {
                    navigation(
                        startDestination = Screen.AuthSplashScreen.route,
                        route = Screen.Auth.route
                    ) {
                        var viewModel: AuthViewModel? = null

                        composable(Screen.AuthSplashScreen.route) {
                            viewModel = hiltViewModel()
                            SplashScreen(viewModel!!, navController)
                        }
                        composable(Screen.AuthLogin.route) {
                            LoginScreen(viewModel!!, navController)

                        }
                        composable(Screen.AuthRegisterCustomer.route) {

                        }
                        composable(Screen.AuthRegisterYayasan.route) {

                        }
                    }
                    navigation(
                        startDestination = "home",
                        route = "customer"
                    ) {
                        composable("home") {

                        }
                        composable("profile") {

                        }
                    }
                    navigation(
                        startDestination = "home",
                        route = "yayasan"
                    ) {
                        composable("home") {

                        }
                        composable("profile") {

                        }
                        composable("drivers") {

                        }
                        composable("driver_profile") {

                        }
                    }
                    navigation(
                        startDestination = "home",
                        route = "driver"
                    ) {
                        composable("home") {

                        }
                        composable("profile") {

                        }
                    }
                }
            }
        }
    }
}

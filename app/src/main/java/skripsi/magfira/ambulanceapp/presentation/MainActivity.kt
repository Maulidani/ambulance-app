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
import skripsi.magfira.ambulanceapp.presentation.auth.screens.SplashScreen
import skripsi.magfira.ambulanceapp.presentation.auth.view_model.AuthViewModel
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
                            SplashScreen(viewModel!!, navController)
                        }
                        composable(
                            route = ScreenRoute.AuthLogin.route,
                            enterTransition = { fadeIn() },
                            exitTransition = { fadeOut() },
                        ) {
                            LoginScreen(viewModel!!, navController)
                        }
                        composable(
                            route = ScreenRoute.AuthRegisterCustomer.route,
                            enterTransition = { fadeIn() },
                            exitTransition = { fadeOut() },
                        ) {

                        }
                        composable(
                            route = ScreenRoute.AuthRegisterYayasan.route,
                            enterTransition = { fadeIn() },
                            exitTransition = { fadeOut() },
                        ) {

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

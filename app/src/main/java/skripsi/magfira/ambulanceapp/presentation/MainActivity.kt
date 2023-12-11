package skripsi.magfira.ambulanceapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import skripsi.magfira.ambulanceapp.presentation.auth.view_model.AuthViewModel
import skripsi.magfira.ambulanceapp.utils.ui.theme.AmbulanceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AmbulanceAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "auth") {
                    navigation(
                        startDestination = "login",
                        route = "auth"
                    ) {
                        composable("login") {
                            val viewModel = it.sharedViewModel<AuthViewModel>(navController)

                            Button(onClick = {
                                navController.navigate("customer") {
                                    popUpTo("auth") {
                                        inclusive = true
                                    }
                                }
                            }) {

                            }
                        }
                        composable("register") {
                            val viewModel = it.sharedViewModel<AuthViewModel>(navController)
                        }
                        composable("forgot_password") {
                            val viewModel = it.sharedViewModel<AuthViewModel>(navController)
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

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            AmbulanceAppTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    AmbulanceAppTheme {
//        Greeting("Android")
//    }
//}
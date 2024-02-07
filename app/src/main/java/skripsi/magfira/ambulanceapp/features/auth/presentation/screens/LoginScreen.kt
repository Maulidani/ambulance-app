package skripsi.magfira.ambulanceapp.features.auth.presentation.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.first
import skripsi.magfira.ambulanceapp.R
import skripsi.magfira.ambulanceapp.datastore.DataStorePreferences
import skripsi.magfira.ambulanceapp.features.auth.domain.model.response.Login
import skripsi.magfira.ambulanceapp.features.auth.presentation.components.HeaderScreenLogin
import skripsi.magfira.ambulanceapp.features.auth.presentation.components.TabViewLogin
import skripsi.magfira.ambulanceapp.features.auth.presentation.screens.customer.CustomerScreen
import skripsi.magfira.ambulanceapp.features.auth.presentation.screens.driver.DriverScreen
import skripsi.magfira.ambulanceapp.features.auth.presentation.screens.yayasan.YayasanScreen
import skripsi.magfira.ambulanceapp.features.auth.presentation.view_models.AuthViewModel
import skripsi.magfira.ambulanceapp.navigation.ScreenRouter
import skripsi.magfira.ambulanceapp.util.MessageUtils.MSG_LOGIN_FAILED
import javax.inject.Inject

class LoginScreen(
    private val viewModel: AuthViewModel?,
    private val navController: NavHostController?
) {
    private val TAG = "LoginScreen"
    private val TAB_OPTIONS_ROLES = listOf("Customer", "Driver", "Yayasan")

    @Inject
    lateinit var dataStorePreferences: DataStorePreferences

    @Composable
    fun MainScreen() {
        val context = LocalContext.current
        dataStorePreferences = DataStorePreferences(context)

        val tabOptions = TAB_OPTIONS_ROLES
        var selectedTab by rememberSaveable { mutableStateOf(tabOptions[0]) }

        // Check Login
        LaunchedEffect(true) {
            val isLogin = dataStorePreferences.getUserIsLogin.first()
            val role = dataStorePreferences.getUserRole.first()

            if (isLogin == true) {

                when (role?.lowercase()) {
                    tabOptions[0].lowercase() -> {
                        // Navigate
                        navController?.navigate(ScreenRouter.Customer.route) {
                            popUpTo(ScreenRouter.Auth.route) {
                                inclusive = false
                            }
                        }
                    }

                    tabOptions[1].lowercase() -> {
                        // Navigate
                        navController?.navigate(ScreenRouter.Driver.route) {
                            popUpTo(ScreenRouter.Auth.route) {
                                inclusive = false
                            }
                        }
                    }

                    tabOptions[2].lowercase() -> {
                        //Navigate
                        navController?.navigate(ScreenRouter.Yayasan.route) {
                            popUpTo(ScreenRouter.Auth.route) {
                                inclusive = false
                            }
                        }
                    }
                }
            } else {
                // Doing nothing
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            HeaderScreenLogin(source = painterResource(id = R.drawable.logo_only))
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                shape = RoundedCornerShape(24.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.secondary)
                ) {
                    TabViewLogin(
                        tabOptions = tabOptions,
                        selectedTab = selectedTab,
                        onTabSelected = { newTab -> selectedTab = newTab }
                    )
                    AnimatedContent(
                        targetState = selectedTab,
                        label = "Tab Content"
                    ) { currentTab ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 24.dp)
                        ) {
                            when (currentTab) {
                                tabOptions[0] -> CustomerScreen(
                                    viewModel,
                                    navController,
                                    context
                                )

                                tabOptions[1] -> DriverScreen(
                                    viewModel,
                                    navController,
                                    context
                                )

                                tabOptions[2] -> YayasanScreen(
                                    viewModel,
                                    navController,
                                    context
                                )
                            }
                        }
                    }
                }
            }
        }

        // Observe ViewModel
        viewModel?.let {
            ViewModelObserver(
                it,
                navController,
                context,
                selectedTab,
                tabOptions
            )
        }

    }

    @Composable
    fun ViewModelObserver(
        viewModel: AuthViewModel,
        navController: NavHostController?,
        context: Context,
        selectedTab: String,
        tabOptions: List<String>,
    ) {
        val loginState = viewModel.stateLogin

        when {
            loginState.isLoading -> {}
            loginState.data != null -> {
                val loginData = loginState.data!!

                // Save data login
                LaunchedEffect(true) {
                    dataStorePreferences.saveLogin(true)
                    dataStorePreferences.saveToken(loginData.token)
                    dataStorePreferences.saveUserId(loginData.user.id.toString())
                    dataStorePreferences.saveRole(selectedTab.lowercase())
                }

                // Navigate
                when (selectedTab) {
                    tabOptions[0] -> {
                        NavigateToRoles(
                            navController,
                            selectedTab,
                            loginData,
                            ScreenRouter.Customer.route,
                            context
                        )
                    }

                    tabOptions[1] -> {
                        NavigateToRoles(
                            navController,
                            selectedTab,
                            loginData,
                            ScreenRouter.Driver.route,
                            context
                        )
                    }

                    tabOptions[2] -> {
                        NavigateToRoles(
                            navController,
                            selectedTab,
                            loginData,
                            ScreenRouter.Yayasan.route,
                            context
                        )
                    }
                }
            }

            loginState.error.isNotEmpty() -> {
                val errorMessage = loginState.error
                Log.d(TAG, "ViewModelObserver: $errorMessage")

                LaunchedEffect(loginState) {
                    Toast.makeText(context, MSG_LOGIN_FAILED, Toast.LENGTH_SHORT).show()
                }
            }

            else -> {
                // Initial state or other cases
            }
        }
    }

    @Composable
    private fun NavigateToRoles(
        navController: NavHostController?,
        selectedTab: String,
        loginData: Login?,
        tabRoute: String,
        context: Context
    ) {
        if (loginData?.user?.roles?.lowercase() == selectedTab.lowercase()) {
            LaunchedEffect(loginData) {
                navController?.navigate(tabRoute) {
                    popUpTo(ScreenRouter.Auth.route) {
                        inclusive = false
                    }
                }
            }
        } else {
            Log.d(TAG, "ViewModelObserver: WRONG ROLES")

            LaunchedEffect(loginData) {
                Toast.makeText(context, MSG_LOGIN_FAILED, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(null, null).MainScreen()
}

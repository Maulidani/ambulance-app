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
import skripsi.magfira.ambulanceapp.R
import skripsi.magfira.ambulanceapp.features.auth.domain.model.response.Login
import skripsi.magfira.ambulanceapp.features.auth.presentation.components.HeaderScreenLogin
import skripsi.magfira.ambulanceapp.features.auth.presentation.components.TabViewLogin
import skripsi.magfira.ambulanceapp.features.auth.presentation.screens.customer.CustomerScreen
import skripsi.magfira.ambulanceapp.features.auth.presentation.screens.driver.DriverScreen
import skripsi.magfira.ambulanceapp.features.auth.presentation.screens.yayasan.YayasanScreen
import skripsi.magfira.ambulanceapp.features.auth.presentation.view_models.LoginViewModel
import skripsi.magfira.ambulanceapp.navigation.ScreenRouter
import skripsi.magfira.ambulanceapp.util.MessageUtils.MSG_LOGIN_FAILED

class LoginScreen(
    private val viewModel: LoginViewModel?,
    private val navController: NavHostController?
) {
    private val TAG = "LoginScreen"
    private val TAB_OPTIONS_ROLES = listOf("Customer", "Driver", "Yayasan")

    @Composable
    fun MainScreen() {
        val context = LocalContext.current
        val tabOptions = TAB_OPTIONS_ROLES
        var selectedTab by rememberSaveable { mutableStateOf(tabOptions[0]) }

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
                                tabOptions[0] -> CustomerScreen(viewModel, navController, context)
                                tabOptions[1] -> DriverScreen(viewModel, navController, context)
                                tabOptions[2] -> YayasanScreen(viewModel, navController, context)
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
        viewModel: LoginViewModel,
        navController: NavHostController?,
        context: Context,
        selectedTab: String,
        tabOptions: List<String>,
    ) {
        val loginState = viewModel.stateLogin

        when {
            loginState.isLoading -> {}
            loginState.data != null -> {
                val loginData = loginState.data
                when (selectedTab) {
                    tabOptions[0] -> NavigateToRoles(
                        navController,
                        selectedTab,
                        loginData,
                        ScreenRouter.Customer.route,
                        context
                    )

                    tabOptions[1] -> NavigateToRoles(
                        navController,
                        selectedTab,
                        loginData,
                        ScreenRouter.Driver.route,
                        context
                    )

                    tabOptions[2] -> NavigateToRoles(
                        navController,
                        selectedTab,
                        loginData,
                        ScreenRouter.Yayasan.route,
                        context
                    )
                }
            }
            loginState.error.isNotEmpty() -> {
                val errorMessage = loginState.error
                Log.d(TAG, "ViewModelObserver: $errorMessage")
                Toast.makeText(context, MSG_LOGIN_FAILED, Toast.LENGTH_SHORT).show()
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
            Toast.makeText(context, MSG_LOGIN_FAILED, Toast.LENGTH_SHORT).show()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(null, null).MainScreen()
}

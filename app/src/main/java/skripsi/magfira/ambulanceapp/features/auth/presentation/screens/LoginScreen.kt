package skripsi.magfira.ambulanceapp.features.auth.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import skripsi.magfira.ambulanceapp.R
import skripsi.magfira.ambulanceapp.features.auth.presentation.components.HeaderScreenLogin
import skripsi.magfira.ambulanceapp.features.auth.presentation.components.TabViewLogin
import skripsi.magfira.ambulanceapp.features.auth.presentation.screens.customer.CustomerScreen
import skripsi.magfira.ambulanceapp.features.auth.presentation.screens.driver.DriverScreen
import skripsi.magfira.ambulanceapp.features.auth.presentation.screens.yayasan.YayasanScreen
import skripsi.magfira.ambulanceapp.features.auth.presentation.view_models.AuthViewModel

class LoginScreen(
    private val viewModel: AuthViewModel?,
    private val navController: NavHostController?
) {
    @Composable
    fun MainScreen(
    ) {
        val tabOptions = listOf("Customer", "Driver", "Yayasan")
        var selectedTab by remember { mutableStateOf(tabOptions[0]) }

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
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 24.dp)
                    ) {
                        when (selectedTab) {
                            tabOptions[0] -> CustomerScreen(viewModel, navController)
                            tabOptions[1] -> DriverScreen(viewModel, navController)
                            tabOptions[2] -> YayasanScreen(viewModel, navController)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(null, null).MainScreen()
}

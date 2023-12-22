package skripsi.magfira.ambulanceapp.presentation.auth.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import skripsi.magfira.ambulanceapp.R
import skripsi.magfira.ambulanceapp.presentation.ScreenRoute
import skripsi.magfira.ambulanceapp.presentation.auth.screens.customer.CustomerScreen
import skripsi.magfira.ambulanceapp.presentation.auth.screens.driver.DriverScreen
import skripsi.magfira.ambulanceapp.presentation.auth.screens.yayasan.YayasanScreen
import skripsi.magfira.ambulanceapp.presentation.auth.view_model.AuthViewModel

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
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .background(color = Color.Gray)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_only),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 150.dp)
                        .padding(top = 24.dp),
                    contentScale = ContentScale.Fit
                )
            }
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
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
                    ) {
                        TabRow(selectedTabIndex = tabOptions.indexOf(selectedTab), indicator = {}) {
                            tabOptions.forEachIndexed { index, tab ->
                                Surface(
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(24.dp),
                                ) {
                                    Tab(modifier = Modifier.background(
                                        if (selectedTab == tab) MaterialTheme.colorScheme.primary
                                        else MaterialTheme.colorScheme.background
                                    ),
                                        text = {
                                            Text(
                                                text = tab,
                                                style = MaterialTheme.typography.titleMedium,
                                                color = if (selectedTab == tab) MaterialTheme.colorScheme.background
                                                else MaterialTheme.colorScheme.primary
                                            )
                                        },
                                        selected = selectedTab == tab,
                                        onClick = { selectedTab = tab })
                                }
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 24.dp)
                    ) {
                        when (selectedTab) {
                            "Customer" -> CustomerScreen(viewModel,navController)
                            "Driver" -> DriverScreen(viewModel,navController)
                            "Yayasan" -> YayasanScreen(viewModel,navController)
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

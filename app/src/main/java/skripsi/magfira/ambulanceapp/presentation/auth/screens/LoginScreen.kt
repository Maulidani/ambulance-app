package skripsi.magfira.ambulanceapp.presentation.auth.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import skripsi.magfira.ambulanceapp.R
import skripsi.magfira.ambulanceapp.presentation.auth.view_model.AuthViewModel

@Composable
fun LoginScreen(
    viewModel: AuthViewModel?,
    navController: NavHostController?
) {
    val tabOptions = listOf("Customer", "Driver", "Yayasan")
    var selectedTab by remember { mutableStateOf(tabOptions[0]) }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .height(200.dp)
                .background(color = Color.LightGray)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_only),
                contentDescription = "logo_only",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 150.dp)
                    .padding(top = 24.dp),
                contentScale = ContentScale.Fit
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            TabRow(selectedTabIndex = tabOptions.indexOf(selectedTab)) {
                tabOptions.forEachIndexed { index, tab ->
                    Tab(
                        text = { Text(tab) },
                        selected = selectedTab == tab,
                        onClick = { selectedTab = tab }
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            when (selectedTab) {
                "Customer" -> CustomerScreen()
                "Driver" -> DriverScreen()
                "Yayasan" -> YayasanScreen()
            }
        }
    }
}

@Composable
fun CustomerScreen() {
    Text(
        text = "Customer Screen Content",
        color = Color.White,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Blue)
    )
}

@Composable
fun DriverScreen() {
    Text(
        text = "Driver Screen Content",
        color = Color.White,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Red)
    )
}

@Composable
fun YayasanScreen() {
    Text(
        text = "Yayasan Screen Content",
        color = Color.White,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.DarkGray)
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(null, null)

}

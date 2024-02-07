package skripsi.magfira.ambulanceapp.features.auth.presentation.screens.driver

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import skripsi.magfira.ambulanceapp.features.auth.presentation.screens.yayasan.ProfileDriverYayasanScreen
import skripsi.magfira.ambulanceapp.features.auth.presentation.view_models.AuthViewModel
import skripsi.magfira.ambulanceapp.features.common.presentation.components.AppBar
import skripsi.magfira.ambulanceapp.features.common.presentation.components.TextFieldProfile

class ProfileAccountDriverScreen(
    private val viewModel: AuthViewModel?,
    private val navController: NavHostController?
) {

    // Safe back
    private val NavHostController.canGoBack : Boolean
        get() = this.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED

    @Composable
    fun MainScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        ) {
            AppBar(
                title = "Pengaturan",
                iconBackClick = {
                    if (navController?.canGoBack == true) {
                        navController.popBackStack()
                    }
                }
            )
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                var username by remember { mutableStateOf("Username") }
                var password by remember { mutableStateOf("Password") }
                Text(
                    modifier = Modifier,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center,
                    text = "Akun",
                )
                Spacer(modifier = Modifier.height(24.dp))
                TextFieldProfile(
                    value = username,
                    icon = Icons.Default.Person,
                    iconEnd = Icons.Default.Edit,
                    iconEndClicked = {
                        //
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldProfile(
                    value = password,
                    icon = Icons.Default.Lock,
                    iconEnd = Icons.Default.Edit,
                    iconEndClicked = {
                        //
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileAccountDriverScreenPreview() {
    ProfileAccountDriverScreen(null, null).MainScreen()
}

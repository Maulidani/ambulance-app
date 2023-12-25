package skripsi.magfira.ambulanceapp.features.auth.presentation.screens.customer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import skripsi.magfira.ambulanceapp.features.auth.presentation.view_models.AuthViewModel
import skripsi.magfira.ambulanceapp.features.common.presentation.components.AppBar
import skripsi.magfira.ambulanceapp.features.common.presentation.components.IconButton
import skripsi.magfira.ambulanceapp.features.common.presentation.components.TextFieldForm
import skripsi.magfira.ambulanceapp.features.common.presentation.components.TextFieldPasswordForm

class RegisterAccountCustomerScreen(
    private val viewModel: AuthViewModel?,
    private val navController: NavHostController?
) {
    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun MainScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        ) {
            AppBar(
                title = "Daftar Customer",
                iconBackClick = {
                    navController?.popBackStack()
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.secondary)
                        .padding(all = 24.dp)
                ) {
                    val username = remember { mutableStateOf("") }
                    val password = remember { mutableStateOf("") }
                    val confirmPassword = remember { mutableStateOf("") }
                    var passwordVisibility by remember { mutableStateOf(false) }
                    var confirmPasswordVisibility by remember { mutableStateOf(false) }
                    val keyboardController = LocalSoftwareKeyboardController.current

                    Text(
                        text = "Lengkapi data berikut",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    TextFieldForm(
                        value = username.value,
                        onValueChange = { username.value = it },
                        label = "Nama Lengkap",
                        icon = Icons.Default.Person
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TextFieldPasswordForm(
                        value = password.value,
                        onValueChange = { password.value = it },
                        label = "Password",
                        icon = Icons.Default.Lock,
                        passwordVisibility = passwordVisibility,
                        onTogglePasswordVisibility = { passwordVisibility = !passwordVisibility },
                        keyboardController = keyboardController
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TextFieldPasswordForm(
                        value = confirmPassword.value,
                        onValueChange = { confirmPassword.value = it },
                        label = "Ulangi Password",
                        icon = Icons.Default.Lock,
                        passwordVisibility = confirmPasswordVisibility,
                        onTogglePasswordVisibility = {
                            confirmPasswordVisibility = !confirmPasswordVisibility
                        },
                        keyboardController = keyboardController
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        IconButton(
                            modifier = Modifier,
                            onClick = {
                                //
                            },
                            icon = Icons.Default.ArrowForwardIos,
                            text = "Daftar",
                            backgroundColor = MaterialTheme.colorScheme.primary,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterAccountCustomerScreenPreview() {
    RegisterAccountCustomerScreen(null, null).MainScreen()
}

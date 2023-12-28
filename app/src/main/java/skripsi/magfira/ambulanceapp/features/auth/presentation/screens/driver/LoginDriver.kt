package skripsi.magfira.ambulanceapp.features.auth.presentation.screens.driver

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import skripsi.magfira.ambulanceapp.features.auth.presentation.view_models.AuthViewModel
import skripsi.magfira.ambulanceapp.features.common.presentation.components.ButtonIcon
import skripsi.magfira.ambulanceapp.features.common.presentation.components.TextFieldForm
import skripsi.magfira.ambulanceapp.features.common.presentation.components.TextFieldPasswordForm
import skripsi.magfira.ambulanceapp.navigation.ScreenRouter

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DriverScreen(
    menu: AuthViewModel?,
    navController: NavHostController?
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    TextFieldForm(
        value = username,
        onValueChange = { password = it },
        label = "Username",
        icon = Icons.Default.Person
    )
    Spacer(modifier = Modifier.height(16.dp))
    TextFieldPasswordForm(
        value = password,
        onValueChange = { password = it },
        label = "Password",
        icon = Icons.Default.Lock,
        passwordVisibility = passwordVisibility,
        onTogglePasswordVisibility = { passwordVisibility = !passwordVisibility },
        keyboardController = keyboardController
    )
    Spacer(modifier = Modifier.height(24.dp))
    ButtonIcon(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            navController?.navigate(ScreenRouter.Driver.route)
        },
        icon = Icons.Default.ArrowForwardIos,
        text = "Masuk",
        textColor = Color.White,
        backgroundColor = MaterialTheme.colorScheme.primary,
    )
    val textRegister = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.Black)) {
            append("Belum punya akun ? ")
        }
        withStyle(style = SpanStyle(color = Color.Black)) {
            append("Silahkan hubungi yayasan anda.")
        }
    }
    Text(
        modifier = Modifier
            .padding(top = 24.dp)
            .fillMaxWidth(),
        text = textRegister,
        style = MaterialTheme.typography.bodySmall,
        textAlign = TextAlign.Center,
    )
}

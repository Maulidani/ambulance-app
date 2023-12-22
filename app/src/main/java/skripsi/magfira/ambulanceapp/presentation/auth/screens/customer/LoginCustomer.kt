package skripsi.magfira.ambulanceapp.presentation.auth.screens.customer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import skripsi.magfira.ambulanceapp.presentation.ScreenRoute
import skripsi.magfira.ambulanceapp.presentation.auth.view_model.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CustomerScreen(
    viewModel: AuthViewModel?,
    navController: NavHostController?
) {
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = username.value,
        onValueChange = { username.value = it },
        label = { Text("Username") },
        shape = RoundedCornerShape(24.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = MaterialTheme.colorScheme.background,
            focusedBorderColor = Color.Transparent,
            focusedLabelColor = Color.Gray,
            unfocusedBorderColor = Color.Transparent,
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Username",
                tint = Color.Gray,
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        singleLine = true
    )
    TextField(
        value = password.value,
        onValueChange = { password.value = it },
        label = { Text("Password") },
        shape = RoundedCornerShape(24.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = MaterialTheme.colorScheme.background,
            focusedBorderColor = Color.Transparent,
            focusedLabelColor = Color.Gray,
            unfocusedBorderColor = Color.Transparent,
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Password",
                tint = Color.Gray,
            )
        },
        trailingIcon = {
            Icon(
                imageVector = if (passwordVisibility) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                contentDescription = "Toggle Password Visibility",
                tint = Color.Gray,
                modifier = Modifier.clickable {
                    passwordVisibility = !passwordVisibility
                    keyboardController?.hide()
                }
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        singleLine = true,
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Button(
            onClick = {
                navController?.navigate(ScreenRoute.Customer.route)
            },
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(
                text = "Masuk",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .background(MaterialTheme.colorScheme.primary),
            )
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = "Arrow Forward",
                tint = Color.White,
            )
        }
    }
    val textRegister = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.Black)) {
            append("Belum punya akun ? ")
        }
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            append("Daftar di sini.")
        }
    }
    Text(
        text = textRegister,
        style = MaterialTheme.typography.bodySmall,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .clickable {
                navController?.navigate(ScreenRoute.AuthRegisterCustomer.route)
            }
    )
}

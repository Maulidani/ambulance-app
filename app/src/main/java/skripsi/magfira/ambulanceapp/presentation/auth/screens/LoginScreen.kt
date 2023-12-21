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
import androidx.compose.ui.text.font.FontWeight
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
                    contentDescription = "logo_only",
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
                            "Customer" -> CustomerScreen(selectedTab)
                            "Driver" -> DriverScreen(selectedTab)
                            "Yayasan" -> YayasanScreen(selectedTab)
                        }
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
    @Composable
    fun CustomerScreen(menu: String) {
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
                    /* Handle login */
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

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
    @Composable
    fun DriverScreen(menu: String) {
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
            withStyle(style = SpanStyle(color = Color.Black)) {
                append("Silahkan hubungi yayasan anda.")
            }
        }
        Text(
            text = textRegister,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
        )
    }

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
    @Composable
    fun YayasanScreen(menu: String) {
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
                        keyboardController?.hide() // Hide the keyboard after toggling visibility
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
                    /* Handle login */
                },
                shape = RoundedCornerShape(24.dp),
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
                    navController?.navigate(ScreenRoute.AuthRegisterYayasan.route)
                }
        )
    }

}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(null, null).MainScreen()
}

package skripsi.magfira.ambulanceapp.features.auth.presentation.screens.yayasan

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import skripsi.magfira.ambulanceapp.features.auth.domain.model.request.RegisterYayasanRequest
import skripsi.magfira.ambulanceapp.features.auth.presentation.view_models.RegisterYayasanViewModel
import skripsi.magfira.ambulanceapp.features.common.presentation.components.AppBar
import skripsi.magfira.ambulanceapp.features.common.presentation.components.ButtonIcon
import skripsi.magfira.ambulanceapp.features.common.presentation.components.TextFieldForm
import skripsi.magfira.ambulanceapp.features.common.presentation.components.TextFieldPasswordForm
import skripsi.magfira.ambulanceapp.navigation.ScreenRouter
import skripsi.magfira.ambulanceapp.util.InputValidation
import skripsi.magfira.ambulanceapp.util.MessageUtils
import skripsi.magfira.ambulanceapp.util.NetworkUtils

class RegisterAccountYayasanScreen(
    private val viewModel: RegisterYayasanViewModel?,
    private val navController: NavHostController?
) {
    private val TAG = "RegisterAccountYayasanScreen"

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun MainScreen(arguments: Map<String, String>?) {
        val context = LocalContext.current

        Log.d(TAG, "Passed: $arguments")
        val name = arguments?.get(NetworkUtils.NAME_KEY_NAME) ?: ""
        val email = arguments?.get(NetworkUtils.NAME_KEY_EMAIL) ?: ""
        val phone = arguments?.get(NetworkUtils.NAME_KEY_PHONE) ?: ""
        val address = arguments?.get(NetworkUtils.NAME_KEY_ADDRESS) ?: ""
        val encodedImageUriString = arguments?.get(NetworkUtils.NAME_KEY_FILE_URI) ?: ""
        val decodedUriString: String = Uri.decode(encodedImageUriString)
        val decodedUri: Uri = Uri.parse(decodedUriString)

        var username by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }
        var confirmPassword by rememberSaveable { mutableStateOf("") }
        var passwordVisibility by rememberSaveable { mutableStateOf(false) }
        var confirmPasswordVisibility by rememberSaveable { mutableStateOf(false) }
        val keyboardController = LocalSoftwareKeyboardController.current

        val scope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }

        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
        ) { _ ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            ) {
                AppBar(
                    title = "Daftar Yayasan",
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
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            text = "Lengkapi data berikut",
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        TextFieldForm(
                            value = username,
                            onValueChange = { username = it },
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
                            onTogglePasswordVisibility = {
                                passwordVisibility = !passwordVisibility
                            },
                            keyboardController = keyboardController
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        TextFieldPasswordForm(
                            value = confirmPassword,
                            onValueChange = { confirmPassword = it },
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
                            ButtonIcon(
                                modifier = Modifier,
                                onClick = {
                                    if (
                                        username.isEmpty() ||
                                        password.isEmpty() ||
                                        confirmPassword.isEmpty()
                                    ) {
                                        Toast.makeText(
                                            context,
                                            MessageUtils.MSG_REQUIRED_FIELDS,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else if (password.lowercase() != confirmPassword.lowercase()) {
                                        Toast.makeText(
                                            context,
                                            MessageUtils.MSG_PASSWORDS_NOT_MATCH, Toast.LENGTH_SHORT
                                        ).show()
                                    } else if (
                                        !InputValidation.containsNoSpaces(username) ||
                                        !InputValidation.containsNoSpaces(password) ||
                                        !InputValidation.containsNoSpaces(confirmPassword)
                                    ) {
                                        Toast.makeText(
                                            context,
                                            MessageUtils.MSG_INPUT_CONTAIN_SPACE,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {

                                        lateinit var partMap: Map<String, RequestBody>
                                        lateinit var filePart: MultipartBody.Part
                                        try {
                                            partMap = RegisterYayasanRequest(
                                                NetworkUtils.ROLE_YAYASAN,
                                                NetworkUtils.ACCOUNT_ON,
                                                name,
                                                email,
                                                phone,
                                                address,
                                                username,
                                                password,
                                                confirmPassword,
                                                decodedUri,
                                                context
                                            ).first
                                            filePart = RegisterYayasanRequest(
                                                NetworkUtils.ROLE_YAYASAN,
                                                NetworkUtils.ACCOUNT_ON,
                                                name,
                                                email,
                                                phone,
                                                address,
                                                username,
                                                password,
                                                confirmPassword,
                                                decodedUri,
                                                context
                                            ).second

                                            // Show confirmation
                                            scope.launch {
                                                val result = snackbarHostState
                                                    .showSnackbar(
                                                        message = MessageUtils.MSG_REGISTER_CONFIRM,
                                                        actionLabel = "Yes",
                                                        // Defaults to SnackbarDuration.Short
                                                        duration = SnackbarDuration.Long
                                                    )
                                                when (result) {
                                                    SnackbarResult.ActionPerformed -> {
                                                        // Continue register
                                                        viewModel?.registerYayasan(
                                                            partMap,
                                                            filePart
                                                        )
                                                    }

                                                    SnackbarResult.Dismissed -> {
                                                        /* Handle snackbar dismissed */
                                                    }
                                                }
                                            }

                                        } catch (e: Exception) {
                                            Log.d(
                                                TAG,
                                                "MainScreen: RegisterYayasanRequest: ${e.localizedMessage}"
                                            )
                                            Toast.makeText(
                                                context,
                                                e.localizedMessage,
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                },
                                icon = Icons.Default.ArrowForwardIos,
                                text = "Daftar",
                                textColor = Color.White,
                                backgroundColor = MaterialTheme.colorScheme.primary,
                                isLoading = viewModel?.stateRegisterYayasan?.isLoading == true
                            )
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
            )
        }
    }

    @Composable
    fun ViewModelObserver(
        viewModel: RegisterYayasanViewModel,
        navController: NavHostController?,
        context: Context,
    ) {
        val registerYayasanState = viewModel.stateRegisterYayasan

        when {
            registerYayasanState.isLoading -> {}
            registerYayasanState.data != null -> {
                val registerYayasanData = registerYayasanState.data

                LaunchedEffect(registerYayasanData) {
                    navController?.navigate(ScreenRouter.AuthLogin.route) {
                        popUpTo(ScreenRouter.Auth.route) {
                            inclusive = false
                        }
                    }
                }

                Toast.makeText(context, MessageUtils.MSG_SUCCESS_REGISTER, Toast.LENGTH_SHORT).show()
            }

            registerYayasanState.error.isNotEmpty() -> {
                val errorMessage = registerYayasanState.error
                Log.d(TAG, "ViewModelObserver: $errorMessage")
                Toast.makeText(context, MessageUtils.MSG_UNEXPECTED_ERROR, Toast.LENGTH_SHORT)
                    .show()
            }

            else -> {
                // Initial state or other cases
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterAccountYayasanScreenPreview() {
    RegisterAccountYayasanScreen(null, null).MainScreen(null)
}

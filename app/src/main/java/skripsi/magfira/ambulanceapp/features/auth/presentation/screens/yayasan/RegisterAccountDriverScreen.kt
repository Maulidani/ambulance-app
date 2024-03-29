package skripsi.magfira.ambulanceapp.features.auth.presentation.screens.yayasan

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Photo
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
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import skripsi.magfira.ambulanceapp.datastore.DataStorePreferences
import skripsi.magfira.ambulanceapp.features.auth.domain.model.request.RegisterCustomerRequest
import skripsi.magfira.ambulanceapp.features.auth.domain.model.request.RegisterDriverRequest
import skripsi.magfira.ambulanceapp.features.auth.presentation.view_models.AuthViewModel
import skripsi.magfira.ambulanceapp.features.common.presentation.components.AppBar
import skripsi.magfira.ambulanceapp.features.common.presentation.components.ButtonIcon
import skripsi.magfira.ambulanceapp.features.common.presentation.components.FileUpload
import skripsi.magfira.ambulanceapp.features.common.presentation.components.TextFieldForm
import skripsi.magfira.ambulanceapp.features.common.presentation.components.TextFieldPasswordForm
import skripsi.magfira.ambulanceapp.features.common.presentation.components.TextFieldProfile
import skripsi.magfira.ambulanceapp.navigation.ScreenRouter
import skripsi.magfira.ambulanceapp.util.InputValidation.containsNoSpaces
import skripsi.magfira.ambulanceapp.util.InputValidation.isValidEmailFormat
import skripsi.magfira.ambulanceapp.util.InputValidation.isValidPhoneFormat
import skripsi.magfira.ambulanceapp.util.MessageUtils
import skripsi.magfira.ambulanceapp.util.MessageUtils.MSG_INPUT_CONTAIN_SPACE
import skripsi.magfira.ambulanceapp.util.MessageUtils.MSG_INPUT_INVALID_EMAIL
import skripsi.magfira.ambulanceapp.util.MessageUtils.MSG_INPUT_INVALID_PHONE
import skripsi.magfira.ambulanceapp.util.MessageUtils.MSG_REQUIRED_FIELDS
import skripsi.magfira.ambulanceapp.util.NetworkUtils
import skripsi.magfira.ambulanceapp.util.requestStoragePermissions
import javax.inject.Inject

class RegisterAccountDriverScreen(
    private val viewModel: AuthViewModel?,
    private val navController: NavHostController?
) {
    private val TAG = "RegisterAccountDriverScreen"

    // Safe back
    private val NavHostController.canGoBack: Boolean
        get() = this.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED

    @Inject
    lateinit var dataStorePreferences: DataStorePreferences

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun MainScreen() {
        val context = LocalContext.current

        dataStorePreferences = DataStorePreferences(context)
        requestStoragePermissions(context)

        var uploadFile by rememberSaveable { mutableStateOf("Upload Foto Profil") }
        var name by rememberSaveable { mutableStateOf("") }
        var phone by rememberSaveable { mutableStateOf("") }
        var username by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }
        var confirmPassword by rememberSaveable { mutableStateOf("") }
        var passwordVisibility by rememberSaveable { mutableStateOf(false) }
        var confirmPasswordVisibility by rememberSaveable { mutableStateOf(false) }
        val keyboardController = LocalSoftwareKeyboardController.current

        var selectedImageUri by rememberSaveable {
            mutableStateOf<Uri?>(null)
        }
        val photoPickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri -> selectedImageUri = uri }
        )
//        val photoPickerLauncher = rememberLauncherForActivityResult(
//            contract = ActivityResultContracts.OpenDocument(),
//            onResult = { uri -> selectedImageUri = uri }
//        )

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
                    title = "Daftar Driver Ambulan",
                    iconBackClick = {
                        if (navController?.canGoBack == true) {
                            navController.popBackStack()
                        }
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
                            value = name,
                            onValueChange = { name = it },
                            label = "Nama Lengkap",
                            icon = Icons.Default.Person
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        TextFieldForm(
                            value = phone,
                            onValueChange = { phone = it },
                            label = "No. Telepon",
                            icon = Icons.Default.Phone
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        FileUpload(
                            label = uploadFile,
                            icon = Icons.Default.Photo,
                            selectedImage = {
                                selectedImageUri
                            },
                            onUploadClick = {
                                photoPickerLauncher.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
//                            photoPickerLauncher.launch(arrayOf("image/*"))
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
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
                                        name.isEmpty() ||
                                        phone.isEmpty() ||
                                        password.isEmpty() ||
                                        selectedImageUri == null
                                    ) {
                                        Toast.makeText(
                                            context,
                                            MSG_REQUIRED_FIELDS,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else if (
                                        !containsNoSpaces(name) ||
                                        !containsNoSpaces(phone) ||
                                        !containsNoSpaces(username) ||
                                        !containsNoSpaces(password)
                                    ) {
                                        Toast.makeText(
                                            context,
                                            MSG_INPUT_CONTAIN_SPACE,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else if (!isValidPhoneFormat(phone)) {
                                        Toast.makeText(
                                            context,
                                            MSG_INPUT_INVALID_PHONE,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else if (password.lowercase() != confirmPassword.lowercase()) {
                                        Toast.makeText(
                                            context,
                                            MessageUtils.MSG_PASSWORDS_NOT_MATCH,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {

                                        lateinit var partMap: Map<String, RequestBody>
                                        lateinit var imagePart: MultipartBody.Part
                                        try {
                                            partMap = RegisterDriverRequest(
                                                NetworkUtils.ROLE_DRIVER,
                                                NetworkUtils.ACCOUNT_ON,
                                                name,
                                                username,
                                                password,
                                                confirmPassword,
                                                selectedImageUri!!,
                                                context
                                            ).first
                                            imagePart = RegisterDriverRequest(
                                                NetworkUtils.ROLE_DRIVER,
                                                NetworkUtils.ACCOUNT_ON,
                                                name,
                                                username,
                                                password,
                                                confirmPassword,
                                                selectedImageUri!!,
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
                                                        viewModel?.createDriver(
                                                            partMap,
                                                            imagePart
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
                                                "MainScreen: RegisterDriverRequest: ${e.localizedMessage}"
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
                                text = "Buat akun",
                                textColor = Color.White,
                                backgroundColor = MaterialTheme.colorScheme.primary,
                            )
                        }
                    }
                }
            }
        }

        if (viewModel != null) {

            // Check Login
            LaunchedEffect(true) {
                val isLogin = dataStorePreferences.getUserIsLogin.first()
                val token = dataStorePreferences.getUserToken.first()

                if (isLogin == true) {
                    viewModel.token = token ?: ""

                } else {
                    // Not Login
                    Toast.makeText(context, MessageUtils.MSG_UNAUTHORIZED, Toast.LENGTH_SHORT)
                        .show()

                    // Unauthorized
                    navController?.navigate(ScreenRouter.Auth.route) {
                        popUpTo(ScreenRouter.Customer.route) {
                            inclusive = false
                        }
                    }
                }
            }

            // Observe ViewModel
            ViewModelObserver(
                viewModel,
                navController,
                context,
            )
        }

    }

    @Composable
    fun ViewModelObserver(
        viewModel: AuthViewModel,
        navController: NavHostController?,
        context: Context,
    ) {
        val createDriverState = viewModel.stateCreateDriver

        when {
            createDriverState.isLoading -> {}
            createDriverState.data != null -> {
                val createDriverData = createDriverState.data

                LaunchedEffect(createDriverState) {
                    if (navController?.canGoBack == true) {
                        navController.popBackStack()
                    }

                    Toast.makeText(context, MessageUtils.MSG_SUCCESS_REGISTER, Toast.LENGTH_SHORT).show()
                }

            }

            createDriverState.error.isNotEmpty() -> {
                val errorMessage = createDriverState.error
                Log.d(TAG, "ViewModelObserver: $errorMessage")

                LaunchedEffect(createDriverState) {
                    Toast.makeText(context, MessageUtils.MSG_UNEXPECTED_ERROR, Toast.LENGTH_SHORT).show()
                }
            }

            else -> {
                // Initial state or other cases
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun RegisterAccountDriverScreenPreview() {
    RegisterAccountDriverScreen(null, null).MainScreen()
}

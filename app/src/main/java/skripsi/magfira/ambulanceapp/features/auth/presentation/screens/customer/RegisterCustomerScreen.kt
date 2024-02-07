package skripsi.magfira.ambulanceapp.features.auth.presentation.screens.customer

import android.net.Uri
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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import skripsi.magfira.ambulanceapp.features.auth.presentation.view_models.AuthViewModel
import skripsi.magfira.ambulanceapp.features.common.presentation.components.AppBar
import skripsi.magfira.ambulanceapp.features.common.presentation.components.ButtonIcon
import skripsi.magfira.ambulanceapp.features.common.presentation.components.FileUpload
import skripsi.magfira.ambulanceapp.features.common.presentation.components.TextFieldForm
import skripsi.magfira.ambulanceapp.navigation.ScreenRouter
import skripsi.magfira.ambulanceapp.util.InputValidation.containsNoSpaces
import skripsi.magfira.ambulanceapp.util.InputValidation.isValidEmailFormat
import skripsi.magfira.ambulanceapp.util.InputValidation.isValidPhoneFormat
import skripsi.magfira.ambulanceapp.util.MessageUtils.MSG_INPUT_CONTAIN_SPACE
import skripsi.magfira.ambulanceapp.util.MessageUtils.MSG_INPUT_INVALID_EMAIL
import skripsi.magfira.ambulanceapp.util.MessageUtils.MSG_INPUT_INVALID_PHONE
import skripsi.magfira.ambulanceapp.util.MessageUtils.MSG_REQUIRED_FIELDS
import skripsi.magfira.ambulanceapp.util.requestStoragePermissions

class RegisterCustomerScreen(
    private val viewModel: AuthViewModel?,
    private val navController: NavHostController?
) {
    private val TAG = "RegisterCustomerScreen"

    // Safe back
    private val NavHostController.canGoBack : Boolean
        get() = this.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED

    @Composable
    fun MainScreen() {
        val context = LocalContext.current
        requestStoragePermissions(context)

        var name by rememberSaveable { mutableStateOf("") }
        var email by rememberSaveable { mutableStateOf("") }
        var phone by rememberSaveable { mutableStateOf("") }
        var uploadFile by rememberSaveable { mutableStateOf("Upload Foto Profil") }

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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        ) {
            AppBar(
                title = "Daftar Customer",
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
                        value = email,
                        onValueChange = { email = it },
                        label = "Email",
                        icon = Icons.Default.Email
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
                                    email.isEmpty() ||
                                    phone.isEmpty() ||
                                    selectedImageUri == null
                                ) {
                                    Toast.makeText(context, MSG_REQUIRED_FIELDS, Toast.LENGTH_SHORT).show()
                                } else if (
                                    !containsNoSpaces(name) ||
                                    !containsNoSpaces(email) ||
                                    !containsNoSpaces(phone)
                                ) {
                                    Toast.makeText(context, MSG_INPUT_CONTAIN_SPACE, Toast.LENGTH_SHORT).show()
                                } else if (!isValidEmailFormat(email)) {
                                    Toast.makeText(context, MSG_INPUT_INVALID_EMAIL, Toast.LENGTH_SHORT).show()
                                } else if (!isValidPhoneFormat(phone)) {
                                    Toast.makeText(context, MSG_INPUT_INVALID_PHONE, Toast.LENGTH_SHORT).show()
                                } else {
                                    val encodedUriString: String = Uri.encode(selectedImageUri.toString())
                                    navController?.navigate(
                                        ScreenRouter.AuthRegisterAccountCustomer.routeWithArguments(name,email,phone,encodedUriString)
                                    )
                                }
                            },
                            icon = Icons.Default.ArrowForwardIos,
                            text = "Lanjut",
                            textColor = Color.White,
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
fun RegisterCustomerScreenPreview() {
    RegisterCustomerScreen(null, null).MainScreen()
}

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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import skripsi.magfira.ambulanceapp.features.auth.presentation.view_models.AuthViewModel
import skripsi.magfira.ambulanceapp.features.common.presentation.components.AppBar
import skripsi.magfira.ambulanceapp.features.common.presentation.components.FileUpload
import skripsi.magfira.ambulanceapp.features.common.presentation.components.IconButton
import skripsi.magfira.ambulanceapp.features.common.presentation.components.TextFieldForm

class RegisterCustomerScreen(
    private val viewModel: AuthViewModel?,
    private val navController: NavHostController?
) {
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
                    val name = remember { mutableStateOf("") }
                    val email = remember { mutableStateOf("") }
                    val phone = remember { mutableStateOf("") }
                    val uploadFile = remember { mutableStateOf("Upload Foto Profil") }

                    Text(
                        text = "Lengkapi data berikut",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    TextFieldForm(
                        value = name.value,
                        onValueChange = { name.value = it },
                        label = "Nama Lengkap",
                        icon = Icons.Default.Person
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TextFieldForm(
                        value = email.value,
                        onValueChange = { email.value = it },
                        label = "Email",
                        icon = Icons.Default.Email
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TextFieldForm(
                        value = phone.value,
                        onValueChange = { phone.value = it },
                        label = "No. Telepon",
                        icon = Icons.Default.Phone
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    FileUpload(
                        label = uploadFile.value,
                        icon = Icons.Default.Photo,
                        onUploadClick = {
                            //
                        }
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
                            text = "Lanjut",
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

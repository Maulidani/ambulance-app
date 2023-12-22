package skripsi.magfira.ambulanceapp.presentation.auth.screens.yayasan

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import skripsi.magfira.ambulanceapp.presentation.ScreenRoute
import skripsi.magfira.ambulanceapp.presentation.auth.view_model.AuthViewModel

class RegisterYayasanScreen(
    private val viewModel: AuthViewModel?,
    private val navController: NavHostController?
) {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .clickable {
                                navController?.popBackStack()
                            }
                    )
                    Text(
                        text = "Daftar Yayasan",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
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
                        .padding(all = 24.dp)
                ) {
                    val name = remember { mutableStateOf("") }
                    val email = remember { mutableStateOf("") }
                    val phone = remember { mutableStateOf("") }
                    val address = remember { mutableStateOf("") }
                    val textUploadFile = remember { mutableStateOf("Surat Izin Pendirian Yayasan") }

                    Text(
                        text = "Lengkapi data berikut",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    TextField(
                        value = name.value,
                        onValueChange = { name.value = it },
                        label = { Text("Nama Yayasan") },
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
                                contentDescription = "Name",
                                tint = Color.Gray,
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        singleLine = true
                    )
                    TextField(
                        value = email.value,
                        onValueChange = { email.value = it },
                        label = { Text("Email") },
                        shape = RoundedCornerShape(24.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = MaterialTheme.colorScheme.background,
                            focusedBorderColor = Color.Transparent,
                            focusedLabelColor = Color.Gray,
                            unfocusedBorderColor = Color.Transparent,
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "Email",
                                tint = Color.Gray,
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        singleLine = true
                    )
                    TextField(
                        value = phone.value,
                        onValueChange = { phone.value = it },
                        label = { Text("No. Telepon") },
                        shape = RoundedCornerShape(24.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = MaterialTheme.colorScheme.background,
                            focusedBorderColor = Color.Transparent,
                            focusedLabelColor = Color.Gray,
                            unfocusedBorderColor = Color.Transparent,
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = "Phone",
                                tint = Color.Gray,
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        singleLine = true
                    )
                    TextField(
                        value = address.value,
                        onValueChange = { address.value = it },
                        label = { Text("Alamat") },
                        shape = RoundedCornerShape(24.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = MaterialTheme.colorScheme.background,
                            focusedBorderColor = Color.Transparent,
                            focusedLabelColor = Color.Gray,
                            unfocusedBorderColor = Color.Transparent,
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = "Location",
                                tint = Color.Gray,
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        maxLines = 3
                    )
                    Surface(
                        modifier = Modifier
                            .padding(top = 16.dp),
                        shape = RoundedCornerShape(24.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .background(MaterialTheme.colorScheme.background),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = textUploadFile.value)
                            Spacer(modifier = Modifier.size(12.dp))
                            Image(
                                modifier = Modifier
                                    .size(width = 80.dp, height = 80.dp)
                                    .clickable {
                                        //
                                    },
                                imageVector = Icons.Default.UploadFile,
                                contentDescription = "Upload File",
                                contentScale = ContentScale.Fit,
                                colorFilter = ColorFilter.tint(Color.Gray)
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Button(
                            onClick = {
                                navController?.navigate(ScreenRoute.AuthRegisterAccountYayasan.route)
                            },
                            shape = RoundedCornerShape(24.dp),
                        ) {
                            Text(
                                text = "Lanjut",
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
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun RegisterYayasanScreenPreview() {
    RegisterYayasanScreen(null, null).MainScreen()
}

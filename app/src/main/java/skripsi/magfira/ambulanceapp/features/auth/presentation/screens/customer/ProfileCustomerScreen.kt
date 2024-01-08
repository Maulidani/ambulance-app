package skripsi.magfira.ambulanceapp.features.auth.presentation.screens.customer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import skripsi.magfira.ambulanceapp.features.common.presentation.components.AppBar
import skripsi.magfira.ambulanceapp.features.common.presentation.components.ButtonIcon
import skripsi.magfira.ambulanceapp.features.common.presentation.components.ImageView
import skripsi.magfira.ambulanceapp.features.common.presentation.components.TextFieldProfile
import skripsi.magfira.ambulanceapp.navigation.ScreenRouter
import skripsi.magfira.ambulanceapp.util.requestStoragePermissions

class ProfileCustomerScreen(
    private val viewModel: Any?,
    private val navController: NavHostController?
) {
    @Composable
    fun MainScreen() {
        val context = LocalContext.current
        requestStoragePermissions(context)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        ) {
            AppBar(
                title = "Pengaturan",
                iconBackClick = {
                    navController?.popBackStack()
                }
            )
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                var photo by remember { mutableStateOf("") }
                var name by remember { mutableStateOf("Name") }
                var email by remember { mutableStateOf("Email") }
                var phone by remember { mutableStateOf("Phone") }
                var address by remember { mutableStateOf("Address") }
                Text(
                    modifier = Modifier,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    text = "Profil",
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    ImageView(
                        source = photo,
                        editable = true,
                        imageClicked = {
                            //
                        },
                        iconEditClicked = {
                            //
                        }
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    TextFieldProfile(
                        value = name,
                        icon = Icons.Default.Person,
                        iconEnd = Icons.Default.Edit,
                        iconEndClicked = {
                            //
                        }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldProfile(
                    value = email,
                    icon = Icons.Default.Email,
                    iconEnd = Icons.Default.Edit,
                    iconEndClicked = {
                        //
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldProfile(
                    value = phone,
                    icon = Icons.Default.Phone,
                    iconEnd = Icons.Default.Edit,
                    iconEndClicked = {
                        //
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextFieldProfile(
                    value = address,
                    icon = Icons.Default.LocationOn,
                    iconEnd = Icons.Default.Edit,
                    iconEndClicked = {
                        //
                    }
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    modifier = Modifier,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center,
                    text = "Akun",
                )
                Spacer(modifier = Modifier.height(24.dp))
                TextFieldProfile(
                    value = "Akun",
                    icon = Icons.Default.Person,
                    iconEnd = Icons.Default.ArrowForwardIos,
                    iconEndClicked = {
                        navController?.navigate(ScreenRouter.CustomerAccount.route)
                    }
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    modifier = Modifier,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center,
                    text = "Lainnya",
                )
                Spacer(modifier = Modifier.height(24.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    ButtonIcon(
                        modifier = Modifier,
                        onClick = {
                            navController?.navigate(ScreenRouter.Auth.route)
                        },
                        icon = Icons.Default.Logout,
                        text = "Keluar",
                        textColor = Color.White,
                        backgroundColor = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileCustomerScreenPreview() {
    ProfileCustomerScreen(null, null).MainScreen()
}

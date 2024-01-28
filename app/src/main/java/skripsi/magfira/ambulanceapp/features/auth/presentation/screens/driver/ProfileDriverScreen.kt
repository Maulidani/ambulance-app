package skripsi.magfira.ambulanceapp.features.auth.presentation.screens.driver

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import skripsi.magfira.ambulanceapp.datastore.DataStorePreferences
import skripsi.magfira.ambulanceapp.features.common.presentation.components.AppBar
import skripsi.magfira.ambulanceapp.features.common.presentation.components.ButtonIcon
import skripsi.magfira.ambulanceapp.features.common.presentation.components.ImageView
import skripsi.magfira.ambulanceapp.features.common.presentation.components.TextFieldProfile
import skripsi.magfira.ambulanceapp.navigation.ScreenRouter
import skripsi.magfira.ambulanceapp.util.MessageUtils
import skripsi.magfira.ambulanceapp.util.requestStoragePermissions
import javax.inject.Inject

class ProfileDriverScreen(
    private val viewModel: Any?,
    private val navController: NavHostController?
) {
    @Inject
    lateinit var dataStorePreferences: DataStorePreferences

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun MainScreen() {
        val context = LocalContext.current

        dataStorePreferences = DataStorePreferences(context)
        requestStoragePermissions(context)

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
                            context = context,
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
                        value = phone,
                        icon = Icons.Default.Phone,
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
                            navController?.navigate(ScreenRouter.DriverAccount.route)
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
                                // Showing confirmation
                                scope.launch {
                                    val result = snackbarHostState
                                        .showSnackbar(
                                            message = MessageUtils.MSG_LOGOUT_CONFIRM,
                                            actionLabel = "Yes",
                                            // Defaults to SnackbarDuration.Short
                                            duration = SnackbarDuration.Long
                                        )
                                    when (result) {
                                        SnackbarResult.ActionPerformed -> {
                                            // Delete login data, then back to Login
                                            dataStorePreferences.saveLogin(false)
                                            navController?.navigate(ScreenRouter.Auth.route) {
                                                popUpTo(ScreenRouter.Customer.route) {
                                                    inclusive = false
                                                }
                                            }
                                        }

                                        SnackbarResult.Dismissed -> {
                                            /* Handle snackbar dismissed */
                                        }
                                    }
                                }
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
}

@Preview(showBackground = true)
@Composable
fun ProfileDriverScreenPreview() {
    ProfileDriverScreen(null, null).MainScreen()
}

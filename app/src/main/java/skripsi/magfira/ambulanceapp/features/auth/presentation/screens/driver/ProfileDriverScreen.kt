package skripsi.magfira.ambulanceapp.features.auth.presentation.screens.driver

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import skripsi.magfira.ambulanceapp.datastore.DataStorePreferences
import skripsi.magfira.ambulanceapp.features.auth.domain.model.response.ShowUser
import skripsi.magfira.ambulanceapp.features.auth.presentation.view_models.AuthViewModel
import skripsi.magfira.ambulanceapp.features.common.presentation.components.AppBar
import skripsi.magfira.ambulanceapp.features.common.presentation.components.ButtonIcon
import skripsi.magfira.ambulanceapp.features.common.presentation.components.ImageView
import skripsi.magfira.ambulanceapp.features.common.presentation.components.TextFieldProfile
import skripsi.magfira.ambulanceapp.navigation.ScreenRouter
import skripsi.magfira.ambulanceapp.util.MessageUtils
import skripsi.magfira.ambulanceapp.util.NetworkUtils
import skripsi.magfira.ambulanceapp.util.requestStoragePermissions
import javax.inject.Inject

class ProfileDriverScreen(
    private val viewModel: AuthViewModel?,
    private val navController: NavHostController?
) {
    private val TAG = "ProfileDriverScreen"

    // Safe back
    private val NavHostController.canGoBack: Boolean
        get() = this.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED

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

        var dataProfile by remember { mutableStateOf<ShowUser?>(null) }
        var name by remember { mutableStateOf("") }
        var phone by remember { mutableStateOf("") }
        var photo by remember { mutableStateOf("") }
        var field by remember { mutableStateOf("") }

        LaunchedEffect(dataProfile ?: true) {
            if (dataStorePreferences.getUserIsLogin.first() == false) {
                navController?.navigate(ScreenRouter.Driver.route) {
                    popUpTo(ScreenRouter.Auth.route) {
                        inclusive = false
                    }
                }
            } else {
                Log.d(TAG, "dataStore: Token: ${dataStorePreferences.getUserToken.first()!!}")
                Log.d(TAG, "dataStore: userID: ${dataStorePreferences.getUserId.first()!!}")

                // Add token to viewmodel
                viewModel?.token = dataStorePreferences.getUserToken.first()!!
                viewModel?.userId = dataStorePreferences.getUserId.first()!!

                // Get data profile
                viewModel?.getProfile()

                // Assign data to variable
                photo = "${NetworkUtils.BASE_URL_FILE}${dataProfile?.data?.foto_profil}"
                name = dataProfile?.data?.name ?: "-"
                phone = dataProfile?.data?.no_telp ?: "-"

            }
        }

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
                        if (navController?.canGoBack == true) {
                            navController.popBackStack()
                        }
                    }
                )
                Spacer(modifier = Modifier.height(24.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
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
                                scope.launch {
                                    snackbarHostState
                                        .showSnackbar(
                                            message = "Cannot edit photo, ${MessageUtils.MSG_COOMING_SOON}",
                                            duration = SnackbarDuration.Short
                                        )
                                }
                            }
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        TextFieldProfile(
                            value = name,
                            icon = Icons.Default.Person,
                            iconEnd = Icons.Default.Edit,
                            iconEndClicked = {
                                scope.launch {
                                    snackbarHostState
                                        .showSnackbar(
                                            message = "Cannot edit name, ${MessageUtils.MSG_COOMING_SOON}",
                                            duration = SnackbarDuration.Short
                                        )
                                }
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    TextFieldProfile(
                        value = phone,
                        icon = Icons.Default.Phone,
                        iconEnd = Icons.Default.Edit,
                        iconEndClicked = {
                            scope.launch {
                                snackbarHostState
                                    .showSnackbar(
                                        message = "Cannot edit phone, ${MessageUtils.MSG_COOMING_SOON}",
                                        duration = SnackbarDuration.Short
                                    )
                            }
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
//                            navController?.navigate(ScreenRouter.DriverAccount.route)
                            scope.launch {
                                snackbarHostState
                                    .showSnackbar(
                                        message = "Cannot edit account, ${MessageUtils.MSG_COOMING_SOON}",
                                        duration = SnackbarDuration.Short
                                    )
                            }
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
                                            // Logout
                                            viewModel?.logout()
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

        // Observe ViewModel
        viewModel?.let {
            ViewModelObserver(
                it,
                context,
                scope,
                snackbarHostState,
                onDataProfileReceived = { profile ->
                    dataProfile = profile
                }
            )
        }

    }

    @Composable
    fun ViewModelObserver(
        viewModel: AuthViewModel,
        context: Context,
        scope: CoroutineScope,
        snackbarHostState: SnackbarHostState,
        onDataProfileReceived: (ShowUser) -> Unit
    ) {
        // Data update profile
        val updateProfileState = viewModel.stateUpdateProfile

        // Data get profile
        val getProfileState = viewModel.stateGetProfile

        // Data logout
        val logoutState = viewModel.stateLogout

        when {
            updateProfileState.isLoading -> {

            }

            updateProfileState.data != null -> {
                val updateData = updateProfileState.data
                Log.d(TAG, "ViewModelObserver: $updateData")

                LaunchedEffect(updateData) {
                    Toast.makeText(context, MessageUtils.MSG_SUCCESS_UPDATE_PROFILE, Toast.LENGTH_SHORT).show()
                }
            }

            updateProfileState.error.isNotEmpty() -> {
                val errorMessage = updateProfileState.error
                Log.d(TAG, "ViewModelObserver: $errorMessage")

                LaunchedEffect(errorMessage) {
                    scope.launch {
                        snackbarHostState
                            .showSnackbar(
                                message = MessageUtils.MSG_SERVER_ERROR,
                                duration = SnackbarDuration.Short
                            )
                    }
                }

            }

            else -> {
                // Initial state or other cases
            }
        }

        when {
            getProfileState.isLoading -> {

            }

            getProfileState.data != null -> {
                var getDataProfile = getProfileState.data
                Log.d(TAG, "ViewModelObserver: $getDataProfile")

                LaunchedEffect(getDataProfile) {
                    getDataProfile?.let {
                        onDataProfileReceived(it) // Call the callback with received profile data
                    }
                }
            }

            getProfileState.error.isNotEmpty() -> {
                val errorMessage = getProfileState.error
                Log.d(TAG, "ViewModelObserver: $errorMessage")

                LaunchedEffect(errorMessage) {
                    scope.launch {
                        snackbarHostState
                            .showSnackbar(
                                message = MessageUtils.MSG_SERVER_ERROR,
                                duration = SnackbarDuration.Short
                            )
                    }
                }

            }

            else -> {
                // Initial state or other cases
            }
        }

        when {
            logoutState.isLoading -> {

            }

            logoutState.data != null -> {
                var getDataLogout = logoutState.data
                Log.d(TAG, "ViewModelObserver: $getDataLogout")

                LaunchedEffect(getDataLogout) {
                    // Delete login data, then back to Login
                    dataStorePreferences.saveLogin(false)
                    navController?.navigate(ScreenRouter.Auth.route) {
                        popUpTo(ScreenRouter.Driver.route) {
                            inclusive = false
                        }
                    }
                }
            }

            logoutState.error.isNotEmpty() -> {
                val errorMessage = getProfileState.error
                Log.d(TAG, "ViewModelObserver: $errorMessage")

                LaunchedEffect(errorMessage) {
                    scope.launch {
                        snackbarHostState
                            .showSnackbar(
                                message = MessageUtils.MSG_SERVER_ERROR,
                                duration = SnackbarDuration.Short
                            )
                    }
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
fun ProfileDriverScreenPreview() {
    ProfileDriverScreen(null, null).MainScreen()
}

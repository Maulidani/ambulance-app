package skripsi.magfira.ambulanceapp.features.auth.presentation.screens.yayasan

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import com.pusher.client.connection.ConnectionEventListener
import com.pusher.client.connection.ConnectionState
import com.pusher.client.connection.ConnectionStateChange
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import skripsi.magfira.ambulanceapp.R
import skripsi.magfira.ambulanceapp.datastore.DataStorePreferences
import skripsi.magfira.ambulanceapp.features.auth.presentation.view_models.AuthViewModel
import skripsi.magfira.ambulanceapp.features.common.presentation.components.AppBar
import skripsi.magfira.ambulanceapp.features.common.presentation.components.ImageView
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.DriversData
import skripsi.magfira.ambulanceapp.features.order.presentation.view_models.OrderViewModel
import skripsi.magfira.ambulanceapp.navigation.ScreenRouter
import skripsi.magfira.ambulanceapp.util.MessageUtils
import skripsi.magfira.ambulanceapp.util.NetworkUtils
import javax.inject.Inject

class DriversYayasanScreen(
    private val viewModel: AuthViewModel?,
    private val navController: NavHostController?
) {
    private val TAG = "DriversYayasanScreen"

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

        var drivers by remember { mutableStateOf(listOf<DriversData>()) }

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    modifier = Modifier.padding(16.dp),
                    onClick = {
                        navController?.navigate(ScreenRouter.YayasanCreateDriver.route)
                    },
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add"
                    )
                }
            },
        ) { _ ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            ) {
                AppBar(
                    title = "Driver",
                    iconBackClick = {
                        if (navController?.canGoBack == true) {
                            navController.popBackStack()
                        }
                    }
                )
                Spacer(modifier = Modifier.height(24.dp))
                LazyColumn() {
                    itemsIndexed(
                        drivers
                    ) { index, item ->

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Surface(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        navController?.navigate(
                                            ScreenRouter.YayasanDriverAccount.routeWithArguments(
                                                item.id.toString()
                                            )
                                        )
                                    },
                                shape = RoundedCornerShape(24.dp),
                                border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                        .background(color = MaterialTheme.colorScheme.background),
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.Top,
                                        horizontalArrangement = Arrangement.Start,
                                    ) {
                                        Row(
                                            modifier = Modifier.weight(1F),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Start,
                                        ) {
                                            ImageView(
                                                context = context,
                                                source = "${NetworkUtils.BASE_URL_FILE}${item.foto_profil}",
                                                editable = false,
                                                imageClicked = {
                                                    //
                                                },
                                                iconEditClicked = {
                                                    //
                                                }
                                            )
                                            Spacer(modifier = Modifier.width(12.dp))
                                            Column(
                                                modifier = Modifier
                                                    .weight(1F),
                                            ) {
                                                Text(
                                                    text = item.name,
                                                    maxLines = 1,
                                                    overflow = TextOverflow.Ellipsis,
                                                )
                                                Spacer(modifier = Modifier.height(8.dp))
                                                Text(
                                                    text = item.no_telp.toString(),
                                                    maxLines = 1,
                                                    overflow = TextOverflow.Ellipsis,
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))

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

                // Get driveres on
                LaunchedEffect(drivers) {
                    viewModel.driversYayasan()
                }

                // Observe ViewModel
                ViewModelObserver(
                    viewModel,
                    onDriversUpdated = { updatedDrivers ->
                        drivers = updatedDrivers
                    },
                    context,
                )
            }

        }

    }


    @Composable
    fun ViewModelObserver(
        viewModel: AuthViewModel,
        onDriversUpdated: (List<DriversData>) -> Unit,
        context: Context,
    ) {
        val driversState = viewModel.stateDriversYayasan

        when {
            driversState.isLoading -> {}

            driversState.data != null -> {
                val driversData = driversState.data
                onDriversUpdated(driversData!!.data)
            }

            driversState.error.isNotEmpty() -> {
                val errorMessage = driversState.error
                Log.d(TAG, "ViewModelObserver: $errorMessage")
            }

            else -> {
                // Initial state or other cases
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun DriversYayasanScreenPreview() {
    DriversYayasanScreen(null, null).MainScreen()
}

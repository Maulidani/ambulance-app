package skripsi.magfira.ambulanceapp.features.order.presentation.screens.driver

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pusher.client.Pusher
import com.pusher.client.connection.ConnectionEventListener
import com.pusher.client.connection.ConnectionState
import com.pusher.client.connection.ConnectionStateChange
import skripsi.magfira.ambulanceapp.datastore.DataStorePreferences
import skripsi.magfira.ambulanceapp.features.common.presentation.components.AppBarHome
import skripsi.magfira.ambulanceapp.features.order.presentation.components.CardActivationDriver
import skripsi.magfira.ambulanceapp.features.order.presentation.components.CardOrderingDriver
import skripsi.magfira.ambulanceapp.features.order.presentation.components.MapViewCustomer
import skripsi.magfira.ambulanceapp.features.order.presentation.components.MapViewDriver
import skripsi.magfira.ambulanceapp.features.order.presentation.view_models.OrderViewModel
import skripsi.magfira.ambulanceapp.navigation.ScreenRouter
import skripsi.magfira.ambulanceapp.util.requestAllPermissions
import javax.inject.Inject

class HomeDriverScreen(
    private val viewModel: OrderViewModel?,
    private val navController: NavHostController?
) {
    private val TAG = "HomeDriverScreen"

    @Inject
    lateinit var dataStorePreferences: DataStorePreferences

    @Inject
    lateinit var pusher: Pusher

    @Composable
    fun MainScreen() {
        val context = LocalContext.current
        requestAllPermissions(context)

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            MapViewDriver()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
            ) {
                AppBarHome(
                    iconSettingClick = {
                        navController?.navigate(ScreenRouter.DriverProfile.route)
                    }
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(Color.Transparent),
                )
                CardActivationDriver()
                Spacer(modifier = Modifier.height(16.dp))
                CardOrderingDriver()
            }
        }
    }

    fun connectPusher() {
        pusher.connect(object : ConnectionEventListener {
            override fun onConnectionStateChange(change: ConnectionStateChange) {
                Log.d(TAG, "Pusher: State changed from ${change.previousState} to ${change.currentState}")
            }

            override fun onError(
                message: String,
                code: String,
                e: Exception
            ) {
                Log.d(TAG, "Pusher: There was a problem connecting! code ($code), message ($message), exception($e)")
            }
        }, ConnectionState.ALL)

        val channel = pusher.subscribe("my-channel")
        channel.bind("my-event") { event ->
            Log.d(TAG,"Pusher: Received event with data: $event")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeDriverScreenPreview() {
    HomeDriverScreen(null, null).MainScreen()
}
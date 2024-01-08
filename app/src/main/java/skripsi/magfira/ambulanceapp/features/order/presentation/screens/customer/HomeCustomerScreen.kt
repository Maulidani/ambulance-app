package skripsi.magfira.ambulanceapp.features.order.presentation.screens.customer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import skripsi.magfira.ambulanceapp.features.common.presentation.components.AppBarHome
import skripsi.magfira.ambulanceapp.features.common.presentation.components.MapView
import skripsi.magfira.ambulanceapp.features.order.presentation.components.CardMainCustomer
import skripsi.magfira.ambulanceapp.navigation.ScreenRouter
import skripsi.magfira.ambulanceapp.util.requestAllPermissions

class HomeCustomerScreen(
    private val viewModel: Any?,
    private val navController: NavHostController?
) {
    @Composable
    fun MainScreen() {
        val context = LocalContext.current
        requestAllPermissions(context)

        val ambulanceActive = 999

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            MapView()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
            ) {
                AppBarHome(
                    iconSettingClick = {
                        navController?.navigate(ScreenRouter.CustomerProfile.route)
                    }
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(Color.Transparent),
                )
                CardMainCustomer(ambulanceActive = ambulanceActive)
//                CardEditLocationCustomer(iconBackClick = {})
//                CardDetailOrderCustomer()
//                CardOrderingCustomer("bla", "bla")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeCustomerScreenPreview() {
    HomeCustomerScreen(null, null).MainScreen()
}
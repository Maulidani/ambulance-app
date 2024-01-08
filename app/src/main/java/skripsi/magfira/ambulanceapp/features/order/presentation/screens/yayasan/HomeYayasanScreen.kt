package skripsi.magfira.ambulanceapp.features.order.presentation.screens.yayasan

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
import skripsi.magfira.ambulanceapp.features.common.presentation.components.AppBarHome
import skripsi.magfira.ambulanceapp.features.order.presentation.components.CardActivationDriver
import skripsi.magfira.ambulanceapp.features.order.presentation.components.CardOrderingDriver
import skripsi.magfira.ambulanceapp.navigation.ScreenRouter
import skripsi.magfira.ambulanceapp.util.requestAllPermissions

class HomeYayasanScreen(
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
//
//            Content View (MapView)
//
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
            ) {
                AppBarHome(
                    iconSettingClick = {
                        navController?.navigate(ScreenRouter.YayasanProfile.route)
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
}

@Preview(showBackground = true)
@Composable
fun HomeYayasanScreenPreview() {
    HomeYayasanScreen(null, null).MainScreen()
}
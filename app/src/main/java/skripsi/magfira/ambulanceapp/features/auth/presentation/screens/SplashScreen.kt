package skripsi.magfira.ambulanceapp.features.auth.presentation.screens

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import skripsi.magfira.ambulanceapp.R
import skripsi.magfira.ambulanceapp.features.auth.presentation.view_models.LoginViewModel
import skripsi.magfira.ambulanceapp.features.common.presentation.components.LocationPermissionTextProvider
import skripsi.magfira.ambulanceapp.features.common.presentation.components.PermissionDialog
import skripsi.magfira.ambulanceapp.features.common.presentation.components.ReadExternalStoragePermissionTextProvider
import skripsi.magfira.ambulanceapp.features.common.presentation.components.ReadMediaImagePermissionTextProvider
import skripsi.magfira.ambulanceapp.features.common.presentation.view_models.PermissionViewModel
import skripsi.magfira.ambulanceapp.navigation.ScreenRouter

class SplashScreen(
    private val splashScreenViewModel: LoginViewModel?,
    private val navController: NavHostController?
) {
    @Composable
    fun MainScreen() {
        val loginState = splashScreenViewModel?.stateLogin

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                val logo = painterResource(id = R.drawable.logo_text)
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 124.dp),
                    contentScale = ContentScale.Fit,
                    painter = logo,
                    contentDescription = logo.toString(),
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 16.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    text = "Versi aplikasi",
                )
            }
        }

        LaunchedEffect(true) {
            delay(2000)
            navController?.navigate(ScreenRouter.AuthLogin.route) {
                popUpTo(ScreenRouter.Auth.route) {
                    inclusive = false
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(null, null)
}

package skripsi.magfira.ambulanceapp.features.auth.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import skripsi.magfira.ambulanceapp.R
import skripsi.magfira.ambulanceapp.features.auth.presentation.view_models.AuthViewModel
import skripsi.magfira.ambulanceapp.navigation.ScreenRouter

class SplashScreen(
    private val viewModel: AuthViewModel?,
    private val navController: NavHostController?
) {
    @Composable
    fun MainScreen() {
        val authState = viewModel?.stateLogin?.collectAsState()
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
                    inclusive = true
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

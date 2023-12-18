package skripsi.magfira.ambulanceapp.presentation.auth.screens

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
import skripsi.magfira.ambulanceapp.presentation.ScreenRoute
import skripsi.magfira.ambulanceapp.presentation.auth.view_model.AuthViewModel

@Composable
fun SplashScreen(
    viewModel: AuthViewModel?,
    navController: NavHostController?
) {
    val authState = viewModel?.stateLogin?.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.logo_text),
                contentDescription = "logo_text",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 124.dp),
                contentScale = ContentScale.Fit
            )
            Text(
                text = "Versi aplikasi",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

    LaunchedEffect(true) {
        delay(2000)
        navController?.navigate(ScreenRoute.AuthLogin.route) {
            popUpTo("auth") {
                inclusive = true
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(null, null)

}

package skripsi.magfira.ambulanceapp.presentation.auth.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import skripsi.magfira.ambulanceapp.presentation.auth.view_model.AuthViewModel

class RegisterYayasanScreen(
    private val viewModel: AuthViewModel?,
    private val navController: NavHostController?
) {
    @Composable
    fun MainScreen(){

    }

}

@Preview(showBackground = true)
@Composable
fun RegisterYayasanScreenPreview() {
    RegisterYayasanScreen(null, null).MainScreen()
}

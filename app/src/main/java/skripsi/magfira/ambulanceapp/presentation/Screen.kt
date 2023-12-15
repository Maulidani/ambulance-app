package skripsi.magfira.ambulanceapp.presentation

sealed class Screen(val route: String) {
    object Auth: Screen("auth")
    object AuthSplashScreen: Screen("auth/splash-screen")
    object AuthLogin: Screen("auth/login")
    object AuthRegisterCustomer: Screen("auth/register-customer")
    object AuthRegisterYayasan: Screen("auth/register-yayasan")
}
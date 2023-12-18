package skripsi.magfira.ambulanceapp.presentation

sealed class ScreenRoute(val route: String) {
    object Auth: ScreenRoute("auth")
    object AuthSplashScreen: ScreenRoute("auth/splash-screen")
    object AuthLogin: ScreenRoute("auth/login")
    object AuthRegisterCustomer: ScreenRoute("auth/register-customer")
    object AuthRegisterYayasan: ScreenRoute("auth/register-yayasan")
}
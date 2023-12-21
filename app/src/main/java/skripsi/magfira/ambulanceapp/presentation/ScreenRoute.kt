package skripsi.magfira.ambulanceapp.presentation

sealed class ScreenRoute(val route: String) {

    // Auth
    object Auth: ScreenRoute("auth")
    object AuthSplashScreen: ScreenRoute("auth/splash-screen")
    object AuthLogin: ScreenRoute("auth/login")
    object AuthRegisterCustomer: ScreenRoute("auth/register-customer")
    object AuthRegisterAccountCustomer: ScreenRoute("auth/register-account-customer")
    object AuthRegisterYayasan: ScreenRoute("auth/register-yayasan")
    object AuthRegisterAccountYayasan: ScreenRoute("auth/register-account-yayasan")

    // Customer
    object Customer: ScreenRoute("customer")
    object CustomerMain: ScreenRoute("customer-main")
    object CustomerProfile: ScreenRoute("customer-profile")
    object CustomerAccount: ScreenRoute("customer-account")

    // Driver
    object Driver: ScreenRoute("driver")
    object DriverMain: ScreenRoute("driver-main")
    object DriverProfile: ScreenRoute("driver-profile")
    object DriverAccount: ScreenRoute("driver-account")

    // Yayasan
    object Yayasan: ScreenRoute("yayasan")
    object YayasanMain: ScreenRoute("yayasan-main")
    object YayasanProfile: ScreenRoute("yayasan-profile")
    object YayasanAccount: ScreenRoute("yayasan-account")
    object YayasanDrivers: ScreenRoute("yayasan-drivers")
    object YayasanDriverAccount: ScreenRoute("yayasan-driver-account")

}
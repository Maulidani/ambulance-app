package skripsi.magfira.ambulanceapp.navigation

sealed class ScreenRouter(val route: String) {

    // Auth
    object Auth: ScreenRouter("auth")
    object AuthSplashScreen: ScreenRouter("auth/splash-screen")
    object AuthLogin: ScreenRouter("auth/login")
    object AuthRegisterCustomer: ScreenRouter("auth/register-customer")
    object AuthRegisterAccountCustomer: ScreenRouter("auth/register-account-customer")
    object AuthRegisterYayasan: ScreenRouter("auth/register-yayasan")
    object AuthRegisterAccountYayasan: ScreenRouter("auth/register-account-yayasan")

    // Customer
    object Customer: ScreenRouter("customer")
    object CustomerMain: ScreenRouter("customer-main")
    object CustomerProfile: ScreenRouter("customer-profile")
    object CustomerAccount: ScreenRouter("customer-account")

    // Driver
    object Driver: ScreenRouter("driver")
    object DriverMain: ScreenRouter("driver-main")
    object DriverProfile: ScreenRouter("driver-profile")
    object DriverAccount: ScreenRouter("driver-account")

    // Yayasan
    object Yayasan: ScreenRouter("yayasan")
    object YayasanMain: ScreenRouter("yayasan-main")
    object YayasanProfile: ScreenRouter("yayasan-profile")
    object YayasanAccount: ScreenRouter("yayasan-account")
    object YayasanDrivers: ScreenRouter("yayasan-drivers")
    object YayasanDriverAccount: ScreenRouter("yayasan-driver-account")

}
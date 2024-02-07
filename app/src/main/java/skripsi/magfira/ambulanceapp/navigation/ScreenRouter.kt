package skripsi.magfira.ambulanceapp.navigation

import skripsi.magfira.ambulanceapp.util.NetworkUtils.NAME_KEY_ADDRESS
import skripsi.magfira.ambulanceapp.util.NetworkUtils.NAME_KEY_EMAIL
import skripsi.magfira.ambulanceapp.util.NetworkUtils.NAME_KEY_FILE_URI
import skripsi.magfira.ambulanceapp.util.NetworkUtils.NAME_KEY_ID
import skripsi.magfira.ambulanceapp.util.NetworkUtils.NAME_KEY_NAME
import skripsi.magfira.ambulanceapp.util.NetworkUtils.NAME_KEY_PHONE

sealed class ScreenRouter(val route: String) {

    // Auth
    object Auth : ScreenRouter("auth")
    object AuthSplashScreen : ScreenRouter("auth/splash-screen")
    object AuthLogin : ScreenRouter("auth/login")
    object AuthRegisterCustomer : ScreenRouter("auth/register-customer")

    //    object AuthRegisterAccountCustomer: ScreenRouter("auth/register-account-customer/{name}/{email}/{phone}/{image-uri}")
    object AuthRegisterAccountCustomer :
        ScreenRouter(route = "auth/register-account-customer/{$NAME_KEY_NAME}/{$NAME_KEY_EMAIL}/{$NAME_KEY_PHONE}/{$NAME_KEY_FILE_URI}") {
        fun routeWithArguments(name: String, email: String, phone: String, imageUri: String): String {
            return "auth/register-account-customer/$name/$email/$phone/$imageUri"
        }
    }

    object AuthRegisterYayasan : ScreenRouter("auth/register-yayasan")

    //    object AuthRegisterAccountYayasan : ScreenRouter("auth/register-account-yayasan")
    object AuthRegisterAccountYayasan :
        ScreenRouter(route = "auth/register-account-customer/{$NAME_KEY_NAME}/{$NAME_KEY_EMAIL}/{$NAME_KEY_PHONE}/{$NAME_KEY_ADDRESS}/{$NAME_KEY_FILE_URI}") {
        fun routeWithArguments(name: String, email: String, phone: String, address: String, imageUri: String): String {
            return "auth/register-account-customer/$name/$email/$phone/$address/$imageUri"
        }
    }

    // Customer
    object Customer : ScreenRouter("customer")
    object CustomerHome : ScreenRouter("customer/main")
    object CustomerProfile : ScreenRouter("customer/profile")
    object CustomerAccount : ScreenRouter("customer/account")

    // Driver
    object Driver : ScreenRouter("driver")
    object DriverHome : ScreenRouter("driver/main")
    object DriverProfile : ScreenRouter("driver/profile")
    object DriverAccount : ScreenRouter("driver/account")

    // Yayasan
    object Yayasan : ScreenRouter("yayasan")
    object YayasanHome : ScreenRouter("yayasan/main")
    object YayasanProfile : ScreenRouter("yayasan/profile")
    object YayasanAccount : ScreenRouter("yayasan/account")
    object YayasanDrivers : ScreenRouter("yayasan/drivers")
    object YayasanCreateDriver : ScreenRouter("yayasan/create-driver")
//    object YayasanDriverAccount : ScreenRouter("yayasan/driver-account")
    object YayasanDriverAccount :
        ScreenRouter(route = "yayasan/driver-account/{$NAME_KEY_ID}") {
        fun routeWithArguments(userId: String): String {
            return "yayasan/driver-account/$userId"
        }
    }
}
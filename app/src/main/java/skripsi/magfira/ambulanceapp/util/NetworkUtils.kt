package skripsi.magfira.ambulanceapp.util

object NetworkUtils {

    // API
    const val BASE_URL = "http://10.0.2.2:8000/api/"
    const val BASE_URL_FILE = "http://10.0.2.2:8000/storage/file/"

    // Pusher
    const val APP_KEY = "07e2d415003b6691c650"
    const val APP_CLUSTER = "ap1"

    // Name Key api/route query
    const val NAME_KEY_ID = "id"
    const val NAME_KEY_NAME = "name"
    const val NAME_KEY_EMAIL = "email"
    const val NAME_KEY_PHONE = "phone"
    const val NAME_KEY_ADDRESS = "address"
    const val NAME_KEY_FILE_URI = "file-uri"
    const val NAME_KEY_PHOTO_PROFILE = "foto_profil"
    const val NAME_KEY_DOCUMENT_YAYASAN = "surat_izin"

    // Status account
    const val ACCOUNT_ON = "on"
    const val ACCOUNT_OFF = "off"

    // Roles account
    const val ROLE_CUSTOMER = "customer"
    const val ROLE_DRIVER = "driver"
    const val ROLE_YAYASAN = "yayasan"


}
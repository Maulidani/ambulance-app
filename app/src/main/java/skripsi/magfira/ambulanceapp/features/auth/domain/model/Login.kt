package skripsi.magfira.ambulanceapp.features.auth.domain.model

data class Login(
    val success: String,
    val message: String,
    val data: LoginData,
    val token: String
)

data class LoginData(
    val id: Int,
    val roles: String,
    val status_login: String,
    val name: String,
    val email: String,
    val no_telp: String,
    val foto_profil: String,
    val username: String,
)
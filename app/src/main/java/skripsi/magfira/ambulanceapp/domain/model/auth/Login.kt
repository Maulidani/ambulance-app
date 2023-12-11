package skripsi.magfira.ambulanceapp.domain.model.auth

data class Login(
    val message : String,
    val data : LoginData,
)
data class LoginData(
    val id: String,
)


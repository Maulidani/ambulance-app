package skripsi.magfira.ambulanceapp.features.auth.domain.model

data class Login(
    val message : String,
    val data : LoginData,
)
data class LoginData(
    val id: String,
)


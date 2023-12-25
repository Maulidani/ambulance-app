package skripsi.magfira.ambulanceapp.features.auth.data.remote.dto

import skripsi.magfira.ambulanceapp.features.auth.domain.model.Login
import skripsi.magfira.ambulanceapp.features.auth.domain.model.LoginData

// Data Response
data class LoginDto(
    val errorCode : String,
    val message : String,
    val data : LoginDataDto,
)
data class LoginDataDto(
    val id: String,
    val created_at: String,
    val updated_at: String,
)

// Mapping
fun LoginDto.toLogin(): Login {
    return Login(
        message = message,
        data = data.toLoginData(),
    )
}
fun LoginDataDto.toLoginData(): LoginData {
    return LoginData(
        id = id,
    )
}

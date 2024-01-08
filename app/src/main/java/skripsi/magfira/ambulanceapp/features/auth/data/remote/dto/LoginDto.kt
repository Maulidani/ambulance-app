package skripsi.magfira.ambulanceapp.features.auth.data.remote.dto

import skripsi.magfira.ambulanceapp.features.auth.domain.model.response.Login
import skripsi.magfira.ambulanceapp.features.auth.domain.model.response.LoginData

// Data Response
data class LoginDto(
    val success: String,
    val message: String,
    val user: LoginDataDto,
    val token: String
)
data class LoginDataDto(
    val id: Int,
    val roles: String,
    val yayasan_id: Int?,
    val status_akun: String,
    val status_login: String,
    val name: String,
    val email: String?,
    val no_telp: String?,
    val alamat: String?,
    val foto_profil: String?,
    val surat_izin: String?,
    val username: String,
    val lat: Double?,
    val long: Double?,
    val created_at: String,
    val updated_at: String
)

// Mapping
fun LoginDto.toLogin(): Login {
    return Login(
        success = success,
        message = message,
        user = user.toLoginData(),
        token = token,
    )
}
fun LoginDataDto.toLoginData(): LoginData {
    return LoginData(
        id = id,
        roles = roles,
        status_login = status_login,
        name = name,
        email = email,
        no_telp = no_telp,
        foto_profil = foto_profil,
        username = username,
    )
}
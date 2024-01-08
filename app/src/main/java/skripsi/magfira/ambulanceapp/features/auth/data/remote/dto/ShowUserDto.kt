package skripsi.magfira.ambulanceapp.features.auth.data.remote.dto

import skripsi.magfira.ambulanceapp.features.auth.domain.model.response.ShowUser
import skripsi.magfira.ambulanceapp.features.auth.domain.model.response.ShowUserData

// Data Response
data class ShowUserDto(
    val success: String,
    val message: String,
    val data: ShowUserDataDto,
)
data class ShowUserDataDto(
    val id: Int,
    val roles: String,
    val yayasan_id: Int?,
    val status_akun: String,
    val status_login: String,
    val name: String,
    val email: String,
    val no_telp: String,
    val alamat: String?,
    val foto_profil: String,
    val surat_izin: String?,
    val username: String,
    val lat: Double?,
    val long: Double?,
    val created_at: String,
    val updated_at: String
)

// Mapping
fun ShowUserDto.toShowUser(): ShowUser {
    return ShowUser(
        success = success,
        message = message,
        data = data.toShowUserData(),
    )
}
fun ShowUserDataDto.toShowUserData(): ShowUserData {
    return ShowUserData(
        id = id,
        roles = roles,
        yayasan_id = yayasan_id,
        status_akun = status_akun,
        status_login = status_login,
        name = name,
        email = email,
        no_telp = no_telp,
        alamat = alamat,
        foto_profil = foto_profil,
        surat_izin = surat_izin,
        username = username,
        lat = lat,
        long = long,
        created_at = created_at,
        updated_at = updated_at
    )
}

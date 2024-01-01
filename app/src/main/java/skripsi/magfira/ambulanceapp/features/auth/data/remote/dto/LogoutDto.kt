package skripsi.magfira.ambulanceapp.features.auth.data.remote.dto

import skripsi.magfira.ambulanceapp.features.auth.domain.model.Logout

// Data Response
data class LogoutDto(
    val success: String,
    val message: String,
)

// Mapping
fun LogoutDto.toLogout(): Logout {
    return Logout(
        success = success,
    )
}
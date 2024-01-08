package skripsi.magfira.ambulanceapp.features.auth.data.remote.dto

import skripsi.magfira.ambulanceapp.features.auth.domain.model.response.CreateDriver

// Data Response
data class CreateDriverDto(
    val success: Boolean,
    val message: String,
    val data: CreateDriverDataDto
)
data class CreateDriverDataDto(
    val roles: String,
    val yayasan_id: Int,
    val status_akun: String,
    val name: String,
    val no_telp: String,
    val foto_profil: String,
    val username: String,
    val updated_at: String,
    val created_at: String,
    val id: Int
)

// Mapping
fun CreateDriverDto.toCreateDriver(): CreateDriver {
    return CreateDriver(
        success = success,
        message = message,
    )
}
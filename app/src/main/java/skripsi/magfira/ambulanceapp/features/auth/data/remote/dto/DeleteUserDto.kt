package skripsi.magfira.ambulanceapp.features.auth.data.remote.dto

import skripsi.magfira.ambulanceapp.features.auth.domain.model.response.DeleteUser

// Data Response
data class DeleteUserDto(
    val success: Boolean,
    val message: String,
    val data: Any?,
)

// Mapping
fun DeleteUserDto.toDeleteUser(): DeleteUser {
    return DeleteUser(
        success = success,
        message = message,
        data = data,
    )
}
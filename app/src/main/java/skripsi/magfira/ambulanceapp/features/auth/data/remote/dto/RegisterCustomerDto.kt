package skripsi.magfira.ambulanceapp.features.auth.data.remote.dto

import skripsi.magfira.ambulanceapp.features.auth.domain.model.response.RegisterCustomer

// Data Response
data class RegisterCustomerDto(
    val success: Boolean,
    val message: String,
    val data: RegisterCustomerDataDto
)
data class RegisterCustomerDataDto(
    val roles: String,
    val status_akun: String,
    val name: String,
    val email: String,
    val no_telp: String,
    val alamat: String?,
    val foto_profil: String,
    val username: String,
    val updated_at: String,
    val created_at: String,
    val id: Int
)

// Mapping
fun RegisterCustomerDto.toRegisterCustomer(): RegisterCustomer {
    return RegisterCustomer(
        success = success,
        message = message,
    )
}
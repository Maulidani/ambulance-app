package skripsi.magfira.ambulanceapp.features.auth.data.remote.dto

import skripsi.magfira.ambulanceapp.features.auth.domain.model.response.RegisterYayasan

// Data Response
data class RegisterYayasanDto(
    val success: Boolean,
    val message: String,
    val data: RegisterCustomerDataDto
)
data class RegisterYayasanDataDto(
    val roles: String,
    val status_akun: String,
    val name: String,
    val email: String,
    val no_telp: String,
    val alamat: String?,
    val surat_izin: String,
    val username: String,
    val updated_at: String,
    val created_at: String,
    val id: Int
)

// Mapping
fun RegisterYayasanDto.toRegisterYayasan(): RegisterYayasan {
    return RegisterYayasan(
        success = success,
        message = message,
    )
}
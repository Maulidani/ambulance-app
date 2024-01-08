package skripsi.magfira.ambulanceapp.features.auth.domain.model.response

data class ShowUser(
    val success: String,
    val message: String,
    val data: ShowUserData,
)

data class ShowUserData(
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
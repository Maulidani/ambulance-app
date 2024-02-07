package skripsi.magfira.ambulanceapp.features.auth.domain.model.response

data class DeleteUser(
    val success: Boolean,
    val message: String,
    val data: Any?,
)
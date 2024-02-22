package skripsi.magfira.ambulanceapp.features.order.domain.model.response

data class GetLocation(
    val status: Boolean,
    val message: String,
    val data: String,
)
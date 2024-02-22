package skripsi.magfira.ambulanceapp.features.order.domain.model.request

data class UpdateLocationRequest(
    val lat: String,
    val long: String,
    val _method: String = "PATCH"
)
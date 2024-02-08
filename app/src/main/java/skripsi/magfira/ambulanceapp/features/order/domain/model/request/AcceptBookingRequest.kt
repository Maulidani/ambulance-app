package skripsi.magfira.ambulanceapp.features.order.domain.model.request

data class AcceptBookingRequest(
    val status_boking: String = "diterima",
    val _method: String = "PATCH"
)

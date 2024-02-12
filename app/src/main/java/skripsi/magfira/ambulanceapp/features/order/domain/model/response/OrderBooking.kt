package skripsi.magfira.ambulanceapp.features.order.domain.model.response

data class OrderBooking(
    val success: Boolean,
    val message: String,
    val data: OrderRequestData
)

data class OrderRequestData(
    val id: Long,
    val customer_id: String,
    val driver_id: String,
    val nama: String,
    val lokasi_jemput: String,
    val detail_pesanan: String?,
    val updated_at: String,
    val created_at: String,
)

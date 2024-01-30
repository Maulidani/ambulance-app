package skripsi.magfira.ambulanceapp.features.order.domain.model.request

data class OrderRequest(
    val driver_id: Long,
    val nama: String,
    val lokasi_jemput: String, // LatLng
    val detail_pesanan: String,
)
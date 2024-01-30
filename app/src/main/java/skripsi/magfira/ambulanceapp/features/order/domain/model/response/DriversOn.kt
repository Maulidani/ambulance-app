package skripsi.magfira.ambulanceapp.features.order.domain.model.response

data class DriversOn(
    val success: Boolean,
    val message: String,
    val data: List<DriversOnData>
)
data class DriversOnData(
    val id: Long,
    val yayasan_id: Long,
    val name: String,
    val no_telp: String?,
    val foto_profil: String,
    val lat: String?,
    val long: String?,
    val yayasan: YayasanData
)
data class YayasanData(
    val name: String,
    val alamat: String,
)

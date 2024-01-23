package skripsi.magfira.ambulanceapp.features.order.domain.model

data class DriversOn(
    val success: Boolean,
    val message: String,
    val data: List<DriversOnData>
)
data class DriversOnData(
    val id: Int,
    val yayasan_id: Int,
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

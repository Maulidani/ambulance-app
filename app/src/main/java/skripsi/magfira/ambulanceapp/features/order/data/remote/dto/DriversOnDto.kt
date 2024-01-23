package skripsi.magfira.ambulanceapp.features.order.data.remote.dto

import skripsi.magfira.ambulanceapp.features.order.domain.model.DriversOn
import skripsi.magfira.ambulanceapp.features.order.domain.model.DriversOnData
import skripsi.magfira.ambulanceapp.features.order.domain.model.YayasanData

data class DriversOnDto(
    val success: Boolean,
    val message: String,
    val data: List<DriversOnDataDto>
)
data class DriversOnDataDto(
    val id: Int,
    val roles: String,
    val yayasan_id: Int,
    val status_akun: String,
    val status_login: String,
    val name: String,
    val email: String?,
    val no_telp: String?,
    val alamat: String?,
    val foto_profil: String,
    val surat_izin: String?,
    val username: String,
    val lat: String?,
    val long: String?,
    val created_at: String,
    val updated_at: String,
    val yayasan: YayasanDataDto
)
data class YayasanDataDto(
    val id: Int,
    val roles: String,
    val status_akun: String,
    val status_login: String,
    val name: String,
    val email: String,
    val no_telp: String?,
    val alamat: String,
    val foto_profil: String?,
    val surat_izin: String,
    val username: String,
    val lat: String?,
    val long: String?,
    val created_at: String,
    val updated_at: String
)

// Mapping
fun DriversOnDto.toDriversOn(): DriversOn {
    return DriversOn(
        success = success,
        message = message,
        data = data.map { it.toDriversOnData() }
    )
}
fun DriversOnDataDto.toDriversOnData(): DriversOnData {
    return DriversOnData(
        id = id,
        yayasan_id = yayasan_id,
        name = name,
        no_telp = no_telp,
        foto_profil = foto_profil,
        lat = lat,
        long = long,
        yayasan = yayasan.toYayasanData()
    )
}
fun YayasanDataDto.toYayasanData(): YayasanData {
    return YayasanData(
        name = name,
        alamat = alamat,
    )
}
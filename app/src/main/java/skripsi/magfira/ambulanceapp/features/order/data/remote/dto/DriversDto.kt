package skripsi.magfira.ambulanceapp.features.order.data.remote.dto

import skripsi.magfira.ambulanceapp.features.order.domain.model.response.Drivers
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.DriversData
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.YayasanData

data class DriversDto(
    val success: Boolean,
    val message: String,
    val data: List<DriversDataDto>
)
data class DriversDataDto(
    val id: Long,
    val roles: String,
    val yayasan_id: Long,
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
    val id: Long,
    val roles: String,
    val status_akun: String,
    val status_login: String,
    val name: String,
    val email: String?,
    val no_telp: String?,
    val alamat: String?,
    val foto_profil: String?,
    val surat_izin: String?,
    val username: String,
    val lat: String?,
    val long: String?,
    val created_at: String,
    val updated_at: String
)

// Mapping
fun DriversDto.toDrivers(): Drivers {
    return Drivers(
        success = success,
        message = message,
        data = data.map { it.toDriversData() }
    )
}
fun DriversDataDto.toDriversData(): DriversData {
    return DriversData(
        id = id,
        yayasan_id = yayasan_id,
        name = name,
        no_telp = no_telp,
        foto_profil = foto_profil,
        lat = lat,
        long = long,
//        yayasan = yayasan.toYayasanData()
    )
}
fun YayasanDataDto.toYayasanData(): YayasanData {
    return YayasanData(
        name = name,
        alamat = alamat,
    )
}
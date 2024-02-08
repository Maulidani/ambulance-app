package skripsi.magfira.ambulanceapp.features.order.data.remote.dto

import skripsi.magfira.ambulanceapp.features.order.domain.model.response.AllBooking
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.BookingData
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.BookingDatas
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.Customer
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.Driver
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.Link

data class AllBookingDto(
    val success: Boolean,
    val message: String,
    val data: BookingDatasDto
)

data class BookingDatasDto(
    val current_page: Int,
    val data: List<BookingDataDto>,
    val first_page_url: String,
    val from: Int,
    val last_page: Int,
    val last_page_url: String,
    val links: List<LinkDto>,
    val next_page_url: String?,
    val path: String,
    val per_page: Int,
    val prev_page_url: String?,
    val to: Int,
    val total: Int
)

data class BookingDataDto(
    val id: Int,
    val customer_id: Int,
    val driver_id: Int,
    val status_boking: String,
    val nama: String,
    val lokasi_jemput: String,
    val detail_pesanan: String,
    val created_at: String,
    val updated_at: String,
    val customer: CustomerDto,
    val driver: DriverDto
)

data class CustomerDto(
    val id: Int,
    val roles: String,
    val yayasan_id: String?,
    val status_akun: String,
    val status_login: String,
    val name: String,
    val email: String?,
    val no_telp: String?,
    val alamat: String?,
    val foto_profil: String?,
    val surat_izin: String?,
    val username: String,
    val lat: Double?,
    val long: Double?,
    val created_at: String,
    val updated_at: String
)

data class DriverDto(
    val id: Int,
    val roles: String,
    val yayasan_id: String?,
    val status_akun: String,
    val status_login: String,
    val name: String,
    val email: String,
    val no_telp: String,
    val alamat: String?,
    val foto_profil: String?,
    val surat_izin: String?,
    val username: String,
    val lat: Double?,
    val long: Double?,
    val created_at: String,
    val updated_at: String
)

data class LinkDto(
    val url: String?,
    val label: String,
    val active: Boolean
)


// Mapping
fun AllBookingDto.toAllBooking(): AllBooking {
    return AllBooking(
        success = success,
        message = message,
        data = data.toBookingDatas()
    )
}

fun BookingDatasDto.toBookingDatas(): BookingDatas {
    return BookingDatas(
        current_page = current_page,
        data = data.map { it.toBookingData() },
        first_page_url = first_page_url,
        from = from,
        last_page = last_page,
        last_page_url = last_page_url,
        links = links.map { it.toLink() },
        next_page_url = next_page_url,
        path = path,
        per_page = per_page,
        prev_page_url = prev_page_url,
        to = to,
        total = total
    )
}

fun BookingDataDto.toBookingData(): BookingData {
    return BookingData(
        id = id,
        customer_id = customer_id,
        driver_id = driver_id,
        status_boking = status_boking,
        nama = nama,
        lokasi_jemput = lokasi_jemput,
        detail_pesanan = detail_pesanan,
        customer = customer.toCustomer(),
        created_at = created_at,
        updated_at = updated_at,
        driver = driver.toDriver()
    )
}

fun CustomerDto.toCustomer(): Customer {
    return Customer(
        id = id,
        name = name,
        no_telp = no_telp ?: "",
        foto_profil = foto_profil ?: ""
    )
}

fun DriverDto.toDriver(): Driver {
    return Driver(
        id = id,
        name = name,
        no_telp = no_telp ?: "",
        foto_profil = foto_profil ?: "",
        lat = lat,
        long = long
    )
}

fun LinkDto.toLink(): Link {
    return Link(
        url = url,
        label = label,
        active = active
    )
}

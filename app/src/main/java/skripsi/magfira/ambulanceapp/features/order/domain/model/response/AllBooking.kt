package skripsi.magfira.ambulanceapp.features.order.domain.model.response

data class AllBooking(
    val success: Boolean,
    val message: String,
    val data: BookingDatas
)

data class BookingDatas(
    val current_page: Int,
    val data: List<BookingData>,
    val first_page_url: String,
    val from: Int,
    val last_page: Int,
    val last_page_url: String,
    val links: List<Link>,
    val next_page_url: String?,
    val path: String,
    val per_page: Int,
    val prev_page_url: String?,
    val to: Int,
    val total: Int
)

data class BookingData(
    val id: Int,
    val customer_id: Int,
    val driver_id: Int,
    val status_boking: String,
    val nama: String,
    val lokasi_jemput: String,
    val detail_pesanan: String,
    val created_at: String,
    val updated_at: String,
    val customer: Customer,
    val driver: Driver
)

data class Customer(
    val id: Int,
    val name: String,
    val no_telp: String,
    val foto_profil: String?,
)

data class Driver(
    val id: Int,
    val name: String,
    val no_telp: String,
    val foto_profil: String?,
    val lat: Double?,
    val long: Double?,
)

data class Link(
    val url: String?,
    val label: String,
    val active: Boolean
)

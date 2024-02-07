package skripsi.magfira.ambulanceapp.features.order.data.remote.dto

import skripsi.magfira.ambulanceapp.features.order.domain.model.response.OrderBooking
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.OrderRequestData

data class OrderBookingDto(
    val success: Boolean,
    val message: String,
    val data: OrderRequestDataDto
)

data class OrderRequestDataDto(
    val customer_id: String,
    val driver_id: String,
    val nama: String,
    val lokasi_jemput: String,
    val detail_pesanan: String?,
    val updated_at: String,
    val created_at: String,
    val id: Long
)

// Mapping
fun OrderBookingDto.toOrderBooking(): OrderBooking {
    return OrderBooking(
        success = success,
        message = message,
        data = data.toOrderRequestData()
    )
}

fun OrderRequestDataDto.toOrderRequestData(): OrderRequestData {
    return OrderRequestData(
        customer_id = customer_id,
        driver_id = driver_id,
        nama = nama,
        lokasi_jemput = lokasi_jemput,
        detail_pesanan = detail_pesanan,
        updated_at = updated_at,
        created_at = created_at
    )
}
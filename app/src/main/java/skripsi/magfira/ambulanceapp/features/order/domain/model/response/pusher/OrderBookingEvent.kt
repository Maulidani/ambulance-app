package skripsi.magfira.ambulanceapp.features.order.domain.model.response.pusher

import com.google.gson.annotations.SerializedName

data class OrderBookingEvent(
    @SerializedName("event")
    val event: String,
    @SerializedName("data")
    val data: String,
    @SerializedName("channel")
    val channel: String
)

data class BookingEventData(
    @SerializedName("boking")
    val booking: BookingEvent
)

data class BookingEvent(
    @SerializedName("id")
    val id: Int,
    @SerializedName("customer_id")
    val customerId: Int,
    @SerializedName("driver_id")
    val driverId: Int,
    @SerializedName("status_boking")
    val bookingStatus: String,
    @SerializedName("nama")
    val name: String,
    @SerializedName("lokasi_jemput")
    val pickupLocation: String,
    @SerializedName("detail_pesanan")
    val orderDetail: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String
)

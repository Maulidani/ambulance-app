package skripsi.magfira.ambulanceapp.features.order.data.remote.dto

import skripsi.magfira.ambulanceapp.features.order.domain.model.response.AcceptBooking

// Data Response
data class AcceptBookingDto(
    val success: Boolean,
    val message: String,
)

// Mapping
fun AcceptBookingDto.toAcceptBooking(): AcceptBooking {
    return AcceptBooking(
        success = success,
        message = message,
    )
}
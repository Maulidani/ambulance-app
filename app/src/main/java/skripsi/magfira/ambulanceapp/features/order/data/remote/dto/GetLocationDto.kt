package skripsi.magfira.ambulanceapp.features.order.data.remote.dto

import skripsi.magfira.ambulanceapp.features.order.domain.model.response.GetLocation

// Data Response
data class GetLocationDto(
    val status: Boolean,
    val message: String,
    val data: String,
)

// Mapping
fun GetLocationDto.toGetLocation(): GetLocation {
    return GetLocation(
        status = status,
        message = message,
        data = data,
    )
}
package skripsi.magfira.ambulanceapp.features.order.presentation.data_states

import skripsi.magfira.ambulanceapp.features.order.domain.model.response.OrderBooking

data class OrderBookingState(
    val isLoading: Boolean = false,
    var data: OrderBooking? = null,
    val error: String = "",
)

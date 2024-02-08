package skripsi.magfira.ambulanceapp.features.order.presentation.data_states

import skripsi.magfira.ambulanceapp.features.order.domain.model.response.AcceptBooking

data class AcceptBookingState(
    val isLoading: Boolean = false,
    var data: AcceptBooking? = null,
    val error: String = "",
)

package skripsi.magfira.ambulanceapp.features.order.presentation.data_states

import skripsi.magfira.ambulanceapp.features.order.domain.model.response.AllBooking

data class GetAllBookingState(
    val isLoading: Boolean = false,
    var data: AllBooking? = null,
    val error: String = "",
)

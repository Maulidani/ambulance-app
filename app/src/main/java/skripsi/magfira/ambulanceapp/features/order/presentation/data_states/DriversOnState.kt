package skripsi.magfira.ambulanceapp.features.order.presentation.data_states

import skripsi.magfira.ambulanceapp.features.order.domain.model.response.Drivers

data class DriversOnState(
    val isLoading: Boolean = false,
    var data: Drivers? = null,
    val error: String = "",
)

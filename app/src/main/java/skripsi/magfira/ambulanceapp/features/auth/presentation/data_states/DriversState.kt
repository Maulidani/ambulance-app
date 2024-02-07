package skripsi.magfira.ambulanceapp.features.auth.presentation.data_states

import skripsi.magfira.ambulanceapp.features.order.domain.model.response.Drivers

data class DriversState(
    val isLoading: Boolean = false,
    var data: Drivers? = null,
    val error: String = "",
)

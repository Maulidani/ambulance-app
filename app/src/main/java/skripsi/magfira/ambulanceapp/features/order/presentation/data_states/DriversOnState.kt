package skripsi.magfira.ambulanceapp.features.order.presentation.data_states

import skripsi.magfira.ambulanceapp.features.order.domain.model.DriversOn

data class DriversOnState(
    val isLoading: Boolean = false,
    var data: DriversOn? = null,
    val error: String = "",
)

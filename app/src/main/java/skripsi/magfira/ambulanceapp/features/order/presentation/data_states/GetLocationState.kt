package skripsi.magfira.ambulanceapp.features.order.presentation.data_states

import skripsi.magfira.ambulanceapp.features.order.domain.model.response.GetLocation

data class GetLocationState(
    val isLoading: Boolean = false,
    var data: GetLocation? = null,
    val error: String = "",
)

package skripsi.magfira.ambulanceapp.features.auth.presentation.data_states

import skripsi.magfira.ambulanceapp.features.auth.domain.model.response.ShowUser

data class ShowUserState(
    val isLoading: Boolean = false,
    var data: ShowUser? = null,
    val error: String = "",
)

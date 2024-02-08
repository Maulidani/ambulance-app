package skripsi.magfira.ambulanceapp.features.auth.presentation.data_states

import skripsi.magfira.ambulanceapp.features.auth.domain.model.response.Login
import skripsi.magfira.ambulanceapp.features.auth.domain.model.response.Logout

data class LogoutState(
    val isLoading: Boolean = false,
    var data: Logout? = null,
    val error: String = "",
)

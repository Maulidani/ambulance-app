package skripsi.magfira.ambulanceapp.features.auth.presentation.data_states

import skripsi.magfira.ambulanceapp.features.auth.domain.model.response.Login

data class LoginState(
    val isLoading: Boolean = false,
    var data: Login? = null,
    val error: String = "",
)

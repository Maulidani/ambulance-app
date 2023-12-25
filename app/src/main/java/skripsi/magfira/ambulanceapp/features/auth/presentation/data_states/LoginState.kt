package skripsi.magfira.ambulanceapp.features.auth.presentation.data_states

import skripsi.magfira.ambulanceapp.features.auth.domain.model.Login


data class LoginState(
    val isLoading: Boolean = false,
    val loginResponse: Login? = null,
    val error: String = "",
)

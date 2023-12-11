package skripsi.magfira.ambulanceapp.presentation.auth.data_state

import skripsi.magfira.ambulanceapp.domain.model.auth.Login

data class LoginState(
    val isLoading: Boolean = false,
    val loginResponse: Login? = null,
    val error: String = "",
)

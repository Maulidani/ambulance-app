package skripsi.magfira.ambulanceapp.features.auth.presentation.data_states

import skripsi.magfira.ambulanceapp.features.auth.domain.model.response.RegisterYayasan


data class RegisterYayasanState(
    val isLoading: Boolean = false,
    var data: RegisterYayasan? = null,
    val error: String = "",
)

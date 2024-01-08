package skripsi.magfira.ambulanceapp.features.auth.presentation.data_states

import skripsi.magfira.ambulanceapp.features.auth.domain.model.response.RegisterCustomer

data class RegisterCustomerState(
    val isLoading: Boolean = false,
    var data: RegisterCustomer? = null,
    val error: String = "",
)

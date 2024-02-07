package skripsi.magfira.ambulanceapp.features.auth.presentation.data_states

import skripsi.magfira.ambulanceapp.features.auth.domain.model.response.DeleteUser

data class DeleteUserState(
    val isLoading: Boolean = false,
    var data: DeleteUser? = null,
    val error: String = "",
)

package skripsi.magfira.ambulanceapp.features.auth.presentation.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import skripsi.magfira.ambulanceapp.features.auth.data.use_case.AuthUseCase
import skripsi.magfira.ambulanceapp.features.auth.domain.model.request.LoginRequest
import skripsi.magfira.ambulanceapp.features.auth.presentation.data_states.LoginState
import skripsi.magfira.ambulanceapp.util.MessageUtils.MSG_UNEXPECTED_ERROR
import skripsi.magfira.ambulanceapp.util.Resource
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    var stateLogin by mutableStateOf(LoginState())
        private set

    fun login(request: LoginRequest) {
        authUseCase.login(request).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    stateLogin = LoginState(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    stateLogin = LoginState(
                        data = result.data
                    )
                }

                is Resource.Error -> {
                    stateLogin = LoginState(
                        error = result.message ?: MSG_UNEXPECTED_ERROR
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}
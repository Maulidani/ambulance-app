package skripsi.magfira.ambulanceapp.features.auth.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import skripsi.magfira.ambulanceapp.features.auth.data.use_case.AuthUseCase
import skripsi.magfira.ambulanceapp.features.auth.presentation.data_states.LoginState
import skripsi.magfira.ambulanceapp.util.Resource
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _stateLogin = MutableStateFlow(LoginState())
    val stateLogin: StateFlow<LoginState> = _stateLogin

    fun login(username: String, password: String){
        authUseCase.login(username, password).onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    _stateLogin.value = LoginState(
                        isLoading = true
                    )
                }
                is Resource.Success -> {
                    _stateLogin.value = LoginState(
                        loginResponse = result.data
                    )
                }
                is Resource.Error -> {
                    _stateLogin.value = LoginState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}
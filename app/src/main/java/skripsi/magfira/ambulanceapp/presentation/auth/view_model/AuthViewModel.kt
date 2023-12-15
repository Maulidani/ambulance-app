package skripsi.magfira.ambulanceapp.presentation.auth.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import skripsi.magfira.ambulanceapp.data.use_case.auth.AuthUseCase
import skripsi.magfira.ambulanceapp.presentation.auth.data_state.LoginState
import skripsi.magfira.ambulanceapp.utils.Resource
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _stateLogin = MutableStateFlow(LoginState())
    val stateLogin: StateFlow<LoginState> = _stateLogin

    fun putValue() {
        _stateLogin.value = LoginState(error = "hi guys")
    }

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
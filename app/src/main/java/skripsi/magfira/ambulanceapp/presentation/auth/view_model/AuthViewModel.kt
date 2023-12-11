package skripsi.magfira.ambulanceapp.presentation.auth.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import skripsi.magfira.ambulanceapp.utils.Resource
import skripsi.magfira.ambulanceapp.data.use_case.auth.AuthUseCase
import skripsi.magfira.ambulanceapp.presentation.auth.data_state.LoginState
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _stateLogin = mutableStateOf(LoginState())
    val stateLogin: State<LoginState> = _stateLogin

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
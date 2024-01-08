package skripsi.magfira.ambulanceapp.features.auth.presentation.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okhttp3.MultipartBody
import okhttp3.RequestBody
import skripsi.magfira.ambulanceapp.features.auth.data.use_case.AuthUseCase
import skripsi.magfira.ambulanceapp.features.auth.presentation.data_states.RegisterCustomerState
import skripsi.magfira.ambulanceapp.util.MessageUtils.MSG_UNEXPECTED_ERROR
import skripsi.magfira.ambulanceapp.util.Resource
import javax.inject.Inject

@HiltViewModel
class RegisterCustomerViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    var stateRegisterCustomer by mutableStateOf(RegisterCustomerState())
        private set

    fun registerCustomer(
        partMap: Map<String, RequestBody>,
        imagePart: MultipartBody.Part
    ) {
        authUseCase.registerCustomer(partMap, imagePart).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    stateRegisterCustomer = RegisterCustomerState(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    stateRegisterCustomer = RegisterCustomerState(
                        data = result.data
                    )
                }

                is Resource.Error -> {
                    stateRegisterCustomer = RegisterCustomerState(
                        error = result.message ?: MSG_UNEXPECTED_ERROR
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}
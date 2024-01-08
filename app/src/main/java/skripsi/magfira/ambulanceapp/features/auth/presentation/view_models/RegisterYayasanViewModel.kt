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
import skripsi.magfira.ambulanceapp.features.auth.presentation.data_states.RegisterYayasanState
import skripsi.magfira.ambulanceapp.util.MessageUtils.MSG_UNEXPECTED_ERROR
import skripsi.magfira.ambulanceapp.util.Resource
import javax.inject.Inject

@HiltViewModel
class RegisterYayasanViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    var stateRegisterYayasan by mutableStateOf(RegisterYayasanState())
        private set

    fun registerYayasan(
        partMap: Map<String, RequestBody>,
        filePart: MultipartBody.Part
    ) {
        authUseCase.registerYayasan(partMap, filePart).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    stateRegisterYayasan = RegisterYayasanState(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    stateRegisterYayasan = RegisterYayasanState(
                        data = result.data
                    )
                }

                is Resource.Error -> {
                    stateRegisterYayasan = RegisterYayasanState(
                        error = result.message ?: MSG_UNEXPECTED_ERROR
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}
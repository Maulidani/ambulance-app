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
import skripsi.magfira.ambulanceapp.features.auth.domain.model.request.LoginRequest
import skripsi.magfira.ambulanceapp.features.auth.domain.model.request.UpdateProfileRequest
import skripsi.magfira.ambulanceapp.features.auth.domain.model.response.DeleteUser
import skripsi.magfira.ambulanceapp.features.auth.presentation.data_states.DeleteUserState
import skripsi.magfira.ambulanceapp.features.auth.presentation.data_states.DriversState
import skripsi.magfira.ambulanceapp.features.auth.presentation.data_states.LoginState
import skripsi.magfira.ambulanceapp.features.auth.presentation.data_states.RegisterCustomerState
import skripsi.magfira.ambulanceapp.features.auth.presentation.data_states.RegisterYayasanState
import skripsi.magfira.ambulanceapp.features.auth.presentation.data_states.ShowUserState
import skripsi.magfira.ambulanceapp.util.MessageUtils.MSG_UNEXPECTED_ERROR
import skripsi.magfira.ambulanceapp.util.Resource
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    lateinit var token: String
    lateinit var userId: String

    var stateLogin by mutableStateOf(LoginState())
        private set
    var stateDeleteUser by mutableStateOf(DeleteUserState())
        private set
    var stateRegisterCustomer by mutableStateOf(RegisterCustomerState())
        private set
    var stateRegisterYayasan by mutableStateOf(RegisterYayasanState())
        private set
    var stateCreateDriver by mutableStateOf(ShowUserState())
        private set
    var stateGetProfile by mutableStateOf(ShowUserState())
        private set
    var stateUpdateProfile by mutableStateOf(ShowUserState())
        private set
    var stateDriversYayasan by mutableStateOf(DriversState())
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

    fun deleteUser() {
        authUseCase.deleteUser("Bearer $token", userId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    stateDeleteUser = DeleteUserState(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    stateDeleteUser = DeleteUserState(
                        data = result.data
                    )
                }

                is Resource.Error -> {
                    stateDeleteUser = DeleteUserState(
                        error = result.message ?: MSG_UNEXPECTED_ERROR
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

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

    fun createDriver(
        partMap: Map<String, RequestBody>,
        filePart: MultipartBody.Part
    ) {
        authUseCase.createDriver("Bearer $token", partMap, filePart).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    stateCreateDriver = ShowUserState(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    stateCreateDriver = ShowUserState(
                        data = result.data
                    )
                }

                is Resource.Error -> {
                    stateCreateDriver = ShowUserState(
                        error = result.message ?: MSG_UNEXPECTED_ERROR
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getProfile() {
        authUseCase.getProfile("Bearer $token", userId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    stateGetProfile = ShowUserState(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    stateGetProfile = ShowUserState(
                        data = result.data
                    )
                }

                is Resource.Error -> {
                    stateGetProfile = ShowUserState(
                        error = result.message ?: MSG_UNEXPECTED_ERROR
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun updateProfile(updateProfileRequest: UpdateProfileRequest) {
        authUseCase.updateProfile("Bearer $token", userId, updateProfileRequest).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    stateUpdateProfile = ShowUserState(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    stateUpdateProfile = ShowUserState(
                        data = result.data
                    )
                }

                is Resource.Error -> {
                    stateUpdateProfile = ShowUserState(
                        error = result.message ?: MSG_UNEXPECTED_ERROR
                    )
                }
            }
        }.launchIn(viewModelScope)
    }



    fun driversYayasan() {
        authUseCase.driversYayasan("Bearer $token").onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    stateDriversYayasan = DriversState(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    stateDriversYayasan = DriversState(
                        data = result.data
                    )
                }

                is Resource.Error -> {
                    stateDriversYayasan = DriversState(
                        error = result.message ?: MSG_UNEXPECTED_ERROR
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}
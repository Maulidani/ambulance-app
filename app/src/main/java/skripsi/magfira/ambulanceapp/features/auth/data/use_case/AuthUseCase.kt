package skripsi.magfira.ambulanceapp.features.auth.data.use_case

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.toLogin
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.toRegisterCustomer
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.toRegisterYayasan
import skripsi.magfira.ambulanceapp.features.auth.domain.model.request.LoginRequest
import skripsi.magfira.ambulanceapp.features.auth.domain.model.response.Login
import skripsi.magfira.ambulanceapp.features.auth.domain.model.response.RegisterCustomer
import skripsi.magfira.ambulanceapp.features.auth.domain.model.response.RegisterYayasan
import skripsi.magfira.ambulanceapp.features.auth.domain.repository.AuthRepository
import skripsi.magfira.ambulanceapp.util.MessageUtils.MSG_SERVER_ERROR
import skripsi.magfira.ambulanceapp.util.MessageUtils.MSG_UNEXPECTED_ERROR
import skripsi.magfira.ambulanceapp.util.Resource
import java.io.IOException
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    private val TAG = "AuthUseCase"

    fun login(request: LoginRequest): Flow<Resource<Login>> = flow {
        try {
            // Loading
            emit(Resource.Loading())
            // Request
            val response = repository.login(request).toLogin()
            // Success
            emit(Resource.Success(response))
            Log.d(TAG, "login: $response")
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: MSG_UNEXPECTED_ERROR))
            Log.d(TAG, "login: ${e.localizedMessage ?: MSG_UNEXPECTED_ERROR}")
        } catch (e: IOException) {
            emit(Resource.Error(MSG_SERVER_ERROR))
            Log.d(TAG, "login: $MSG_SERVER_ERROR")
        }
    }

    fun registerCustomer(
        partMap: Map<String, RequestBody>,
        imagePart: MultipartBody.Part
    ): Flow<Resource<RegisterCustomer>> = flow {
        try {
            // Loading
            emit(Resource.Loading())
            // Request
            val response = repository.registerCustomer(partMap, imagePart).toRegisterCustomer()
            // Success
            emit(Resource.Success(response))
            Log.d(TAG, "registerCustomer: $response")
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: MSG_UNEXPECTED_ERROR))
            Log.d(TAG, "registerCustomer: ${e.localizedMessage ?: MSG_UNEXPECTED_ERROR}")
        } catch (e: IOException) {
            emit(Resource.Error(MSG_SERVER_ERROR))
            Log.d(TAG, "registerCustomer: $MSG_SERVER_ERROR")
        }
    }

    fun registerYayasan(
        partMap: Map<String, RequestBody>,
        filePart: MultipartBody.Part
    ): Flow<Resource<RegisterYayasan>> = flow {
        try {
            // Loading
            emit(Resource.Loading())
            // Request
            val response = repository.registerYayasan(partMap, filePart).toRegisterYayasan()
            // Success
            emit(Resource.Success(response))
            Log.d(TAG, "registerCustomer: $response")
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: MSG_UNEXPECTED_ERROR))
            Log.d(TAG, "registerCustomer: ${e.localizedMessage ?: MSG_UNEXPECTED_ERROR}")
        } catch (e: IOException) {
            emit(Resource.Error(MSG_SERVER_ERROR))
            Log.d(TAG, "registerCustomer: $MSG_SERVER_ERROR")
        }
    }

}
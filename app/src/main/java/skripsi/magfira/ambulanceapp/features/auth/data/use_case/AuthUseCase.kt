package skripsi.magfira.ambulanceapp.features.auth.data.use_case

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.toDeleteUser
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.toLogin
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.toRegisterCustomer
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.toRegisterYayasan
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.toShowUser
import skripsi.magfira.ambulanceapp.features.auth.domain.model.request.LoginRequest
import skripsi.magfira.ambulanceapp.features.auth.domain.model.request.UpdateProfileRequest
import skripsi.magfira.ambulanceapp.features.auth.domain.model.response.DeleteUser
import skripsi.magfira.ambulanceapp.features.auth.domain.model.response.Login
import skripsi.magfira.ambulanceapp.features.auth.domain.model.response.RegisterCustomer
import skripsi.magfira.ambulanceapp.features.auth.domain.model.response.RegisterYayasan
import skripsi.magfira.ambulanceapp.features.auth.domain.model.response.ShowUser
import skripsi.magfira.ambulanceapp.features.auth.domain.repository.AuthRepository
import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.toDrivers
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.Drivers
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
            emit(Resource.Error(e.localizedMessage ?: MSG_UNEXPECTED_ERROR))
            Log.d(TAG, "login: ${e.localizedMessage ?: MSG_UNEXPECTED_ERROR}")
        }
    }

    fun deleteUser(
        token: String,
        userId: String,
    ): Flow<Resource<DeleteUser>> = flow {
        try {
            // Loading
            emit(Resource.Loading())
            // Request
            val response = repository.deleteUser(token, userId).toDeleteUser()
            // Success
            emit(Resource.Success(response))
            Log.d(TAG, "deleteAccount: $response")
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: MSG_UNEXPECTED_ERROR))
            Log.d(TAG, "deleteAccount: ${e.localizedMessage ?: MSG_UNEXPECTED_ERROR}")
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: MSG_UNEXPECTED_ERROR))
            Log.d(TAG, "deleteAccount: ${e.localizedMessage ?: MSG_UNEXPECTED_ERROR}")
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
            emit(Resource.Error(e.localizedMessage ?: MSG_UNEXPECTED_ERROR))
            Log.d(TAG, "registerCustomer: ${e.localizedMessage ?: MSG_UNEXPECTED_ERROR}")
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
            emit(Resource.Error(e.localizedMessage ?: MSG_UNEXPECTED_ERROR))
            Log.d(TAG, "registerCustomer: ${e.localizedMessage ?: MSG_UNEXPECTED_ERROR}")
        }
    }

    fun createDriver(
        token: String,
        partMap: Map<String, RequestBody>,
        filePart: MultipartBody.Part
    ): Flow<Resource<ShowUser>> = flow {
        try {
            // Loading
            emit(Resource.Loading())
            // Request
            val response = repository.createDriver(token, partMap, filePart).toShowUser()
            // Success
            emit(Resource.Success(response))
            Log.d(TAG, "createDriver: $response")
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: MSG_UNEXPECTED_ERROR))
            Log.d(TAG, "createDriver: ${e.localizedMessage ?: MSG_UNEXPECTED_ERROR}")
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: MSG_UNEXPECTED_ERROR))
            Log.d(TAG, "createDriver: ${e.localizedMessage ?: MSG_UNEXPECTED_ERROR}")
        }
    }

    fun getProfile(
        token: String,
        userId: String,
    ): Flow<Resource<ShowUser>> = flow {
        try {
            // Loading
            emit(Resource.Loading())
            // Request
            val response = repository.getProfile(token, userId).toShowUser()
            // Success
            emit(Resource.Success(response))
            Log.d(TAG, "getProfile: $response")
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: MSG_UNEXPECTED_ERROR))
            Log.d(TAG, "getProfile: ${e.localizedMessage ?: MSG_UNEXPECTED_ERROR}")
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: MSG_SERVER_ERROR))
            Log.d(TAG, "getProfile: ${e.localizedMessage ?: MSG_UNEXPECTED_ERROR}")
        }
    }

    fun updateProfile(
        token: String,
        userId: String,
        updateProfileRequest: UpdateProfileRequest
    ): Flow<Resource<ShowUser>> = flow {
        try {
            // Loading
            emit(Resource.Loading())
            // Request
            val response =
                repository.updateProfile(token, userId, updateProfileRequest).toShowUser()
            // Success
            emit(Resource.Success(response))
            Log.d(TAG, "updateProfile: $response")
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: MSG_UNEXPECTED_ERROR))
            Log.d(TAG, "updateProfile: ${e.localizedMessage ?: MSG_UNEXPECTED_ERROR}")
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: MSG_UNEXPECTED_ERROR))
            Log.d(TAG, "updateProfile: ${e.localizedMessage ?: MSG_UNEXPECTED_ERROR}")
        }
    }

    fun driversYayasan(
        token: String,
    ): Flow<Resource<Drivers>> = flow {
        try {
            // Loading
            emit(Resource.Loading())
            // Request
            val response = repository.driversYayasan(token).toDrivers()
            // Success
            emit(Resource.Success(response))
            Log.d(TAG, "driversYayasan: $response")
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: MSG_UNEXPECTED_ERROR))
            Log.d(TAG, "driversYayasan: ${e.localizedMessage ?: MSG_UNEXPECTED_ERROR}")
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: MSG_UNEXPECTED_ERROR))
            Log.d(TAG, "driversYayasan: ${e.localizedMessage ?: MSG_UNEXPECTED_ERROR}")
        }
    }

}
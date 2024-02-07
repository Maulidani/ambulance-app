package skripsi.magfira.ambulanceapp.features.auth.data.repository

import okhttp3.MultipartBody
import okhttp3.RequestBody
import skripsi.magfira.ambulanceapp.features.auth.data.remote.AuthApi
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.DeleteUserDto
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.LoginDto
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.RegisterCustomerDto
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.RegisterYayasanDto
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.ShowUserDto
import skripsi.magfira.ambulanceapp.features.auth.domain.model.request.LoginRequest
import skripsi.magfira.ambulanceapp.features.auth.domain.model.request.UpdateProfileRequest
import skripsi.magfira.ambulanceapp.features.auth.domain.repository.AuthRepository
import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.DriversDto
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi
) : AuthRepository {

    override suspend fun login(request: LoginRequest): LoginDto {
        return api.login(request)
    }

    override suspend fun deleteUser(token: String, userId: String): DeleteUserDto {
        return api.deleteUser(token, userId)
    }

    override suspend fun registerCustomer(
        partMap: Map<String, RequestBody>,
        imagePart: MultipartBody.Part
    ): RegisterCustomerDto {
        return api.registerCustomer(partMap,imagePart)
    }

    override suspend fun registerYayasan(
        partMap: Map<String, RequestBody>,
        filePart: MultipartBody.Part
    ): RegisterYayasanDto {
        return api.registerYayasan(partMap,filePart)
    }

    override suspend fun createDriver(
        token: String,
        partMap: Map<String, RequestBody>,
        filePart: MultipartBody.Part
    ): ShowUserDto {
        return api.createDriver(token, partMap,filePart)
    }

    override suspend fun getProfile(token: String, userId: String): ShowUserDto {
        return api.getProfile(token, userId)
    }

    override suspend fun updateProfile(
        token: String,
        userId: String,
        updateProfileRequest: UpdateProfileRequest
    ): ShowUserDto {
        return api.updateProfile(token, userId, updateProfileRequest)
    }

    override suspend fun driversYayasan(
        token: String,
    ): DriversDto {
        return api.driversYayasan(token)
    }

}
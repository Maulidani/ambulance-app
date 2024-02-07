package skripsi.magfira.ambulanceapp.features.auth.domain.repository

import okhttp3.MultipartBody
import okhttp3.RequestBody
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.DeleteUserDto
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.LoginDto
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.RegisterCustomerDto
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.RegisterYayasanDto
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.ShowUserDto
import skripsi.magfira.ambulanceapp.features.auth.domain.model.request.LoginRequest
import skripsi.magfira.ambulanceapp.features.auth.domain.model.request.UpdateProfileRequest
import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.DriversDto

interface AuthRepository {

    suspend fun login(request: LoginRequest): LoginDto

    suspend fun deleteUser(
        token: String,
        userId: String,
    ): DeleteUserDto

    suspend fun registerCustomer(
        partMap: Map<String, RequestBody>,
        imagePart: MultipartBody.Part
    ): RegisterCustomerDto

    suspend fun registerYayasan(
        partMap: Map<String, RequestBody>,
        filePart: MultipartBody.Part
    ): RegisterYayasanDto

    suspend fun createDriver(
        token: String,
        partMap: Map<String, RequestBody>,
        filePart: MultipartBody.Part
    ): ShowUserDto

    suspend fun getProfile(
        token: String,
        userId: String,
    ): ShowUserDto

    suspend fun updateProfile(
        token: String,
        userId: String,
        updateProfileRequest: UpdateProfileRequest
    ): ShowUserDto

    suspend fun driversYayasan(
        token: String,
    ): DriversDto

}
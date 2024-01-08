package skripsi.magfira.ambulanceapp.features.auth.data.repository

import okhttp3.MultipartBody
import okhttp3.RequestBody
import skripsi.magfira.ambulanceapp.features.auth.data.remote.AuthApi
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.LoginDto
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.RegisterCustomerDto
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.RegisterYayasanDto
import skripsi.magfira.ambulanceapp.features.auth.domain.model.request.LoginRequest
import skripsi.magfira.ambulanceapp.features.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi
) : AuthRepository {

    override suspend fun login(request: LoginRequest): LoginDto {
        return api.login(request)
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

}
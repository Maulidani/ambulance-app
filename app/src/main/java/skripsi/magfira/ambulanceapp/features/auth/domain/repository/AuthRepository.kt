package skripsi.magfira.ambulanceapp.features.auth.domain.repository

import okhttp3.MultipartBody
import okhttp3.RequestBody
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.LoginDto
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.RegisterCustomerDto
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.RegisterYayasanDto
import skripsi.magfira.ambulanceapp.features.auth.domain.model.request.LoginRequest

interface AuthRepository {

    suspend fun login(request: LoginRequest): LoginDto

    suspend fun registerCustomer(
        partMap: Map<String, RequestBody>,
        imagePart: MultipartBody.Part
    ): RegisterCustomerDto

    suspend fun registerYayasan(
        partMap: Map<String, RequestBody>,
        filePart: MultipartBody.Part
    ): RegisterYayasanDto

}
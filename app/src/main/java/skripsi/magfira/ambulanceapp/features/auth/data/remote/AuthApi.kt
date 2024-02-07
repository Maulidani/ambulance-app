package skripsi.magfira.ambulanceapp.features.auth.data.remote

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Path
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.DeleteUserDto
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.LoginDto
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.LogoutDto
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.RegisterCustomerDto
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.RegisterYayasanDto
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.ShowUserDto
import skripsi.magfira.ambulanceapp.features.auth.domain.model.request.LoginRequest
import skripsi.magfira.ambulanceapp.features.auth.domain.model.request.UpdateProfileRequest
import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.DriversDto

interface AuthApi {

    @POST("admin/login")
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): LoginDto

    @POST("admin/logout")
    suspend fun logout(
        @Header("Authorization") token: String,
    ): LogoutDto

    @Multipart
    @POST("admin/regis")
    suspend fun registerCustomer(
        @PartMap partMap: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part foto_profil: MultipartBody.Part
    ): RegisterCustomerDto

    @Multipart
    @POST("admin/regis")
    suspend fun registerYayasan(
        @PartMap partMap: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part surat_izin: MultipartBody.Part
    ): RegisterYayasanDto

    @Multipart
    @POST("admin/users")
    suspend fun createDriver(
        @Header("Authorization") token: String,
        @PartMap partMap: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part foto_profil: MultipartBody.Part
    ): ShowUserDto

    @GET("admin/users/{userId}")
    suspend fun getProfile(
        @Header("Authorization") token: String,
        @Path("userId") userId: String
    ): ShowUserDto

    @POST("admin/users/{userId}")
    suspend fun updateProfile(
        @Header("Authorization") token: String,
        @Path("userId") userId: String,
        @Body updateProfileRequest: UpdateProfileRequest,
    ): ShowUserDto

    @DELETE("admin/users/{userId}")
    suspend fun deleteUser(
        @Header("Authorization") token: String,
        @Path("userId") userId: String
    ): DeleteUserDto

    @GET("admin/driver_yayasan")
    suspend fun driversYayasan(
        @Header("Authorization") token: String,
    ): DriversDto

}
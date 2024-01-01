package skripsi.magfira.ambulanceapp.features.auth.data.remote

import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.CreateDriverDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.DeleteUserDto
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.LoginDto
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.LogoutDto
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.RegisterCustomerDto
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.RegisterYayasanDto
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.ShowUserDto

interface AuthApi {

    @FormUrlEncoded
    @POST("admin/login")
    suspend fun login(
        @Body username: String,
        @Body password: String,
    ): LoginDto

    @FormUrlEncoded
    @POST("admin/logout")
    suspend fun logout(): LogoutDto

    @Multipart
    @POST("admin/regis")
    suspend fun registerCustomer(
        @Part roles: RequestBody,
        @Part status_akun: RequestBody,
        @Part name: RequestBody,
        @Part email: RequestBody,
        @Part no_telp: RequestBody,
        @Part foto_profil: MultipartBody.Part,
        @Part username: RequestBody,
        @Part password: RequestBody,
        @Part password_confirmation: RequestBody,
    ): RegisterCustomerDto

    @Multipart
    @POST("admin/regis")
    suspend fun registerYayasan(
        @Part roles: RequestBody,
        @Part status_akun: RequestBody,
        @Part name: RequestBody,
        @Part email: RequestBody,
        @Part no_telp: RequestBody,
        @Part alamat: RequestBody,
        @Part surat_izin: MultipartBody.Part,
        @Part username: RequestBody,
        @Part password: RequestBody,
        @Part password_confirmation: RequestBody,
    ): RegisterYayasanDto

    @Multipart
    @POST("admin/users")
    suspend fun createDriver(
        @Part roles: RequestBody,
        @Part status_akun: RequestBody,
        @Part name: RequestBody,
        @Part foto_profil: MultipartBody.Part,
        @Part username: RequestBody,
        @Part password: RequestBody,
        @Part password_confirmation: RequestBody,
    ): CreateDriverDto

    @GET("admin/users/{userId}")
    suspend fun showUser(
        @Path("userId") userId: String
    ): ShowUserDto

    @DELETE("admin/users/{userId}")
    fun deleteUser(
        @Path("userId") userId: String
    ): DeleteUserDto

}
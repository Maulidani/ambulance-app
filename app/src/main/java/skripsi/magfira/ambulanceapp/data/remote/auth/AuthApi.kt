package skripsi.magfira.ambulanceapp.data.remote.auth

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import skripsi.magfira.ambulanceapp.data.remote.auth.dto.LoginDto

interface AuthApi {

    @POST("")
    suspend fun login(
        @Body username: String,
        @Body password: String,
    ): LoginDto
}
package skripsi.magfira.ambulanceapp.features.auth.data.remote

import retrofit2.http.Body
import retrofit2.http.POST
import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.LoginDto

interface AuthApi {

    @POST("")
    suspend fun login(
        @Body username: String,
        @Body password: String,
    ): LoginDto
}
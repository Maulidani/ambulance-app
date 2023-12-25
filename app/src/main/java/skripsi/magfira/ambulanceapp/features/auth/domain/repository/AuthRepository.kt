package skripsi.magfira.ambulanceapp.features.auth.domain.repository

import skripsi.magfira.ambulanceapp.features.auth.data.remote.dto.LoginDto

interface AuthRepository {

    suspend fun login(username: String, password: String): LoginDto

//    suspend fun register(): RegisterDto

}
package skripsi.magfira.ambulanceapp.domain.repository.auth

import skripsi.magfira.ambulanceapp.data.remote.auth.dto.LoginDto

interface AuthRepository {

    suspend fun login(username: String, password: String): LoginDto

//    suspend fun register(): RegisterDto

}
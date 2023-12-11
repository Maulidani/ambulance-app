package skripsi.magfira.ambulanceapp.data.repository.auth

import skripsi.magfira.ambulanceapp.data.remote.auth.AuthApi
import skripsi.magfira.ambulanceapp.data.remote.auth.dto.LoginDto
import skripsi.magfira.ambulanceapp.domain.repository.auth.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi
): AuthRepository {

    override suspend fun login(username: String, password: String): LoginDto {
        return api.login(username, password)
    }

}
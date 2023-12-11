package skripsi.magfira.ambulanceapp.data.use_case.auth

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import skripsi.magfira.ambulanceapp.utils.Resource
import skripsi.magfira.ambulanceapp.data.remote.auth.dto.toLogin
import skripsi.magfira.ambulanceapp.domain.model.auth.Login
import skripsi.magfira.ambulanceapp.domain.repository.auth.AuthRepository
import java.io.IOException
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    fun login(username: String, password: String): Flow<Resource<Login>> = flow {
        try {
            // Loading
            emit(Resource.Loading())
            // Request
            val loginResponse = repository.login(username, password).toLogin() // Request
            // Success
            emit(Resource.Success(loginResponse))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

}
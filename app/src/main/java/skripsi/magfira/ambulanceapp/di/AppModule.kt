package skripsi.magfira.ambulanceapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import skripsi.magfira.ambulanceapp.features.auth.data.remote.AuthApi
import skripsi.magfira.ambulanceapp.features.auth.data.repository.AuthRepositoryImpl
import skripsi.magfira.ambulanceapp.features.auth.domain.repository.AuthRepository
import skripsi.magfira.ambulanceapp.util.Constants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Auth
    @Provides
    @Singleton
    fun provideAuthApi(): AuthApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi): AuthRepository {
        return AuthRepositoryImpl(api)
    }

//    @Provides
//    @Singleton
//    fun provideAuthUseCase(repository: AuthRepository): AuthUseCase {
//        return AuthUseCase(repository)
//    }

    // Customer
    //

    // Driver
    //

    // Yayasan
    //

}
package skripsi.magfira.ambulanceapp.utils.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import skripsi.magfira.ambulanceapp.utils.Constants
import skripsi.magfira.ambulanceapp.data.remote.auth.AuthApi
import skripsi.magfira.ambulanceapp.data.repository.auth.AuthRepositoryImpl
import skripsi.magfira.ambulanceapp.domain.repository.auth.AuthRepository
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

    // Customer
    //

    // Driver
    //

    // Yayasan
    //

}
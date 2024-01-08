package skripsi.magfira.ambulanceapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import skripsi.magfira.ambulanceapp.features.auth.data.remote.AuthApi
import skripsi.magfira.ambulanceapp.features.auth.data.repository.AuthRepositoryImpl
import skripsi.magfira.ambulanceapp.features.auth.domain.repository.AuthRepository
import skripsi.magfira.ambulanceapp.util.NetworkUtils
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // OkHttpClient
    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder().apply {
//                connectTimeout(60, TimeUnit.SECONDS)
//                readTimeout(60, TimeUnit.SECONDS)
//                writeTimeout(60, TimeUnit.SECONDS)
                addInterceptor(Interceptor { chain ->
                    val originalRequest: Request = chain.request()
                    val newRequest: Request = originalRequest.newBuilder()
//                        .header("Content-Type", "application/json")
//                        .header("Content-Type", "multipart/form-data")
//                        .header("Authorization", "Bearer $TOKEN")
                        .build()
                    chain.proceed(newRequest)
                })
                addInterceptor(httpLoggingInterceptor)
            }
            .build()

    // Auth
    @Provides
    @Singleton
    fun provideAuthApi(okHttpClient: OkHttpClient): AuthApi {
        return Retrofit.Builder()
            .baseUrl(NetworkUtils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi): AuthRepository {
        return AuthRepositoryImpl(api)
    }

}
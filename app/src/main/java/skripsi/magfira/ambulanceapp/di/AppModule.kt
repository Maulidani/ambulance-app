package skripsi.magfira.ambulanceapp.di

import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
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
import skripsi.magfira.ambulanceapp.datastore.DataStorePreferences
import skripsi.magfira.ambulanceapp.features.auth.data.remote.AuthApi
import skripsi.magfira.ambulanceapp.features.auth.data.repository.AuthRepositoryImpl
import skripsi.magfira.ambulanceapp.features.auth.domain.repository.AuthRepository
import skripsi.magfira.ambulanceapp.features.order.data.remote.OrderApi
import skripsi.magfira.ambulanceapp.features.order.data.repository.OrderRepositoryImpl
import skripsi.magfira.ambulanceapp.features.order.domain.repository.OrderRepository
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

    @Singleton
    @Provides
    fun provideDataStorePreferences(dataStorePreferences: DataStorePreferences): DataStorePreferences =
        dataStorePreferences

    // Network
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

    // Auth Repo
    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi): AuthRepository {
        return AuthRepositoryImpl(api)
    }

    // Order
    @Provides
    @Singleton
    fun provideOrderApi(okHttpClient: OkHttpClient): OrderApi {
        return Retrofit.Builder()
            .baseUrl(NetworkUtils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(OrderApi::class.java)
    }

    // Order Repo
    @Provides
    @Singleton
    fun provideOrderRepository(api: OrderApi): OrderRepository {
        return OrderRepositoryImpl(api)
    }

    // Pusher WebSocket
    @Provides
    @Singleton
    fun providePusher(): Pusher {
        val options = PusherOptions()
        options.setCluster(NetworkUtils.APP_CLUSTER)
        return Pusher(NetworkUtils.APP_KEY, options)
    }

}
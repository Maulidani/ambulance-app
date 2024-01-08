package skripsi.magfira.ambulanceapp.features.order.data.remote

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.DriversOnDto

interface AuthOrder {

    @GET("admin/all_driver_on")
    suspend fun driversOn(): DriversOnDto

    @GET("admin/driver_yayasan_on")
    suspend fun driversYayasanOn(): DriversOnDto

//    @POST("login-admin/bokings")
//    fun orderRequest(
//        @Body("driver_id") driverId: Int,
//        @Body("nama") name: String,
//        @Body("lokasi_jemput") pickUpLocation: String, // LatLng
//        @Body("detail_pesanan") type: String,
//    ): orderRequestDto

}
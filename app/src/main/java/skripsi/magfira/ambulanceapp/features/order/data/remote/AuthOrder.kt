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

//    @FormUrlEncoded
//    @POST("login-admin/bokings")
//    fun orderRequest(
//        @Field("driver_id") driverId: Int,
//        @Field("nama") name: String,
//        @Field("lokasi_jemput") pickUpLocation: String, // LatLng
//        @Field("detail_pesanan") type: String,
//    ): orderRequestDto

}
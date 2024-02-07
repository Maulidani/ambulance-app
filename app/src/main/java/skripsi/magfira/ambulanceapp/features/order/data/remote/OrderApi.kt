package skripsi.magfira.ambulanceapp.features.order.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.DriversDto
import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.OrderBookingDto
import skripsi.magfira.ambulanceapp.features.order.domain.model.request.OrderRequest

interface OrderApi {

    @GET("admin/all_driver_on")
    suspend fun driversOn(
        @Header("Authorization") token: String
    ): DriversDto

    @GET("admin/driver_yayasan_on")
    suspend fun driversYayasanOn(
        @Header("Authorization") token: String,
    ): DriversDto

    @POST("admin/bokings")
    fun orderBooking(
        @Header("Authorization") token: String,
        @Body orderRequest: OrderRequest
    ): OrderBookingDto

}
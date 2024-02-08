package skripsi.magfira.ambulanceapp.features.order.data.remote

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.AcceptBookingDto
import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.AllBookingDto
import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.DriversDto
import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.OrderBookingDto
import skripsi.magfira.ambulanceapp.features.order.domain.model.request.AcceptBookingRequest
import skripsi.magfira.ambulanceapp.features.order.domain.model.request.OrderRequest
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.AllBooking

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
    suspend fun orderBooking(
        @Header("Authorization") token: String,
        @Body orderRequest: OrderRequest
    ): OrderBookingDto

    @GET("admin/bokings")
    suspend fun getAllBooking(
        @Header("Authorization") token: String,
    ): AllBookingDto

    @POST("admin/bokings/{bookingId}")
    suspend fun acceptBooking(
        @Header("Authorization") token: String,
        @Path("bookingId") bookingId: String,
        @Body acceptBookingRequest: AcceptBookingRequest
    ): AcceptBookingDto

    @DELETE("admin/bokings/{bookingId}")
    suspend fun cancelBooking(
        @Header("Authorization") token: String,
        @Path("bookingId") bookingId: String,
    ): AcceptBookingDto

}
package skripsi.magfira.ambulanceapp.features.order.domain.repository

import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.AcceptBookingDto
import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.AllBookingDto
import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.DriversDto
import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.OrderBookingDto
import skripsi.magfira.ambulanceapp.features.order.domain.model.request.AcceptBookingRequest
import skripsi.magfira.ambulanceapp.features.order.domain.model.request.OrderRequest
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.AllBooking

interface OrderRepository {

    suspend fun driversOn(token: String): DriversDto
    suspend fun driversYayasanOn(token: String): DriversDto
    suspend fun orderBooking(token: String, orderRequest: OrderRequest): OrderBookingDto
    suspend fun getAllBooking(token: String): AllBookingDto
    suspend fun acceptBooking(token: String,bookingId: String, acceptBookingRequest: AcceptBookingRequest): AcceptBookingDto
    suspend fun cancelBooking(token: String,bookingId: String): AcceptBookingDto

}
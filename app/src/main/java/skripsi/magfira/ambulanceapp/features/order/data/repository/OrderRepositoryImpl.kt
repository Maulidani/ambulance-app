package skripsi.magfira.ambulanceapp.features.order.data.repository

import skripsi.magfira.ambulanceapp.features.order.data.remote.OrderApi
import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.AcceptBookingDto
import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.AllBookingDto
import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.DriversDto
import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.GetLocationDto
import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.OrderBookingDto
import skripsi.magfira.ambulanceapp.features.order.domain.model.request.AcceptBookingRequest
import skripsi.magfira.ambulanceapp.features.order.domain.model.request.OrderRequest
import skripsi.magfira.ambulanceapp.features.order.domain.model.request.UpdateLocationRequest
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.AllBooking
import skripsi.magfira.ambulanceapp.features.order.domain.repository.OrderRepository
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val api: OrderApi
) : OrderRepository {

    override suspend fun getDriverLocation(token: String, driverId: String): GetLocationDto {
        return api.getDriverLocation(token, driverId)
    }

    override suspend fun updateLocation(token: String, userId: String, updateLocationRequest: UpdateLocationRequest): GetLocationDto {
        return api.updateLocation(token, userId, updateLocationRequest)
    }

    override suspend fun driversOn(token: String): DriversDto {
        return api.driversOn(token)
    }

    override suspend fun driversYayasanOn(token: String): DriversDto {
        return api.driversYayasanOn(token)
    }

    override suspend fun orderBooking(token: String, orderRequest: OrderRequest): OrderBookingDto {
        return api.orderBooking(token, orderRequest)
    }

    override suspend fun getAllBooking(token: String): AllBookingDto {
        return api.getAllBooking(token)
    }

    override suspend fun acceptBooking(
        token: String,
        bookingId: String,
        acceptBookingRequest: AcceptBookingRequest
    ): AcceptBookingDto {
        return api.acceptBooking(token, bookingId, acceptBookingRequest)
    }

    override suspend fun cancelBooking(
        token: String,
        bookingId: String,
    ): AcceptBookingDto {
        return api.cancelBooking(token, bookingId)
    }

}
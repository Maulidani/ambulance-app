package skripsi.magfira.ambulanceapp.features.order.data.repository

import skripsi.magfira.ambulanceapp.features.order.data.remote.OrderApi
import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.DriversDto
import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.OrderBookingDto
import skripsi.magfira.ambulanceapp.features.order.domain.model.request.OrderRequest
import skripsi.magfira.ambulanceapp.features.order.domain.repository.OrderRepository
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val api: OrderApi
) : OrderRepository {

    override suspend fun driversOn(token: String): DriversDto {
        return api.driversOn(token)
    }

    override suspend fun driversYayasanOn(token: String): DriversDto {
        return api.driversYayasanOn(token)
    }

    override suspend fun orderBooking(token: String, orderRequest: OrderRequest): OrderBookingDto {
        return api.orderBooking(token,orderRequest)
    }

}
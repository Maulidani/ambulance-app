package skripsi.magfira.ambulanceapp.features.order.domain.repository

import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.DriversDto
import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.OrderBookingDto
import skripsi.magfira.ambulanceapp.features.order.domain.model.request.OrderRequest

interface OrderRepository {

    suspend fun driversOn(token: String): DriversDto
    suspend fun driversYayasanOn(token: String): DriversDto
    suspend fun orderBooking(token: String, orderRequest: OrderRequest): OrderBookingDto

}
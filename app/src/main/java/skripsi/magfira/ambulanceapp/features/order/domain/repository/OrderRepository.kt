package skripsi.magfira.ambulanceapp.features.order.domain.repository

import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.DriversOnDto
import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.OrderBookingDto
import skripsi.magfira.ambulanceapp.features.order.domain.model.request.OrderRequest

interface OrderRepository {

    suspend fun driversOn(token: String): DriversOnDto
    suspend fun orderBooking(token: String, orderRequest: OrderRequest): OrderBookingDto

}
package skripsi.magfira.ambulanceapp.features.order.domain.repository

import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.DriversOnDto

interface OrderRepository {

    suspend fun driversOn(token: String): DriversOnDto

}
package skripsi.magfira.ambulanceapp.features.order.data.use_case

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.toAcceptBooking
import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.toAllBooking
import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.toDrivers
import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.toGetLocation
import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.toOrderBooking
import skripsi.magfira.ambulanceapp.features.order.domain.model.request.AcceptBookingRequest
import skripsi.magfira.ambulanceapp.features.order.domain.model.request.OrderRequest
import skripsi.magfira.ambulanceapp.features.order.domain.model.request.UpdateLocationRequest
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.AcceptBooking
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.AllBooking
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.Drivers
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.GetLocation
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.OrderBooking
import skripsi.magfira.ambulanceapp.features.order.domain.repository.OrderRepository
import skripsi.magfira.ambulanceapp.util.MessageUtils.MSG_SERVER_ERROR
import skripsi.magfira.ambulanceapp.util.MessageUtils.MSG_UNEXPECTED_ERROR
import skripsi.magfira.ambulanceapp.util.Resource
import java.io.IOException
import javax.inject.Inject

class OrderUseCase @Inject constructor(
    private val repository: OrderRepository
) {
    private val TAG = "OrderUseCase"

    fun getDriverLocation(token: String, driverId: String): Flow<Resource<GetLocation>> = flow {
        try {
            // Loading
            emit(Resource.Loading())
            // Request
            val response = repository.getDriverLocation(token, driverId).toGetLocation()
            // Success
            emit(Resource.Success(response))
            Log.d(TAG, "getDriverLocation: $response")
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: MSG_UNEXPECTED_ERROR))
            Log.d(TAG, "getDriverLocation: ${e.localizedMessage ?: MSG_UNEXPECTED_ERROR}")
        } catch (e: IOException) {
            emit(Resource.Error(MSG_SERVER_ERROR))
            Log.d(TAG, "getDriverLocation: $MSG_SERVER_ERROR")
        }
    }

    fun updateLocation(token: String, userId: String, updateLocationRequest: UpdateLocationRequest): Flow<Resource<GetLocation>> = flow {
        try {
            // Loading
            emit(Resource.Loading())
            // Request
            val response = repository.updateLocation(token, userId, updateLocationRequest).toGetLocation()
            // Success
            emit(Resource.Success(response))
            Log.d(TAG, "updateLocation: $response")
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: MSG_UNEXPECTED_ERROR))
            Log.d(TAG, "updateLocation: ${e.localizedMessage ?: MSG_UNEXPECTED_ERROR}")
        } catch (e: IOException) {
            emit(Resource.Error(MSG_SERVER_ERROR))
            Log.d(TAG, "updateLocation: $MSG_SERVER_ERROR")
        }
    }

    fun driversOn(token: String): Flow<Resource<Drivers>> = flow {
        try {
            // Loading
            emit(Resource.Loading())
            // Request
            val response = repository.driversOn(token).toDrivers()
            // Success
            emit(Resource.Success(response))
            Log.d(TAG, "driversOn: $response")
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: MSG_UNEXPECTED_ERROR))
            Log.d(TAG, "driversOn: ${e.localizedMessage ?: MSG_UNEXPECTED_ERROR}")
        } catch (e: IOException) {
            emit(Resource.Error(MSG_SERVER_ERROR))
            Log.d(TAG, "driversOn: $MSG_SERVER_ERROR")
        }
    }

    fun driversYayasanOn(token: String): Flow<Resource<Drivers>> = flow {
        try {
            // Loading
            emit(Resource.Loading())
            // Request
            val response = repository.driversYayasanOn(token).toDrivers()
            // Success
            emit(Resource.Success(response))
            Log.d(TAG, "driversOn: $response")
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: MSG_UNEXPECTED_ERROR))
            Log.d(TAG, "driversOn: ${e.localizedMessage ?: MSG_UNEXPECTED_ERROR}")
        } catch (e: IOException) {
            emit(Resource.Error(MSG_SERVER_ERROR))
            Log.d(TAG, "driversOn: $MSG_SERVER_ERROR")
        }
    }

    fun orderBooking(token: String, orderRequest: OrderRequest): Flow<Resource<OrderBooking>> =
        flow {
            try {
                // Loading
                emit(Resource.Loading())
                // Request
                val response = repository.orderBooking(token, orderRequest).toOrderBooking()
                // Success
                emit(Resource.Success(response))
                Log.d(TAG, "orderBooking: $response")
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: MSG_UNEXPECTED_ERROR))
                Log.d(TAG, "orderBooking: ${e.localizedMessage ?: MSG_UNEXPECTED_ERROR}")
            } catch (e: IOException) {
                emit(Resource.Error(MSG_SERVER_ERROR))
                Log.d(TAG, "orderBooking: $MSG_SERVER_ERROR")
            }
        }

    fun getAllBooking(token: String): Flow<Resource<AllBooking>> = flow {
        try {
            // Loading
            emit(Resource.Loading())
            // Request
            val response = repository.getAllBooking(token).toAllBooking()
            // Success
            emit(Resource.Success(response))
            Log.d(TAG, "getAllBooking: $response")
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: MSG_UNEXPECTED_ERROR))
            Log.d(TAG, "getAllBooking: ${e.localizedMessage ?: MSG_UNEXPECTED_ERROR}")
        } catch (e: IOException) {
            emit(Resource.Error(MSG_SERVER_ERROR))
            Log.d(TAG, "getAllBooking: $MSG_SERVER_ERROR")
        }
    }

    fun acceptBooking(
        token: String,
        bookingId: String,
        acceptBookingRequest: AcceptBookingRequest
    ): Flow<Resource<AcceptBooking>> = flow {
        try {
            // Loading
            emit(Resource.Loading())
            // Request
            val response = repository.acceptBooking(token,bookingId,acceptBookingRequest).toAcceptBooking()
            // Success
            emit(Resource.Success(response))
            Log.d(TAG, "acceptBooking: $response")
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: MSG_UNEXPECTED_ERROR))
            Log.d(TAG, "acceptBooking: ${e.localizedMessage ?: MSG_UNEXPECTED_ERROR}")
        } catch (e: IOException) {
            emit(Resource.Error(MSG_SERVER_ERROR))
            Log.d(TAG, "acceptBooking: $MSG_SERVER_ERROR")
        }
    }

    fun cancelBooking(
        token: String,
        bookingId: String,
    ): Flow<Resource<AcceptBooking>> = flow {
        try {
            // Loading
            emit(Resource.Loading())
            // Request
            val response = repository.cancelBooking(token,bookingId).toAcceptBooking()
            // Success
            emit(Resource.Success(response))
            Log.d(TAG, "cancelBooking: $response")
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: MSG_UNEXPECTED_ERROR))
            Log.d(TAG, "cancelBooking: ${e.localizedMessage ?: MSG_UNEXPECTED_ERROR}")
        } catch (e: IOException) {
            emit(Resource.Error(MSG_SERVER_ERROR))
            Log.d(TAG, "cancelBooking: $MSG_SERVER_ERROR")
        }
    }

}
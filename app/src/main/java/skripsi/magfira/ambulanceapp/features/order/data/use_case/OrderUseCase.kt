package skripsi.magfira.ambulanceapp.features.order.data.use_case

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import skripsi.magfira.ambulanceapp.features.order.data.remote.dto.toDriversOn
import skripsi.magfira.ambulanceapp.features.order.domain.model.DriversOn
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

    fun driversOn(token: String): Flow<Resource<DriversOn>> = flow {
        try {
            // Loading
            emit(Resource.Loading())
            // Request
            val response = repository.driversOn(token).toDriversOn()
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

}
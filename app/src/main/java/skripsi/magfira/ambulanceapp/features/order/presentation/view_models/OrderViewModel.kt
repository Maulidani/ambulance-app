package skripsi.magfira.ambulanceapp.features.order.presentation.view_models

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import skripsi.magfira.ambulanceapp.features.order.data.use_case.OrderUseCase
import skripsi.magfira.ambulanceapp.features.order.domain.model.request.OrderRequest
import skripsi.magfira.ambulanceapp.features.order.presentation.data_states.DriversOnState
import skripsi.magfira.ambulanceapp.features.order.presentation.data_states.OrderBookingState
import skripsi.magfira.ambulanceapp.util.LocationProvider
import skripsi.magfira.ambulanceapp.util.MessageUtils.MSG_UNEXPECTED_ERROR
import skripsi.magfira.ambulanceapp.util.Resource
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderUseCase: OrderUseCase
) : ViewModel() {

    lateinit var token: String

    var currentLocation by mutableStateOf(LatLng(0.0, 0.0))
        private set
    var currentLocationInitialized by mutableStateOf(false)
        private set
    var editableMyLocation by mutableStateOf(false)
        private set

    @Composable
    fun InitializeLocation(context: Context) {
        LocationProvider(context) {
            currentLocation = LatLng(it.latitude, it.longitude)
            currentLocationInitialized = true
        }
    }
    fun editableLocation(isEditable: Boolean) {
        editableMyLocation = isEditable
    }

    var stateDriversOn by mutableStateOf(DriversOnState())
        private set

    var stateOrderBooking by mutableStateOf(OrderBookingState())
        private set

    fun driversOn() {
        orderUseCase.driversOn("Bearer $token").onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    stateDriversOn = DriversOnState(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    stateDriversOn = DriversOnState(
                        data = result.data
                    )
                }

                is Resource.Error -> {
                    stateDriversOn = DriversOnState(
                        error = result.message ?: MSG_UNEXPECTED_ERROR
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun orderBooking(orderRequest: OrderRequest) {
        orderUseCase.orderBooking("Bearer $token", orderRequest).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    stateOrderBooking = OrderBookingState(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    stateOrderBooking = OrderBookingState(
                        data = result.data
                    )
                }

                is Resource.Error -> {
                    stateOrderBooking = OrderBookingState(
                        error = result.message ?: MSG_UNEXPECTED_ERROR
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}
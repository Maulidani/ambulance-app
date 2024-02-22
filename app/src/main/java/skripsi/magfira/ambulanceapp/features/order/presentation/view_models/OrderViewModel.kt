package skripsi.magfira.ambulanceapp.features.order.presentation.view_models

import android.content.Context
import android.location.Location
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import skripsi.magfira.ambulanceapp.datastore.DataStorePreferences
import skripsi.magfira.ambulanceapp.features.order.data.use_case.OrderUseCase
import skripsi.magfira.ambulanceapp.features.order.domain.model.request.AcceptBookingRequest
import skripsi.magfira.ambulanceapp.features.order.domain.model.request.OrderRequest
import skripsi.magfira.ambulanceapp.features.order.domain.model.request.UpdateLocationRequest
import skripsi.magfira.ambulanceapp.features.order.presentation.data_states.AcceptBookingState
import skripsi.magfira.ambulanceapp.features.order.presentation.data_states.DriversOnState
import skripsi.magfira.ambulanceapp.features.order.presentation.data_states.GetAllBookingState
import skripsi.magfira.ambulanceapp.features.order.presentation.data_states.GetLocationState
import skripsi.magfira.ambulanceapp.features.order.presentation.data_states.OrderBookingState
import skripsi.magfira.ambulanceapp.util.LocationProvider
import skripsi.magfira.ambulanceapp.util.MessageUtils.MSG_UNEXPECTED_ERROR
import skripsi.magfira.ambulanceapp.util.Resource
import skripsi.magfira.ambulanceapp.util.stopLocationUpdate
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderUseCase: OrderUseCase
) : ViewModel() {

    private val USER_ROLES = listOf("Customer", "Driver", "Yayasan")

    lateinit var token: String
    lateinit var role: String
    lateinit var userId: String

    var currentLocation by mutableStateOf(LatLng(0.0, 0.0))
        private set
    var editableMyLocation by mutableStateOf(false)
        private set

    @Composable
    fun InitializeLocation(context: Context) {
        LocationProvider(context) {
            currentLocation = LatLng(it.latitude, it.longitude)

            if (role.lowercase() == USER_ROLES[1].lowercase()) { // If driver
                val distance = FloatArray(1)
                Location.distanceBetween(
                    currentLocation.latitude, currentLocation.longitude,
                    it.latitude, it.longitude,
                    distance
                )
                val range = 50.0 // in meters

                if (distance[0] >= range) {
                    currentLocation = LatLng(it.latitude, it.longitude)

                    // Update to server
                    val request = UpdateLocationRequest(
                        currentLocation.latitude.toString(),
                        currentLocation.longitude.toString(),
                    )
                    updateLocation(request)
                }
            }

        }
    }

    fun editableLocation(isEditable: Boolean) {
        editableMyLocation = isEditable
    }

    fun updateMyLocation(latLng: LatLng) {
        currentLocation = latLng
    }

    var stateDriverLocation by mutableStateOf(GetLocationState())
        private set
    var stateUpdateLocation by mutableStateOf(GetLocationState())
        private set
    var stateDriversOn by mutableStateOf(DriversOnState())
        private set
    var stateDriversYayasanOn by mutableStateOf(DriversOnState())
        private set
    var stateOrderBooking by mutableStateOf(OrderBookingState())
        private set
    var stateAllBooking by mutableStateOf(GetAllBookingState())
        private set
    var stateAcceptBooking by mutableStateOf(AcceptBookingState())
        private set
    var stateCancelBooking by mutableStateOf(AcceptBookingState())
        private set

    fun getDriverLocation(driverId: String) {
        orderUseCase.getDriverLocation("Bearer $token", driverId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    stateDriverLocation = GetLocationState(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    stateDriverLocation = GetLocationState(
                        data = result.data
                    )
                }

                is Resource.Error -> {
                    stateDriverLocation = GetLocationState(
                        error = result.message ?: MSG_UNEXPECTED_ERROR
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun updateLocation(updateLocationRequest: UpdateLocationRequest) {
        orderUseCase.updateLocation("Bearer $token", userId, updateLocationRequest)
            .onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        stateUpdateLocation = GetLocationState(
                            isLoading = true
                        )
                    }

                    is Resource.Success -> {
                        stateUpdateLocation = GetLocationState(
                            data = result.data
                        )
                    }

                    is Resource.Error -> {
                        stateUpdateLocation = GetLocationState(
                            error = result.message ?: MSG_UNEXPECTED_ERROR
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }

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

    fun driversYayasanOn() {
        orderUseCase.driversYayasanOn("Bearer $token").onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    stateDriversYayasanOn = DriversOnState(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    stateDriversYayasanOn = DriversOnState(
                        data = result.data
                    )
                }

                is Resource.Error -> {
                    stateDriversYayasanOn = DriversOnState(
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

    fun getAllBooking() {
        orderUseCase.getAllBooking("Bearer $token").onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    stateAllBooking = GetAllBookingState(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    stateAllBooking = GetAllBookingState(
                        data = result.data
                    )
                }

                is Resource.Error -> {
                    stateAllBooking = GetAllBookingState(
                        error = result.message ?: MSG_UNEXPECTED_ERROR
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun acceptBooking(bookingId: String, acceptBookingRequest: AcceptBookingRequest) {
        orderUseCase.acceptBooking("Bearer $token", bookingId, acceptBookingRequest)
            .onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        stateAcceptBooking = AcceptBookingState(
                            isLoading = true
                        )
                    }

                    is Resource.Success -> {
                        stateAcceptBooking = AcceptBookingState(
                            data = result.data
                        )
                    }

                    is Resource.Error -> {
                        stateAcceptBooking = AcceptBookingState(
                            error = result.message ?: MSG_UNEXPECTED_ERROR
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun cancelBooking(bookingId: String) {
        orderUseCase.cancelBooking("Bearer $token", bookingId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    stateCancelBooking = AcceptBookingState(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    stateCancelBooking = AcceptBookingState(
                        data = result.data
                    )
                }

                is Resource.Error -> {
                    stateCancelBooking = AcceptBookingState(
                        error = result.message ?: MSG_UNEXPECTED_ERROR
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}
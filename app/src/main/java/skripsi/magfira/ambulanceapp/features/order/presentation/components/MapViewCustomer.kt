package skripsi.magfira.ambulanceapp.features.order.presentation.components

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import skripsi.magfira.ambulanceapp.R
import skripsi.magfira.ambulanceapp.features.common.presentation.components.MarkerMapDetail
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.BookingData
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.DriversData
import skripsi.magfira.ambulanceapp.features.order.presentation.view_models.OrderViewModel
import skripsi.magfira.ambulanceapp.util.getReadableLocation
import skripsi.magfira.ambulanceapp.util.stopLocationUpdate

@Composable
fun MapViewCustomer(
    viewModel: OrderViewModel,
    driversOnData: List<DriversData>,
    bookingData: List<BookingData>?,
    context: Context
) {
    val TAG = "MapViewCustomer"

    Log.d(TAG, "myLocation editable: ${viewModel.editableMyLocation}")
    Log.d(TAG, "myLocation: ${viewModel.currentLocation}")
    Log.d(
        TAG,
        "myLocation : ${
            getReadableLocation(
                viewModel.currentLocation.latitude,
                viewModel.currentLocation.longitude,
                context
            )
        }"
    )

    val ORDERING_FLOW = listOf("dalam proses", "diterima")

    var isOrderAccepted = false
    if (bookingData != null) {
        if (bookingData.isNotEmpty()) {
            if (bookingData?.get(0)?.status_boking == ORDERING_FLOW[1]) {
                isOrderAccepted = true
            } else {
                isOrderAccepted = false
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(viewModel.currentLocation, 15F)
        }
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapClick = {
                if (viewModel.editableMyLocation) {
                    viewModel.updateMyLocation(it)
                    stopLocationUpdate()

                    Log.d(TAG, "myLocation editable: edit marker")
                }
            }) {

            LaunchedEffect(viewModel.currentLocation) {
                cameraPositionState.animate(
                    CameraUpdateFactory.newLatLngZoom(viewModel.currentLocation, 15f),
                    1000
                )
            }
            Marker(
                state = MarkerState(viewModel.currentLocation),
                title = "Lokasi saya",
                icon = BitmapDescriptorFactory.fromResource(R.drawable.marker_my_location),
            )

            // In ordering state
            if (isOrderAccepted) {
                stopLocationUpdate()

                MarkerInfoWindowContent(
                    state = MarkerState(
                        position = LatLng(
                            bookingData?.get(0)?.driver?.lat ?: 0.0,
                            bookingData?.get(0)?.driver?.long ?: 0.0
                        )
                    ),
                    title = "Driver",
                    icon = BitmapDescriptorFactory.fromResource(R.drawable.marker_ambulance_location),
                ) {
//                    val data = bookingData?.driver
//                    MarkerMapDetail(driverOnData = data)
                }

            } else {
                // Drivers location
                driversOnData.forEach { data ->
                    MarkerInfoWindowContent(
                        state = MarkerState(
                            position = LatLng(
                                data.lat?.toDoubleOrNull() ?: 0.0,
                                data.long?.toDoubleOrNull() ?: 0.0
                            )
                        ),
                        title = "Driver",
                        icon = BitmapDescriptorFactory.fromResource(R.drawable.marker_ambulance_location),
                    ) {
                        MarkerMapDetail(driverOnData = data)
                    }
                }
            }
        }
    }
}

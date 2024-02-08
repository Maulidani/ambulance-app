package skripsi.magfira.ambulanceapp.features.order.presentation.components

import android.content.Context
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
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.AllBooking
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.DriversData
import skripsi.magfira.ambulanceapp.features.order.presentation.view_models.OrderViewModel
import skripsi.magfira.ambulanceapp.util.parseLatLngFromString

@Composable
fun MapViewDriver(
    viewModel: OrderViewModel,
    allBooking: AllBooking,
    myUserId: String,
    context: Context
) {
    val TAG = "MapViewDriver"
    val ORDERING_FLOW = listOf("dalam proses", "diterima")

    val filteredData = allBooking.data.data.filter { it.driver_id.toString() == myUserId}

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
        ) {

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

            // Check if in ordering
            if (filteredData.isNotEmpty()) {
                // There are items that match the conditions
                val isOrdering = filteredData[0]

                val customerLocations = parseLatLngFromString(isOrdering.lokasi_jemput)
                val customerData = isOrdering.customer

                // Customer location
                customerLocations?.let {
                    MarkerState(
                        position = it
                    )
                }?.let {
                    MarkerInfoWindowContent(
                        state = it,
                        title = "Detail",
                        icon = BitmapDescriptorFactory.fromResource(R.drawable.marker_ambulance_location),
                    ) {
                        if (myUserId == filteredData.get(0).driver_id.toString()){
                            MarkerMapDetail(customerData = customerData)
                        }
                    }
                }

            } else {
                // No items match the conditions
            }

        }
    }
}

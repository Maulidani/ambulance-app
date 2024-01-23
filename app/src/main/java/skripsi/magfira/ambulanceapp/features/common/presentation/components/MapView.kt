package skripsi.magfira.ambulanceapp.features.common.presentation.components

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import skripsi.magfira.ambulanceapp.features.order.domain.model.DriversOnData
import skripsi.magfira.ambulanceapp.features.order.presentation.view_models.OrderViewModel
import skripsi.magfira.ambulanceapp.util.getReadableLocation
import skripsi.magfira.ambulanceapp.util.requestAllPermissions

@Composable
fun MapView(
    viewModel: OrderViewModel,
    driversOnData: List<DriversOnData>,
    context: Context
) {
    if (requestAllPermissions(context = context)) {
        viewModel.initializeLocation(context = context)
    } else {
        // Not granted
    }
    var myLocation = viewModel.currentLocation
//    var myLocation by rememberSaveable { mutableStateOf(currentLocation) }
    var editableMyLocation = viewModel.editableMyLocation
//    var editableMyLocation by rememberSaveable { mutableStateOf(viewModel.editableMyLocation)}

    Log.d("MapView", "myLocation: $myLocation")
    Log.d("MapView", "myLocation : ${getReadableLocation(myLocation.latitude, myLocation.longitude, context)}")

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(myLocation, 15F)
        }
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapClick = {
                if (editableMyLocation) {
                    myLocation = it
                }
            }) {

            LaunchedEffect(myLocation) {
                cameraPositionState.animate(
                    CameraUpdateFactory.newLatLngZoom(myLocation, 12f),
                    500
                )
            }
            Marker(
                state = MarkerState(myLocation),
                title = "Lokasi saya",
                icon = BitmapDescriptorFactory.fromResource(R.drawable.marker_my_location),
            )

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
                    MarkerMapDetail(data)
                }
            }
        }
    }
}

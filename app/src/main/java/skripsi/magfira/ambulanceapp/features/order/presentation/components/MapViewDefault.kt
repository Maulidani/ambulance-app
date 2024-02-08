package skripsi.magfira.ambulanceapp.features.order.presentation.components

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import skripsi.magfira.ambulanceapp.R
import skripsi.magfira.ambulanceapp.features.order.presentation.view_models.OrderViewModel

@Composable
fun MapViewDefault(
    viewModel: OrderViewModel,
    context: Context
) {
    val TAG = "MapViewDefault"
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(viewModel.currentLocation, 15F)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
    ) {
        Marker(
            state = MarkerState(viewModel.currentLocation),
            title = "Lokasi saya",
            icon = BitmapDescriptorFactory.fromResource(R.drawable.marker_my_location),
        )
    }
}

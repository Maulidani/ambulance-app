package skripsi.magfira.ambulanceapp.features.common.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapView() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        val location = LatLng(-5.142836108686529, 119.47596759451385)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(location, 15F)
        }
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
//            Marker(
//                state = MarkerState(position = location),
//                title = "Location",
//                snippet = "Marker in Location"
//            )
            MarkerInfoWindowContent(
                state = MarkerState(position = location),
                title = "Location",
                snippet = "Marker in Location"
            ) {
                MarkerMapDetail()
            }
        }
    }
}
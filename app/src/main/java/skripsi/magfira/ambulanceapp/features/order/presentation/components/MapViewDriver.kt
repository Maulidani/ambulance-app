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
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.DriversData
import skripsi.magfira.ambulanceapp.features.order.presentation.view_models.OrderViewModel

@Composable
fun MapViewDriver(
) {
    val TAG = "MapViewDriver"

}

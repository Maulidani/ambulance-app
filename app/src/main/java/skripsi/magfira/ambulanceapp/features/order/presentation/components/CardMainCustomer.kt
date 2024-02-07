package skripsi.magfira.ambulanceapp.features.order.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.EditLocationAlt
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import skripsi.magfira.ambulanceapp.R
import skripsi.magfira.ambulanceapp.features.common.presentation.components.ButtonIcon
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.DriversData

@Composable
fun CardMainCustomer(
    driversOnData: List<DriversData>,
    toEditLocation: () -> Unit,
    toOrderDetail: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(color = MaterialTheme.colorScheme.background),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val iconMarkerLocation =
                    painterResource(id = R.drawable.marker_my_location)
                Image(
                    modifier = Modifier
                        .height(32.dp)
                        .width(32.dp),
                    painter = iconMarkerLocation,
                    contentDescription = iconMarkerLocation.toString(),
                    contentScale = ContentScale.Fit,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Lokasi saya / jemput",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { toEditLocation() }
                ) {
                    val iconEditLocation = Icons.Default.EditLocationAlt
                    Icon(
                        imageVector = iconEditLocation,
                        contentDescription = Icons.Default.EditLocationAlt.toString()
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                val iconMarkerAmbulanceLocation =
                    painterResource(id = R.drawable.logo_only)
                Image(
                    modifier = Modifier
                        .height(32.dp)
                        .width(32.dp),
                    painter = iconMarkerAmbulanceLocation,
                    contentDescription = iconMarkerAmbulanceLocation.toString(),
                    contentScale = ContentScale.Fit,
                )
                Spacer(modifier = Modifier.width(8.dp))
                if (driversOnData.isEmpty()) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Tidak ada ambulance disekitar anda",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Red,
                    )
                } else {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Ada ${driversOnData.size} ambulance disekitar anda yang siap mengantar...",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                ButtonIcon(
                    modifier = Modifier,
                    onClick = {
                        toOrderDetail()
                    },
                    icon = Icons.Default.ArrowForwardIos,
                    text = "Pesan",
                    textColor = Color.White,
                    backgroundColor = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}
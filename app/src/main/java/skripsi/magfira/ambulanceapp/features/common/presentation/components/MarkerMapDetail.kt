package skripsi.magfira.ambulanceapp.features.common.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import skripsi.magfira.ambulanceapp.R

@Composable
fun MarkerMapDetail() {
    Surface(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        color = Color.Transparent
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val iconMarkerAmbulanceLocation =
                    painterResource(id = R.drawable.logo_only)
                Image(
                    modifier = Modifier
                        .height(24.dp)
                        .width(24.dp),
                    painter = iconMarkerAmbulanceLocation,
                    contentDescription = iconMarkerAmbulanceLocation.toString(),
                    contentScale = ContentScale.Fit,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Testing",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {

                val nameDriver = "Testing"
                val phoneDriver = "Testing"
                val nameYayasanDriver = "Testing"
                val imageDriver = painterResource(id = R.drawable.logo_only)
                Surface(
                    modifier = Modifier
                        .height(64.dp)
                        .width(48.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Gray),
                        painter = imageDriver,
                        contentDescription = imageDriver.toString(),
                        contentScale = ContentScale.Fit,
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = nameDriver,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = phoneDriver,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}
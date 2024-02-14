package skripsi.magfira.ambulanceapp.features.order.presentation.components

import android.content.Context
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import skripsi.magfira.ambulanceapp.R
import skripsi.magfira.ambulanceapp.features.common.presentation.components.ButtonNoIcon
import skripsi.magfira.ambulanceapp.features.common.presentation.components.ImageView
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.BookingData
import skripsi.magfira.ambulanceapp.features.order.presentation.view_models.OrderViewModel
import skripsi.magfira.ambulanceapp.util.NetworkUtils
import skripsi.magfira.ambulanceapp.util.OpenChat
import skripsi.magfira.ambulanceapp.util.getReadableLocation
import skripsi.magfira.ambulanceapp.util.parseLatLngFromString

@Composable
fun CardOrderingCustomer(
    viewModel: OrderViewModel,
    context: Context,
    bookingData: BookingData,
    toMainOrder: () -> Unit,
    toChat: () -> Unit,
) {
    val ORDERING_FLOW = listOf("dalam proses", "diterima")

    val notAcceptedYet = "Tunggu driver ambulan menerima pesanan anda"
    val accepted = "Driver ambulan telah menerima pesanan anda"
    var orderAcceptByDriver = false

    val locationName = parseLatLngFromString(bookingData.lokasi_jemput)?.let {
        getReadableLocation(
            it.latitude,
            it.longitude, context
        )
    }

    if (bookingData.status_boking == ORDERING_FLOW[1]) {
        orderAcceptByDriver = true
    } else {
        orderAcceptByDriver = false
    }

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
                verticalAlignment = Alignment.Top,
            ) {
                val iconMarkerAmbulanceLocation =
                    painterResource(id = R.drawable.marker_ambulance_location)
                Image(
                    modifier = Modifier
                        .height(32.dp)
                        .width(32.dp),
                    painter = iconMarkerAmbulanceLocation,
                    contentDescription = iconMarkerAmbulanceLocation.toString(),
                    contentScale = ContentScale.Fit,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = if (orderAcceptByDriver) accepted else notAcceptedYet,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }

            // Show data driver
            if (orderAcceptByDriver) {

                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                ) {
                    ImageView(
                        context = context,
                        source = "${NetworkUtils.BASE_URL_FILE}${bookingData.driver.foto_profil}",
                        editable = false,
                        imageClicked = {
                            //
                        },
                        iconEditClicked = {
                            //
                        }
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = bookingData.driver.name,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = bookingData.driver.no_telp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
            ) {
                var hide by remember { mutableStateOf(true) }
                val iconDown = Icons.Default.KeyboardArrowDown
                val iconUp = Icons.Default.KeyboardArrowUp

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = bookingData.nama
                        )
                        Icon(
                            modifier = Modifier
                                .clickable {
                                    hide = !hide
                                },
                            imageVector = if (hide) iconDown else iconUp,
                            contentDescription = if (hide) iconDown.toString() else iconUp.toString()
                        )
                    }
                    Column(
                        modifier = Modifier
                            .animateContentSize()
                    ) {
                        if (!hide) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = bookingData.customer.no_telp)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = locationName ?: "-")
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = bookingData.detail_pesanan)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (orderAcceptByDriver) {
                        ButtonNoIcon(
                            modifier = Modifier.weight(1F),
                            onClick = {
                                toMainOrder()
                            },
                            text = "Selesai",
                            textColor = MaterialTheme.colorScheme.primary,
                            backgroundColor = MaterialTheme.colorScheme.secondary,
                        )
                        Box(modifier = Modifier.weight(0.1F))
                    } else {
                        Box(modifier = Modifier.weight(1F))
                    }
                    ButtonNoIcon(
                        modifier = Modifier.weight(1F),
                        onClick = {
                            if (orderAcceptByDriver) {
                                toChat()
                            }
                        },
                        text = if (orderAcceptByDriver) "Chat Driver" else "Menunggu...",
                        textColor = Color.White,
                        backgroundColor = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        }
    }
}
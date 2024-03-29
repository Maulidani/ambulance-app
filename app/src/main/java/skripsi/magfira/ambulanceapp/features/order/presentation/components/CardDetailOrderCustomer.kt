package skripsi.magfira.ambulanceapp.features.order.presentation.components

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import skripsi.magfira.ambulanceapp.datastore.DataStorePreferences
import skripsi.magfira.ambulanceapp.features.common.presentation.components.ButtonIcon
import skripsi.magfira.ambulanceapp.features.common.presentation.components.TextFieldOutlinedForm
import skripsi.magfira.ambulanceapp.features.order.domain.model.request.OrderRequest
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.DriversData
import skripsi.magfira.ambulanceapp.features.order.presentation.view_models.OrderViewModel
import skripsi.magfira.ambulanceapp.util.CalculateLocations
import skripsi.magfira.ambulanceapp.util.MessageUtils
import skripsi.magfira.ambulanceapp.util.getReadableLocation

@Composable
fun CardDetailOrderCustomer(
    viewModel: OrderViewModel,
    dataStorePreferences: DataStorePreferences,
    context: Context,
    driversOn: List<DriversData>,
    iconBackClick: () -> Unit,
    toOrdering: () -> Unit
) {
    var myLocation = viewModel.currentLocation
    var closestDriverAmbulanData = CalculateLocations
        .findClosestUser( myLocation, driversOn)

    Log.d("MapView", "myLocation: $myLocation")
    Log.d(
        "MapView",
        "myLocation : ${getReadableLocation(myLocation.latitude, myLocation.longitude, context)}"
    )

    var orderName by rememberSaveable { mutableStateOf("") }
    var orderLocation by rememberSaveable {
        mutableStateOf(
            getReadableLocation(
                myLocation.latitude,
                myLocation.longitude,
                context
            )
        )
    }
    var orderDetailLocation by rememberSaveable { mutableStateOf("") }

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
            Text(
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
                text = "Detail",
            )
            Spacer(modifier = Modifier.height(12.dp))
            TextFieldOutlinedForm(
                value = orderName,
                onValueChange = { orderName = it },
                label = "Nama Pemesan",
                icon = Icons.Default.Person
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldOutlinedForm(
                value = orderLocation,
                onValueChange = { orderLocation = it },
                label = "Lokasi Jemput",
                icon = Icons.Default.LocationOn
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldOutlinedForm(
                value = orderDetailLocation,
                onValueChange = { orderDetailLocation = it },
                label = "Detail Lokasi",
                icon = Icons.Default.Edit
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                val iconBack = Icons.Default.ArrowBack
                Icon(
                    modifier = Modifier
                        .clickable { iconBackClick() },
                    imageVector = iconBack,
                    contentDescription = iconBack.toString(),
                    tint = MaterialTheme.colorScheme.onBackground,
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    ButtonIcon(
                        modifier = Modifier,
                        onClick = {
                            if (orderName.isEmpty() ||
                                orderLocation.isEmpty() ||
                                orderDetailLocation.isEmpty() ||
                                closestDriverAmbulanData == null
                            ) {
                                Toast.makeText(
                                    context,
                                    MessageUtils.MSG_REQUIRED_FIELDS,
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                val orderRequest = OrderRequest(
                                    driver_id = closestDriverAmbulanData.id,
                                    nama = orderName,
                                    lokasi_jemput = "${myLocation.latitude}, ${myLocation.longitude}", // LatLng
                                    detail_pesanan = orderDetailLocation,
                                )
                                viewModel.orderBooking(orderRequest)
                            }
                        },
                        icon = Icons.Default.ArrowForwardIos,
                        text = "Pesan Sekarang",
                        textColor = Color.White,
                        backgroundColor = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        }
    }
}
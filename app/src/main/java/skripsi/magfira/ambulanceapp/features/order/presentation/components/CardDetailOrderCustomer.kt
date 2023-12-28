package skripsi.magfira.ambulanceapp.features.order.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import skripsi.magfira.ambulanceapp.features.common.presentation.components.ButtonIcon
import skripsi.magfira.ambulanceapp.features.common.presentation.components.TextFieldOutlinedForm

@Composable
fun CardDetailOrderCustomer() {
    val orderName = remember { mutableStateOf("") }
    val orderLocation = remember { mutableStateOf("") }
    val orderDetailLocation = remember { mutableStateOf("") }
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
                value = orderName.value,
                onValueChange = { orderName.value },
                label = "Nama Pemesan",
                icon = Icons.Default.Person
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldOutlinedForm(
                value = orderLocation.value,
                onValueChange = { orderLocation.value },
                label = "Lokasi Jemput",
                icon = Icons.Default.LocationOn
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldOutlinedForm(
                value = orderDetailLocation.value,
                onValueChange = { orderDetailLocation.value },
                label = "Detail Lokasi",
                icon = Icons.Default.Edit
            )
            Spacer(modifier = Modifier.height(24.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                ButtonIcon(
                    modifier = Modifier,
                    onClick = {
                        //
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
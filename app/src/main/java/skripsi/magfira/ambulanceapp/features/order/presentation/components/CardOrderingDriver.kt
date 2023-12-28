package skripsi.magfira.ambulanceapp.features.order.presentation.components

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
import androidx.compose.foundation.layout.fillMaxSize
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

@Composable
fun CardOrderingDriver() {
    val orderRequestedText = "Ada pesanan..."
    val orderAccaptedText = "Sedang dalam pesanan"
    val orderAccapted = false
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
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
                text = if (orderAccapted) orderAccaptedText else orderRequestedText,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
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
                            text = "Testing"
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
                            Text(text = "Testing")
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "Testing")
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "Testing")
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
                    if (orderAccapted) {
                        Box(modifier = Modifier.weight(1F))
                    } else {
                        ButtonNoIcon(
                            modifier = Modifier.weight(1F),
                            onClick = {
                                //
                            },
                            text = "Tolak",
                            textColor = MaterialTheme.colorScheme.primary,
                            backgroundColor = MaterialTheme.colorScheme.secondary,
                        )
                        Box(modifier = Modifier.weight(0.5F))
                    }
                    ButtonNoIcon(
                        modifier = Modifier.weight(1F),
                        onClick = {
                            //
                        },
                        text = if (orderAccapted) "Chat" else "Terima",
                        textColor = Color.White,
                        backgroundColor = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        }
    }
}
package skripsi.magfira.ambulanceapp.features.common.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldProfile(
    value: String,
    icon: ImageVector,
    iconEnd: ImageVector?,
    iconEndClicked: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon, contentDescription = icon.toString()
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                modifier = Modifier.weight(1F),
                text = value,
                style = MaterialTheme.typography.bodyLarge
            )
            iconEnd?.let {
                Icon(
                    modifier = Modifier.clickable { iconEndClicked() },
                    imageVector = it, contentDescription = iconEnd.toString()
                )
            }
        }
    }
}
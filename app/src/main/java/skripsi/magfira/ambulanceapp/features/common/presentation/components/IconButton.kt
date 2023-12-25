package skripsi.magfira.ambulanceapp.features.common.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun IconButton(
    modifier: Modifier,
    onClick: () -> Unit,
    icon: ImageVector,
    text: String,
    backgroundColor: Color,
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(24.dp),
        modifier = modifier
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .background(backgroundColor),
        )
        Icon(
            imageVector = icon,
            contentDescription = icon.toString(),
            tint = Color.White,
        )
    }
}

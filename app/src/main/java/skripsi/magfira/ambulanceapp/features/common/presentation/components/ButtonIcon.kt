package skripsi.magfira.ambulanceapp.features.common.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ButtonIcon(
    modifier: Modifier,
    onClick: () -> Unit,
    icon: ImageVector,
    text: String,
    textColor: Color,
    backgroundColor: Color,
    isLoading: Boolean = false
) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = Color.Transparent
    ) {
        Button(
            modifier = modifier,
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(backgroundColor),
            enabled = !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary
                )
            } else {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 6.dp, vertical = 4.dp),
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    color = textColor,
                )
                Icon(
                    imageVector = icon,
                    contentDescription = icon.toString(),
                    tint = Color.White,
                )
            }
        }
    }
}

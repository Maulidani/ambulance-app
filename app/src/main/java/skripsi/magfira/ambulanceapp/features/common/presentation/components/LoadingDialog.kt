package skripsi.magfira.ambulanceapp.features.common.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun LoadingDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        dismissButton = {
            // No dismiss button for loading dialog
        },
        confirmButton = {
            // No confirm button for loading dialog
        },
        title = {
            // You can optionally provide a title here
        },
        text = {
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                // Display your loading indicator here, e.g., CircularProgressIndicator
                CircularProgressIndicator(color = Color.Gray)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Loading...")
            }
        },
        modifier = modifier
    )
}
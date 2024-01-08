package skripsi.magfira.ambulanceapp.features.common.presentation.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun FileUpload(
    label: String,
    icon: ImageVector,
    selectedImage: () -> Uri?,
    onUploadClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(24.dp),
//        color = Color.Transparent
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (selectedImage() == null) {
                Text(text = label)
                Spacer(modifier = Modifier.size(8.dp))
            }
            Surface(
                shape = RoundedCornerShape(24.dp),
            ) {
                Image(
                    modifier = Modifier
                        .size(width = 100.dp, height = 120.dp)
                        .background(Color.Transparent),
                    imageVector = icon,
                    contentDescription = icon.toString(),
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.tint(Color.Gray)
                )
                AsyncImage(
                    modifier = Modifier
                        .size(width = 100.dp, height = 120.dp)
                        .clickable(onClick = onUploadClick),
                    model = selectedImage(),
                    contentDescription = selectedImage.toString(),
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}

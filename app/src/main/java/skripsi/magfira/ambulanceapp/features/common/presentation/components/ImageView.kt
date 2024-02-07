package skripsi.magfira.ambulanceapp.features.common.presentation.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import skripsi.magfira.ambulanceapp.R

@Composable
fun ImageView(
    context: Context,
    source: String,
    editable: Boolean,
    imageClicked: () -> Unit,
    iconEditClicked: () -> Unit
) {
    Row(modifier = Modifier.height(48.dp)) {
        Surface(
            modifier = Modifier.size(48.dp),
            shape = RoundedCornerShape(24.dp),
            color = Color.Transparent
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(source)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.logo_only),
                contentDescription = source,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(color = Color.Gray)
            )
        }
        if(editable) {
            val iconEdit = Icons.Default.Edit
            Box(
                Modifier
                    .fillMaxHeight()
                    .padding(end = 12.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Icon(
                    modifier = Modifier
                        .size(16.dp)
                        .clickable { iconEditClicked() },
                    imageVector = iconEdit,
                    contentDescription = iconEdit.toString()
                )

            }
        }
    }
}

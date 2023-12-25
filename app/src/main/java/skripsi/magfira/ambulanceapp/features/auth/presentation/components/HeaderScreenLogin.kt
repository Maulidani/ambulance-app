package skripsi.magfira.ambulanceapp.features.auth.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@Composable
fun HeaderScreenLogin(
    source: Painter
) {
    Box(
        modifier = Modifier
            .height(200.dp)
            .background(color = Color.Gray)
    ) {
        Image(
            painter = source,
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 150.dp)
                .padding(top = 24.dp),
            contentScale = ContentScale.Fit
        )
    }
}

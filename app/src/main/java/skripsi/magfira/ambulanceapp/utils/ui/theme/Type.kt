package skripsi.magfira.ambulanceapp.utils.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import skripsi.magfira.ambulanceapp.R

val poppins = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold)
)

val Typography = Typography(
    displayLarge = TextStyle(fontFamily = poppins, fontSize = 57.sp, fontWeight = FontWeight.Bold),
    displayMedium = TextStyle(fontFamily = poppins, fontSize = 45.sp, fontWeight = FontWeight.Bold),
    displaySmall = TextStyle(fontFamily = poppins, fontSize = 36.sp, fontWeight = FontWeight.Bold),

    headlineLarge = TextStyle(fontFamily = poppins, fontSize = 32.sp, fontWeight = FontWeight.Bold),
    headlineMedium = TextStyle(fontFamily = poppins, fontSize = 28.sp, fontWeight = FontWeight.Bold),
    headlineSmall = TextStyle(fontFamily = poppins, fontSize = 24.sp, fontWeight = FontWeight.Bold),

    titleLarge = TextStyle(fontFamily = poppins, fontSize = 22.sp, fontWeight = FontWeight.Medium),
    titleMedium = TextStyle(fontFamily = poppins, fontSize = 16.sp, fontWeight = FontWeight.Medium),
    titleSmall = TextStyle(fontFamily = poppins, fontSize = 12.sp, fontWeight = FontWeight.Medium),

    labelLarge = TextStyle(fontFamily = poppins, fontSize = 14.sp, fontWeight = FontWeight.Medium),
    labelMedium = TextStyle(fontFamily = poppins, fontSize = 12.sp, fontWeight = FontWeight.Medium),
    labelSmall = TextStyle(fontFamily = poppins, fontSize = 10.sp, fontWeight = FontWeight.Medium),

    bodyLarge = TextStyle(fontFamily = poppins, fontSize = 16.sp, fontWeight = FontWeight.Normal),
    bodyMedium = TextStyle(fontFamily = poppins, fontSize = 14.sp, fontWeight = FontWeight.Normal),
    bodySmall = TextStyle(fontFamily = poppins, fontSize = 12.sp, fontWeight = FontWeight.Normal),
)

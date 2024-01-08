package skripsi.magfira.ambulanceapp.features.auth.presentation.screens.yayasan

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import skripsi.magfira.ambulanceapp.R
import skripsi.magfira.ambulanceapp.features.common.presentation.components.AppBar
import skripsi.magfira.ambulanceapp.navigation.ScreenRouter

class DriversYayasanScreen(
    private val viewModel: Any?,
    private val navController: NavHostController?
) {
    @Composable
    fun MainScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        ) {
            AppBar(
                title = "Driver",
                iconBackClick = {
                    navController?.popBackStack()
                }
            )
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                val nameDriver = "Testing"
                val phoneDriver = "Testing"
                val nameYayasanDriver = "Testing"
                val imageDriver = painterResource(id = R.drawable.logo_only)
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController?.navigate(ScreenRouter.DriverProfile.route)
                        },
                    shape = RoundedCornerShape(24.dp),
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .background(color = MaterialTheme.colorScheme.background),
                    ) {
                        Row(
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.Start,
                        ) {
                            Row(
                                modifier = Modifier.weight(1F),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start,
                            ) {
                                Surface(
                                    modifier = Modifier
                                        .height(81.dp)
                                        .width(64.dp),
                                    shape = RoundedCornerShape(16.dp)
                                ) {
                                    Image(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(Color.Gray),
                                        painter = imageDriver,
                                        contentDescription = imageDriver.toString(),
                                        contentScale = ContentScale.Fit,
                                    )
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Column(
                                    modifier = Modifier
                                        .weight(1F),
                                ) {
                                    Text(
                                        text = nameDriver,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = phoneDriver,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            val iconEdit = Icons.Default.Edit
                            Icon(
                                modifier = Modifier
                                    .clickable {
                                        //
                                    },
                                imageVector = iconEdit, contentDescription = iconEdit.toString()
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DriversYayasanScreenPreview() {
    DriversYayasanScreen(null, null).MainScreen()
}

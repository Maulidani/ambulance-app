package skripsi.magfira.ambulanceapp.features.auth.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TabViewLogin(
    tabOptions: List<String>,
    selectedTab: String,
    onTabSelected: (String) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
    ) {
        TabRow(selectedTabIndex = tabOptions.indexOf(selectedTab), indicator = {}) {
            tabOptions.forEach { tab ->
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                ) {
                    Tab(
                        modifier = Modifier.background(
                            if (selectedTab == tab) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.background
                        ),
                        text = {
                            Text(
                                text = tab,
                                style = MaterialTheme.typography.titleMedium,
                                color = if (selectedTab == tab) MaterialTheme.colorScheme.background
                                else MaterialTheme.colorScheme.primary
                            )
                        },
                        selected = selectedTab == tab,
                        onClick = { onTabSelected(tab) }
                    )
                }
            }
        }
    }
}

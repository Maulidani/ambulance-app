package skripsi.magfira.ambulanceapp.presentation.auth.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import skripsi.magfira.ambulanceapp.R
import skripsi.magfira.ambulanceapp.presentation.auth.view_model.AuthViewModel

@Composable
fun LoginScreen(
    viewModel: AuthViewModel?, navController: NavHostController?
) {
    val tabOptions = listOf("Customer", "Driver", "Yayasan")
    var selectedTab by remember { mutableStateOf(tabOptions[0]) }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .height(200.dp)
                .background(color = Color.LightGray)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_only),
                contentDescription = "logo_only",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 150.dp)
                    .padding(top = 24.dp),
                contentScale = ContentScale.Fit
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            shape = RoundedCornerShape(24.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.secondary)
            ) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
                ) {
                    TabRow(selectedTabIndex = tabOptions.indexOf(selectedTab), indicator = {}) {
                        tabOptions.forEachIndexed { index, tab ->
                            Surface(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(24.dp),
                            ) {
                                Tab(modifier = Modifier.background(
                                    if (selectedTab == tab) MaterialTheme.colorScheme.primary
                                    else MaterialTheme.colorScheme.background
                                ),
                                    text = {
                                        Text(
                                            text = tab,
                                            style = MaterialTheme.typography.bodyLarge,
                                            fontWeight = FontWeight.Bold,
                                            color = if (selectedTab == tab) MaterialTheme.colorScheme.background
                                            else MaterialTheme.colorScheme.primary
                                        )
                                    },
                                    selected = selectedTab == tab,
                                    onClick = { selectedTab = tab })
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 24.dp)
                ) {
                    when (selectedTab) {
                        "Customer" -> CustomerScreen(selectedTab)
                        "Driver" -> DriverScreen(selectedTab)
                        "Yayasan" -> YayasanScreen(selectedTab)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerScreen(menu: String) {
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val roundedFormShape = RoundedCornerShape(24.dp)

    TextField(
        value = username.value,
        onValueChange = { username.value = it },
        label = { Text("Username") },
        shape = roundedFormShape,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = MaterialTheme.colorScheme.background,
            focusedBorderColor = Color.Transparent,
            focusedLabelColor = Color.Gray,
            unfocusedBorderColor = Color.Transparent,
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Username",
                tint = Color.Gray,
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp),
        singleLine = true
    )
    TextField(
        value = password.value,
        onValueChange = { password.value = it },
        label = { Text("Password") },
        shape = roundedFormShape,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = MaterialTheme.colorScheme.background,
            focusedBorderColor = Color.Transparent,
            focusedLabelColor = Color.Gray,
            unfocusedBorderColor = Color.Transparent,
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Username",
                tint = Color.Gray,
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp),
        singleLine = true,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Button(
            onClick = {
                /* Handle login */
            },
            shape = roundedFormShape
        ) {
            Text(
                text = "Masuk",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.background,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .background(MaterialTheme.colorScheme.primary),
            )
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = "Arrow Forward",
                tint = MaterialTheme.colorScheme.background,
            )
        }
    }
}

@Composable
fun DriverScreen(menu: String) {
    Text(
        text = "Driver Screen Content",
        color = Color.Black,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun YayasanScreen(menu: String) {
    Text(
        text = "Yayasan Screen Content",
        color = Color.Black,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.fillMaxSize()
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(null, null)
}

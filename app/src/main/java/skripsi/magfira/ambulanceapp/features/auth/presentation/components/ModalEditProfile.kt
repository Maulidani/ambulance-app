package skripsi.magfira.ambulanceapp.features.auth.presentation.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import skripsi.magfira.ambulanceapp.features.auth.domain.model.request.UpdateProfileRequest
import skripsi.magfira.ambulanceapp.features.auth.presentation.view_models.AuthViewModel
import skripsi.magfira.ambulanceapp.features.common.presentation.components.ButtonIcon
import skripsi.magfira.ambulanceapp.util.InputValidation
import skripsi.magfira.ambulanceapp.util.MessageUtils.MSG_INPUT_CONTAIN_SPACE
import skripsi.magfira.ambulanceapp.util.MessageUtils.MSG_INPUT_INVALID_EMAIL
import skripsi.magfira.ambulanceapp.util.MessageUtils.MSG_INPUT_INVALID_PHONE
import skripsi.magfira.ambulanceapp.util.MessageUtils.MSG_REQUIRED_FIELDS

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyModal(
    viewModel: AuthViewModel,
    context: Context,
    field: String,
    value: String,
    onValueChange: (String) -> Unit,
    icon: ImageVector,
    isModalOpen: Boolean,
    onCloseRequest: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var valueEdited by remember { mutableStateOf(value) }

    if (isModalOpen) {
        Dialog(
            onDismissRequest = {
                valueEdited = value
                onCloseRequest()
            },
        ) {
            Surface(
                shape = RoundedCornerShape(24.dp),
            ) {
                Column(
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.background)
                        .padding(24.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        text = "Edit Profil",
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Surface(
                        shape = RoundedCornerShape(24.dp),
                        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
                    ) {
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally),
                            singleLine = true,
                            value = valueEdited,
                            onValueChange = {
                                valueEdited = it
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                focusedBorderColor = Color.Transparent,
                                focusedLabelColor = Color.Gray,
                                unfocusedBorderColor = Color.Transparent,
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = icon.toString(),
                                    tint = Color.Gray,
                                )
                            },
                        )
                    }
                    Spacer(modifier = Modifier.height(48.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        ButtonIcon(
                            modifier = Modifier,
                            onClick = {
                                if (valueEdited.isNotEmpty()) {
                                    when (field) {
                                        "name" -> {
                                            val request = UpdateProfileRequest(
                                                name = valueEdited
                                            )
                                            viewModel.updateProfile(request)

                                            // Close modal
                                            onCloseRequest()
                                            onValueChange(valueEdited)
                                        }

                                        "email" -> {
                                            if (
                                                InputValidation.containsNoSpaces(valueEdited) &&
                                                InputValidation.isValidEmailFormat(valueEdited)
                                            ) {
                                                val request = UpdateProfileRequest(
                                                    email = valueEdited
                                                )
                                                viewModel.updateProfile(request)

                                                // Close modal
                                                onCloseRequest()
                                                onValueChange(valueEdited)
                                            } else {
                                                // Format invalid
                                                Toast.makeText(
                                                    context,
                                                    MSG_INPUT_INVALID_EMAIL,
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }

                                        "no_telp" -> {
                                            if (
                                                InputValidation.containsNoSpaces(valueEdited) &&
                                                InputValidation.isValidPhoneFormat(valueEdited)
                                            ) {
                                                val request = UpdateProfileRequest(
                                                    no_telp = valueEdited
                                                )
                                                viewModel.updateProfile(request)

                                                // Close modal
                                                onCloseRequest()
                                                onValueChange(valueEdited)
                                            } else {
                                                // Format invalid
                                                Toast.makeText(
                                                    context,
                                                    MSG_INPUT_INVALID_PHONE,
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }

                                        "alamat" -> {
                                            val request = UpdateProfileRequest(
                                                alamat = valueEdited
                                            )
                                            viewModel.updateProfile(request)

                                            // Close modal
                                            onCloseRequest()
                                            onValueChange(valueEdited)
                                        }

                                        "username" -> {
                                            if (InputValidation.containsNoSpaces(valueEdited)) {
                                                val request = UpdateProfileRequest(
                                                    username = valueEdited
                                                )
                                                viewModel.updateProfile(request)

                                                // Close modal
                                                onCloseRequest()
                                                onValueChange(valueEdited)
                                            } else {
                                                // Format invalid
                                                Toast.makeText(context, MSG_INPUT_CONTAIN_SPACE, Toast.LENGTH_SHORT)
                                                    .show()
                                            }
                                        }

                                        "password" -> {
                                            if (InputValidation.containsNoSpaces(valueEdited)) {
                                                val request = UpdateProfileRequest(
                                                    password = valueEdited,
                                                    password_confirmation = valueEdited
                                                )
                                                viewModel.updateProfile(request)

                                                // Close modal
                                                onCloseRequest()
                                                onValueChange(valueEdited)
                                            } else {
                                                // Format invalid
                                                Toast.makeText(context, MSG_INPUT_CONTAIN_SPACE, Toast.LENGTH_SHORT)
                                                    .show()
                                            }
                                        }
                                    }

                                } else {
                                    // Format invalid
                                    Toast.makeText(context, MSG_REQUIRED_FIELDS, Toast.LENGTH_SHORT)
                                        .show()
                                }
                            },
                            icon = Icons.Default.ArrowForwardIos,
                            text = "Edit",
                            textColor = Color.White,
                            backgroundColor = MaterialTheme.colorScheme.primary,
                        )
                    }
                }
            }
        }
    }
}
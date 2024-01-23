package skripsi.magfira.ambulanceapp.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import skripsi.magfira.ambulanceapp.features.common.presentation.components.LocationPermissionTextProvider
import skripsi.magfira.ambulanceapp.features.common.presentation.components.PermissionDialog
import skripsi.magfira.ambulanceapp.features.common.presentation.components.ReadExternalStoragePermissionTextProvider
import skripsi.magfira.ambulanceapp.features.common.presentation.components.ReadMediaImagePermissionTextProvider
import skripsi.magfira.ambulanceapp.features.common.presentation.view_models.PermissionViewModel

@Composable
fun requestAllPermissions(context: Context): Boolean {
    val activity = context as Activity
    val viewModel = viewModel<PermissionViewModel>()
    val dialogQueue = viewModel.visiblePermissionDialogQueue

    var permissionGranted = arrayListOf<Boolean>()

    val permissionsToRequest = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION,
            Manifest.permission.READ_MEDIA_IMAGES, // minSdkVersion = 33
        )
    } else {
        arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE, // maxSdkVersion = 32
        )
    }

    val multiplePermissionResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { perms ->
            permissionsToRequest.forEach { permission ->
                viewModel.onPermissionResult(
                    permission = permission,
                    isGranted = perms[permission] == true
                )
                permissionGranted.add(perms[permission] == true)
            }
        }
    )
    val openSettingsLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {}

    dialogQueue
        .reversed()
        .forEach { permission ->
            PermissionDialog(
                permissionTextProvider = when (permission) {
                    Manifest.permission.READ_EXTERNAL_STORAGE -> {
                        ReadExternalStoragePermissionTextProvider()
                    }

                    Manifest.permission.READ_MEDIA_IMAGES -> {
                        ReadMediaImagePermissionTextProvider()
                    }

                    Manifest.permission.ACCESS_FINE_LOCATION -> {
                        LocationPermissionTextProvider()
                    }

                    Manifest.permission.ACCESS_COARSE_LOCATION -> {
                        LocationPermissionTextProvider()
                    }

                    else -> return@forEach
                },
                isPermanentlyDeclined = !ActivityCompat.shouldShowRequestPermissionRationale(
                    activity, permission
                ),
                onDismiss = viewModel::dismissDialog,
                onOkClick = {
                    viewModel.dismissDialog()
                    multiplePermissionResultLauncher.launch(
                        arrayOf(permission)
                    )
                },
                onGoToAppSettingsClick = {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    intent.data = Uri.fromParts("package", activity.packageName, null)
                    openSettingsLauncher.launch(intent)
                }
            )
        }

    LaunchedEffect(viewModel) {
        multiplePermissionResultLauncher.launch(permissionsToRequest)
    }

    return !permissionGranted.contains(false)
}

@Composable
fun requestStoragePermissions(context: Context): Boolean {
    val activity = context as Activity
    val viewModel = viewModel<PermissionViewModel>()
    val dialogQueue = viewModel.visiblePermissionDialogQueue

    var permissionGranted = false

    val permissionToRequest = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        Manifest.permission.READ_MEDIA_IMAGES // minSdkVersion = 33
    } else {
        Manifest.permission.READ_EXTERNAL_STORAGE // maxSdkVersion = 32
    }

    val singlePermissionResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            viewModel.onPermissionResult(
                permission = permissionToRequest,
                isGranted = isGranted
            )
            permissionGranted = isGranted
        }
    )

    val openSettingsLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {}

    dialogQueue
        .reversed()
        .forEach { permission ->
            PermissionDialog(
                permissionTextProvider = when (permission) {
                    Manifest.permission.READ_EXTERNAL_STORAGE -> {
                        ReadExternalStoragePermissionTextProvider()
                    }
                    Manifest.permission.READ_MEDIA_IMAGES -> {
                        ReadMediaImagePermissionTextProvider()
                    }
                    else -> return@forEach
                },
                isPermanentlyDeclined = !ActivityCompat.shouldShowRequestPermissionRationale(
                    activity, permission
                ),
                onDismiss = viewModel::dismissDialog,
                onOkClick = {
                    viewModel.dismissDialog()
                    singlePermissionResultLauncher.launch(
                        permission
                    )
                },
                onGoToAppSettingsClick = {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    intent.data = Uri.fromParts("package", activity.packageName, null)
                    openSettingsLauncher.launch(intent)
                }
            )
        }

    LaunchedEffect(viewModel) {
        singlePermissionResultLauncher.launch(permissionToRequest)
    }

    return permissionGranted
}
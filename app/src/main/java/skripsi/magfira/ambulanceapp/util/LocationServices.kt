package skripsi.magfira.ambulanceapp.util

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.os.Looper
import android.util.Log
import androidx.compose.runtime.*
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import java.io.IOException
import java.util.Locale
import java.util.concurrent.TimeUnit

private val TAG = "LocationService"
private lateinit var locationProvider: FusedLocationProviderClient
private lateinit var locationCallback: LocationCallback

@SuppressLint("MissingPermission")
@Composable
fun LocationProvider(context: Context, onLocationResult: (LatLng) -> Unit) {
    if (!::locationProvider.isInitialized) {
        locationProvider = LocationServices.getFusedLocationProviderClient(context)

        // Declare latlng here to store the latest location
        var latlng by remember { mutableStateOf(LatLng(0.0, 0.0)) }

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                for (location in result.locations) {
                    latlng = LatLng(location.latitude, location.longitude)
                    onLocationResult(latlng)

                    Log.d(TAG, "${location.latitude},${location.longitude}")
                }
            }
        }
    }

}

@SuppressLint("MissingPermission")
fun locationUpdate() {
    val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 7000)
        .setMinUpdateIntervalMillis(5000)
        .setIntervalMillis(7000)
        .setMaxUpdateDelayMillis(10000)
        .build()

    locationProvider.requestLocationUpdates(
        locationRequest,
        locationCallback,
        Looper.getMainLooper()
    )

}

fun stopLocationUpdate() {
    try {
        //Removes all location updates for the given callback.
        Log.d(TAG, "stopLocationUpdate() called")
        if (::locationProvider.isInitialized && ::locationCallback.isInitialized) {
            Log.d(TAG, "Removing location updates")

            val removeTask = locationProvider.removeLocationUpdates(locationCallback)
            removeTask.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Location Callback removed.")
                } else {
                    Log.d(TAG, "Failed to remove Location Callback.")
                }
            }
        }
    } catch (se: SecurityException) {
        Log.e(TAG, "Failed to remove Location Callback.. $se")
    }
}

fun getReadableLocation(latitude: Double, longitude: Double, context: Context): String {
    var addressText = ""
    val geocoder = Geocoder(context, Locale.getDefault())

    try {

        val addresses = geocoder.getFromLocation(latitude, longitude, 1)

        if (addresses?.isNotEmpty() == true) {
            val address = addresses[0]
            addressText = "${address.subLocality}, ${address.locality}"
            // Use the addressText in your app
            Log.d(TAG, "getReadableLocation: $addressText")
        }

    } catch (e: IOException) {
        Log.d(TAG, "getReadableLocation: ${e.message}")

    }

    return addressText

}

fun parseLatLngFromString(locationString: String): LatLng? {
    val coordinates = locationString.split(", ")
    if (coordinates.size == 2) {
        val lat = coordinates[0].toDoubleOrNull()
        val lng = coordinates[1].toDoubleOrNull()
        if (lat != null && lng != null) {
            return LatLng(lat, lng)
        }
    }
    return null
}
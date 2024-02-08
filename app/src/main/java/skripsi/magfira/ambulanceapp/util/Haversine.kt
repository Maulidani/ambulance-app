package skripsi.magfira.ambulanceapp.util

import com.google.android.gms.maps.model.LatLng
import skripsi.magfira.ambulanceapp.features.order.domain.model.response.DriversData
import java.lang.Math.atan2
import java.lang.Math.cos
import java.lang.Math.sin
import java.lang.Math.sqrt

object CalculateLocations {

    fun haversine(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val R = 6371.0 // Earth radius in kilometers

        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)

        val a = sin(dLat / 2) * sin(dLat / 2) + cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
                sin(dLon / 2) * sin(dLon / 2)

        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        return R * c
    }

    fun haversine(location1: LatLng, location2: LatLng): Double {
        return haversine(location1.latitude, location1.longitude, location2.latitude, location2.longitude)
    }

    fun findClosestUser(target: LatLng, users: List<DriversData>): DriversData? {
        if (users.isEmpty()) return null

        var closestUser: DriversData? = null
        var minDistance = Double.MAX_VALUE

        for (user in users) {
            val distance = haversine(target, LatLng(user.lat?.toDouble() ?: 0.0, user.long?.toDouble() ?: 0.0))
            if (distance < minDistance) {
                minDistance = distance
                closestUser = user
            }
        }

        return closestUser
    }
}
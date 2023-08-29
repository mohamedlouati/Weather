package com.louati.weather.utils

import android.content.Context
import android.location.Address
import android.location.Geocoder
import com.google.android.gms.maps.model.LatLng
import java.util.Locale

class GetAddress {
    /**
     * @param context of calling fragment
     * @param latLng user current position
     * @return address of user
     */
    fun setAddress(context: Context, latLng: LatLng): String {
        var address = "Tunis"
        val geocoder = Geocoder(context, Locale.getDefault())
        var addresses: List<Address>? = null
        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (!addresses.isNullOrEmpty()) {
            addresses[0].locality?.let {
                address = it
            } ?: run {
                address = "Tunis"
            }
        }
        return address
    }
}
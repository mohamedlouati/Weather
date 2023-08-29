package com.louati.weather.modules.towns.userInterface.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.louati.weather.R
import com.louati.weather.databinding.ActivityAddTownBinding
import com.louati.weather.utils.GetAddress

class AddTownActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityAddTownBinding

    private var latLng: LatLng? = null
    private var townName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTownBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            latLng?.let {
                val data = Intent()
                data.putExtra("latitude", it.latitude)
                data.putExtra("longitude", it.longitude)
                data.putExtra("name", townName)
                setResult(RESULT_OK, data)
                finish()
            } ?: run {
                val data = Intent()
                data.putExtra("latitude", -34.0)
                data.putExtra("longitude", 151.0)
                data.putExtra("name", "Sydney")
                setResult(RESULT_OK, data)
                finish()
            }
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        mMap.setOnMapClickListener {
            mMap.clear()
            latLng = it
            townName = GetAddress().setAddress(this, it)
            mMap.addMarker(MarkerOptions().position(it).title(townName))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(it))
        }

    }
}
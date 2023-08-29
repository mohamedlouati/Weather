package com.louati.weather

import android.app.Application

class WeatherApplications: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: WeatherApplications
            private set
    }

}
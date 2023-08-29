package com.louati.repository.roomDataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.louati.repository.roomDataBase.townsRepository.dataSource.TownsDAO
import com.louati.repository.roomDataBase.townsRepository.models.TownsEntity

@Database(
    entities = [
        TownsEntity::class,
    ], version = 1, exportSchema = false
)
abstract class WeatherDataBase : RoomDatabase() {

    abstract fun townsDAO(): TownsDAO

    companion object {
        @Volatile
        private var INSTANCE: WeatherDataBase? = null
        fun getDatabase(context: Context): WeatherDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDataBase::class.java,
                    "weatherDatabase.db"
                ).fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }


}
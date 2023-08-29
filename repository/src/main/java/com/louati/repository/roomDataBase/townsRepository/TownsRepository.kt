package com.louati.repository.roomDataBase.townsRepository

import android.content.Context
import com.louati.repository.roomDataBase.WeatherDataBase
import com.louati.repository.roomDataBase.townsRepository.dataSource.TownsDAO
import com.louati.repository.roomDataBase.townsRepository.models.TownsEntity
import com.louati.utils.Resource

class TownsRepository(context: Context): TownsRepositoryInterface {

    private val townsDAO = WeatherDataBase.getDatabase(context).townsDAO()

    override suspend fun insertTown(
        name: String,
        latitude: Double,
        longitude: Double
    ): Long {
        val townsEntity = TownsEntity(
            name = name,
            latitude = latitude,
            longitude = longitude
        )
        return townsDAO.insertTown(townsEntity)
    }

    override suspend fun fetchTownsList(): Resource<List<TownsEntity>> {
        val townsList = townsDAO.getAllTowns()
        return townsList?.let { list ->
            if (list.isEmpty()) {
                Resource.Error()
            }else {
                Resource.Success(list)
            }
        } ?: run {
            Resource.Failure()
        }
    }
}
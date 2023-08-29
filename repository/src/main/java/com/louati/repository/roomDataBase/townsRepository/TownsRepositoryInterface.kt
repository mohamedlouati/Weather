package com.louati.repository.roomDataBase.townsRepository

import com.louati.repository.roomDataBase.townsRepository.models.TownsEntity
import com.louati.utils.Resource

interface TownsRepositoryInterface {

    suspend fun insertTown(
        name: String,
        latitude: Double,
        longitude: Double
    ): Long

    suspend fun fetchTownsList(): Resource<List<TownsEntity>>

}
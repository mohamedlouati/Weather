package com.louati.repository.roomDataBase.townsRepository.dataSource

import androidx.room.*
import com.louati.repository.roomDataBase.townsRepository.models.TownsEntity

@Dao
interface TownsDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTown(town: TownsEntity): Long

    @Query("SELECT * FROM table_towns ORDER BY id ASC")
    fun getAllTowns(): List<TownsEntity>?

}
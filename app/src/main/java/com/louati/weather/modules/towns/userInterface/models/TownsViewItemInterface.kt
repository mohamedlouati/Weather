package com.louati.weather.modules.towns.userInterface.models

import com.louati.repository.roomDataBase.townsRepository.models.TownsEntity

interface TownsViewItemInterface {
    val id: Int
    val name: String
    val latitude: Double
    val longitude: Double
}

data class TownsViewItem(
    override val id: Int,
    override val name: String,
    override val latitude: Double,
    override val longitude: Double
): TownsViewItemInterface

fun TownsEntity.convertToTownsViewItem(): TownsViewItemInterface =
    TownsViewItem(
        id = this.id,
        name = this.name,
        latitude = this.latitude,
        longitude = this.longitude
    )
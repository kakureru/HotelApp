package com.example.rooms.presentation

import com.example.core.toFormattedPrice
import com.example.rooms.domain.model.Room

data class RoomItem(
    val id: Int,
    val name: String,
    val price: String,
    val pricePer: String,
    val peculiarities: List<String>,
    val imageUrls: List<String>,
)

fun Room.toRoomItem() = RoomItem(
    id = id,
    name = name,
    price = price.toFormattedPrice(),
    pricePer = pricePer.lowercase(),
    peculiarities = peculiarities,
    imageUrls = imageUrls,
)
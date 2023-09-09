package com.example.rooms.data.repository

import com.example.rooms.data.api.RoomsApi
import com.example.rooms.data.model.RoomsResponse
import com.example.rooms.domain.model.Room
import com.example.rooms.domain.repository.RoomsRepository

class RoomsRepositoryImpl(
    private val roomsApi: RoomsApi,
) : RoomsRepository {

    override suspend fun getHotelRooms(hotelId: Int): List<Room> = roomsApi.getRooms().rooms.map { it.toDomain() }

    private fun RoomsResponse.RoomDto.toDomain() = Room(
        id = id ?: 0,
        name = name ?: "",
        price = price ?: 0,
        pricePer = pricePer ?: "",
        peculiarities = peculiarities ?: emptyList(),
        imageUrls = imageUrls ?: emptyList(),
    )
}
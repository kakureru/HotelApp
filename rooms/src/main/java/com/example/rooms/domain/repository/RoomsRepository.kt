package com.example.rooms.domain.repository

import com.example.rooms.domain.model.Room

interface RoomsRepository {

    suspend fun getHotelRooms(hotelId: Int): List<Room>
}
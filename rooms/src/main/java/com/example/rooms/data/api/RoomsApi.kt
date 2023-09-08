package com.example.rooms.data.api

import com.example.rooms.data.model.RoomsResponse

interface RoomsApi {
    suspend fun getRooms(): RoomsResponse
}
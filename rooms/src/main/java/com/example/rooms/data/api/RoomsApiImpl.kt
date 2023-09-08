package com.example.rooms.data.api

import com.example.rooms.data.model.RoomsResponse

class RoomsApiImpl(
    private val roomsApiRetrofit: RoomsApiRetrofit,
) : RoomsApi {
    override suspend fun getRooms(): RoomsResponse = roomsApiRetrofit.getRooms()
}
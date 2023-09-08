package com.example.rooms.data.api

import com.example.rooms.data.model.RoomsResponse
import retrofit2.http.GET

interface RoomsApiRetrofit {

    @GET("f9a38183-6f95-43aa-853a-9c83cbb05ecd")
    suspend fun getRooms(): RoomsResponse
}
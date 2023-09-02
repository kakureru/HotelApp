package com.example.hotelapp.data.api

import com.example.hotelapp.data.api.model.BookingResponse
import com.example.hotelapp.data.api.model.HotelResponse
import com.example.hotelapp.data.api.model.RoomsResponse
import retrofit2.http.GET

interface HotelApiRetrofit {
    @GET("35e0d18e-2521-4f1b-a575-f0fe366f66e3")
    suspend fun getHotel(): HotelResponse

    @GET("f9a38183-6f95-43aa-853a-9c83cbb05ecd")
    suspend fun getRooms(): RoomsResponse

    @GET("e8868481-743f-4eb2-a0d7-2bc4012275c8")
    suspend fun getBooking(): BookingResponse
}
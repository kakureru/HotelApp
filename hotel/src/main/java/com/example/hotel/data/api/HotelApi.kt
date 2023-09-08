package com.example.hotel.data.api

import com.example.hotel.data.api.model.HotelResponse

interface HotelApi {

    suspend fun getHotel(): HotelResponse
}
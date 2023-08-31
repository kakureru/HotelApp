package com.example.hotelapp.data.api

import com.example.hotelapp.data.api.model.BookingResponse
import com.example.hotelapp.data.api.model.HotelResponse
import com.example.hotelapp.data.api.model.RoomsResponse

interface HotelApi {
    suspend fun getHotel(): HotelResponse
    suspend fun getRooms(): RoomsResponse
    suspend fun getBooking(): BookingResponse
}
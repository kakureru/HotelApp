package com.example.hotelapp.data.api

import com.example.hotelapp.data.api.model.BookingResponse
import com.example.hotelapp.data.api.model.HotelResponse
import com.example.hotelapp.data.api.model.RoomsResponse

class HotelApiImpl(
    private val hotelApiRetrofit: HotelApiRetrofit,
) : HotelApi {
    override suspend fun getHotel(): HotelResponse = hotelApiRetrofit.getHotel()
    override suspend fun getRooms(): RoomsResponse = hotelApiRetrofit.getRooms()
    override suspend fun getBooking(): BookingResponse = hotelApiRetrofit.getBooking()
}
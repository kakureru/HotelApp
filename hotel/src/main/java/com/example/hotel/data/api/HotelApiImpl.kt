package com.example.hotel.data.api

import com.example.hotel.data.api.model.HotelResponse

class HotelApiImpl(
    private val hotelApiRetrofit: HotelApiRetrofit
) : HotelApi {

    override suspend fun getHotel(): HotelResponse = hotelApiRetrofit.getHotel()
}
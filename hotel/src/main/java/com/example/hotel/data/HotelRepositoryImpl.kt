package com.example.hotel.data

import com.example.hotel.data.api.HotelApi
import com.example.hotel.data.api.model.HotelResponse
import com.example.hotel.domain.Hotel
import com.example.hotel.domain.HotelRepository

class HotelRepositoryImpl(
    private val hotelApi: HotelApi
) : HotelRepository {

    override suspend fun getHotel(): Hotel = hotelApi.getHotel().toDomain()

    private fun HotelResponse.toDomain() = Hotel(
        id = id ?: 0,
        name = name ?: "",
        address = address ?: "",
        minimalPrice = minimalPrice ?: 0,
        priceForIt = priceForIt ?: "",
        rating = rating ?: 0,
        ratingName = ratingName ?: "",
        imageUrls = imageUrls ?: emptyList(),
        description = aboutTheHotel?.description ?: "",
        peculiarities = aboutTheHotel?.peculiarities ?: emptyList(),
    )
}
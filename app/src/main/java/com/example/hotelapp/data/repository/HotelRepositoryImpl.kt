package com.example.hotelapp.data.repository

import com.example.hotelapp.data.api.HotelApi
import com.example.hotelapp.data.api.model.BookingResponse
import com.example.hotelapp.data.api.model.HotelResponse
import com.example.hotelapp.data.api.model.RoomsResponse
import com.example.hotelapp.domain.HotelRepository
import com.example.hotelapp.domain.model.Booking
import com.example.hotelapp.domain.model.Hotel
import com.example.hotelapp.domain.model.Room

class HotelRepositoryImpl(
    private val hotelApi: HotelApi,
) : HotelRepository {

    override suspend fun getHotel(): Hotel = hotelApi.getHotel().toDomain()

    override suspend fun getHotelRooms(): List<Room> = hotelApi.getRooms().rooms.map { it.toDomain() }

    override suspend fun getBooking(): Booking = hotelApi.getBooking().toDomain()

    private fun RoomsResponse.RoomDto.toDomain() = Room(
        id = id ?: 0,
        name = name ?: "",
        price = price ?: 0,
        pricePer = pricePer ?: "",
        peculiarities = peculiarities ?: emptyList(),
        imageUrls = imageUrls ?: emptyList(),
    )

    private fun BookingResponse.toDomain() = Booking(
        id = id ?: 0,
        hotelName = hotelName ?: "",
        hotelAddress = hotelAddress ?: "",
        rating = rating ?: 0,
        ratingName = ratingName ?: "",
        departure = departure ?: "",
        arrivalCountry = arrivalCountry ?: "",
        tourDateStart = tourDateStart ?: "",
        tourDateStop = tourDateStop ?: "",
        numberOfNights = numberOfNights ?: 0,
        room = room ?: "",
        nutrition = nutrition ?: "",
        tourPrice = tourPrice ?: 0,
        fuelCharge = fuelCharge ?: 0,
        serviceCharge = serviceCharge ?: 0,
    )

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
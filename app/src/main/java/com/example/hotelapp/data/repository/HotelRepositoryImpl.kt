package com.example.hotelapp.data.repository

import com.example.hotelapp.data.api.HotelApi
import com.example.hotelapp.data.api.model.BookingResponse
import com.example.hotelapp.data.api.model.HotelResponse
import com.example.hotelapp.data.api.model.RoomDto
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
        id = id,
        name = name,
        price = price,
        pricePer = pricePer,
        peculiarities = peculiarities,
        imageUrls = imageUrls,
    )

    private fun BookingResponse.toDomain() = Booking(
        id = id,
        hotelName = hotelName,
        hotelAddress = hotelAddress,
        rating = rating,
        ratingName = ratingName,
        departure = departure,
        arrivalCountry = arrivalCountry,
        tourDateStart = tourDateStart,
        tourDateStop = tourDateStop,
        numberOfNights = numberOfNights,
        room = room,
        nutrition = nutrition,
        tourPrice = tourPrice,
        fuelCharge = fuelCharge,
        serviceCharge = serviceCharge,
    )

    private fun HotelResponse.toDomain() = Hotel(
        id = id,
        name = name,
        address = address,
        minimalPrice = minimalPrice,
        priceForIt = priceForIt,
        rating = rating,
        ratingName = ratingName,
        imageUrls = imageUrls,
        aboutTheHotel = aboutTheHotel,
        description = aboutTheHotel.description,
        peculiarities = aboutTheHotel.peculiarities,
    )
}
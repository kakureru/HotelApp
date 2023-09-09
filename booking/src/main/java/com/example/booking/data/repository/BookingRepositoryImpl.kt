package com.example.booking.data.repository

import com.example.booking.data.api.BookingApi
import com.example.booking.data.api.model.BookingResponse
import com.example.booking.domain.model.Booking
import com.example.booking.domain.repository.BookingRepository

class BookingRepositoryImpl(
    private val bookingApi: BookingApi,
) : BookingRepository {

    override suspend fun getBooking(roomId: Int): Booking = bookingApi.getBooking().toDomain()

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
}
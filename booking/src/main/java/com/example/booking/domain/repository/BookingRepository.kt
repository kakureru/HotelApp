package com.example.booking.domain.repository

import com.example.booking.domain.model.Booking

interface BookingRepository {
    suspend fun getBooking(): Booking
}
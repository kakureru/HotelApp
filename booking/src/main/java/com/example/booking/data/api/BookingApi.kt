package com.example.booking.data.api

import com.example.booking.data.api.model.BookingResponse

interface BookingApi {

    suspend fun getBooking(): BookingResponse
}
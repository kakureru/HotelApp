package com.example.booking.data.api

import com.example.booking.data.api.model.BookingResponse

class BookingApiImpl(
    private val bookingApiRetrofit: BookingApiRetrofit,
) : BookingApi {

    override suspend fun getBooking(): BookingResponse = bookingApiRetrofit.getBooking()
}
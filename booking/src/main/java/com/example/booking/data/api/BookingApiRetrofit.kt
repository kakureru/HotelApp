package com.example.booking.data.api

import com.example.booking.data.api.model.BookingResponse
import retrofit2.http.GET

interface BookingApiRetrofit {

    @GET("e8868481-743f-4eb2-a0d7-2bc4012275c8")
    suspend fun getBooking(): BookingResponse
}
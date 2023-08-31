package com.example.hotelapp.domain

import com.example.hotelapp.domain.model.Booking
import com.example.hotelapp.domain.model.Hotel
import com.example.hotelapp.domain.model.Room

interface HotelRepository {
    suspend fun getHotel(): Hotel
    suspend fun getHotelRooms(): List<Room>
    suspend fun getBooking(): Booking
}
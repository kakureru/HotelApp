package com.example.hotel.domain

interface HotelRepository {
    suspend fun getHotel(): Hotel
}
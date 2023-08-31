package com.example.hotelapp.domain.model

import com.example.hotelapp.data.api.model.HotelResponse

class Hotel(
    val id: Int,
    val name: String,
    val address: String,
    val minimalPrice: Int,
    val priceForIt: String,
    val rating: Int,
    val ratingName: String,
    val imageUrls: List<String>,
    val aboutTheHotel: HotelResponse.HotelDescription,
    val description: String,
    val peculiarities: List<String>,
)
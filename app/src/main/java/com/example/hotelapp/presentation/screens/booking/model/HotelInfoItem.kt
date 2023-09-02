package com.example.hotelapp.presentation.screens.booking.model

import com.example.hotelapp.domain.model.Booking

class HotelInfoItem(
    val name: String,
    val rating: String,
    val address: String,
)

fun Booking.toHotelInfoItem() = HotelInfoItem(
    name = hotelName,
    rating = "$rating $ratingName",
    address = hotelAddress,
)
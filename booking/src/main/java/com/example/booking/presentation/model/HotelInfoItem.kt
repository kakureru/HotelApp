package com.example.booking.presentation.model

import com.example.booking.domain.model.Booking

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
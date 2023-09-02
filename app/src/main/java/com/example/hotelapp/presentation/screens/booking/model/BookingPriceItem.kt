package com.example.hotelapp.presentation.screens.booking.model

import com.example.hotelapp.domain.model.Booking

class BookingPriceItem(
    val tour: String,
    val fuel: String,
    val service: String,
    val total: String,
)

fun Booking.toBookingPriceItem() = BookingPriceItem(
    tour = "$tourPrice",
    fuel = "$fuelCharge",
    service = "$serviceCharge",
    total = "${tourPrice + fuelCharge + serviceCharge}",
)
package com.example.booking.presentation.model

import com.example.booking.domain.model.Booking
import com.example.core.toFormattedPrice

class BookingPriceItem(
    val tour: String,
    val fuel: String,
    val service: String,
    val total: String,
)

fun Booking.toBookingPriceItem() = BookingPriceItem(
    tour = tourPrice.toFormattedPrice(),
    fuel = fuelCharge.toFormattedPrice(),
    service = serviceCharge.toFormattedPrice(),
    total = (tourPrice + fuelCharge + serviceCharge).toFormattedPrice(),
)
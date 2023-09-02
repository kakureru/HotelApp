package com.example.hotelapp.presentation.screens.booking.model

import com.example.hotelapp.domain.model.Booking

class BookingDataItem(
    val departure: String,
    val arrivalCountry: String,
    val dates: String,
    val numberOfNights: String,
    val hotelName: String,
    val room: String,
    val nutrition: String,
)

fun Booking.toBookingDataItem() = BookingDataItem(
    departure = departure,
    arrivalCountry = arrivalCountry,
    dates = "$tourDateStart - $tourDateStop",
    numberOfNights = "$numberOfNights",
    hotelName = hotelName,
    room = room,
    nutrition = nutrition,
)
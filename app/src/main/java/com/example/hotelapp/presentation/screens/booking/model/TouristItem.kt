package com.example.hotelapp.presentation.screens.booking.model

data class TouristItem(
    val ordinal: Int,
    val ordinalName: String,
    val name: String = "",
    val secondName: String = "",
    val birthdayDate: String = "",
    val citizenship: String = "",
    val passportNumber: String = "",
    val passportExpirationDate: String = "",
    val isExpanded: Boolean = true,
)
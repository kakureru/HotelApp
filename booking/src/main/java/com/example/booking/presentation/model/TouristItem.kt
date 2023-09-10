package com.example.booking.presentation.model

data class TouristItem(
    val ordinal: Int,
    val ordinalName: String,
    val name: InputState = InputState.Accepted(""),
    val secondName: InputState = InputState.Accepted(""),
    val birthdayDate: InputState = InputState.Accepted(""),
    val citizenship: InputState = InputState.Accepted(""),
    val passportNumber: InputState = InputState.Accepted(""),
    val passportExpirationDate: InputState = InputState.Accepted(""),
    val isExpanded: Boolean = true,
)
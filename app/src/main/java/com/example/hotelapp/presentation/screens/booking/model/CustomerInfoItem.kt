package com.example.hotelapp.presentation.screens.booking.model

data class CustomerInfoItem(
    val phone: InputState = InputState.Normal(""),
    val mail: InputState = InputState.Normal(""),
)
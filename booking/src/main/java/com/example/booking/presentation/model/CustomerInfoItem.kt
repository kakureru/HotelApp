package com.example.booking.presentation.model

data class CustomerInfoItem(
    val phone: InputState = InputState.Normal(""),
    val mail: InputState = InputState.Normal(""),
)
package com.example.booking.presentation.model

data class CustomerInfoItem(
    val phone: InputState = InputState.Accepted(""),
    val mail: InputState = InputState.Accepted(""),
)
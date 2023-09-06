package com.example.hotelapp.presentation.screens.booking

import com.example.hotelapp.presentation.recyclerview.delegate.DelegateItem

data class BookingUiState(
    val data: List<DelegateItem> = emptyList(),
    val totalCharge: String,
)
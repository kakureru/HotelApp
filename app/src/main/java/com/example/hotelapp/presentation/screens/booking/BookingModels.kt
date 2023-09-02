package com.example.hotelapp.presentation.screens.booking

import com.example.hotelapp.presentation.recyclerview.adapter.DelegateItem

data class BookingUiState(
    val data: List<DelegateItem> = emptyList(),
)
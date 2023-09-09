package com.example.booking.presentation

import com.example.core.recyclerview.delegate.DelegateItem

data class BookingUiState(
    val data: List<DelegateItem> = emptyList(),
    val totalCharge: String = "",
)

sealed class BookingEffect {
    class Error(val msgRes: Int) : BookingEffect()
}
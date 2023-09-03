package com.example.hotelapp.presentation.screens.rooms

import com.example.hotelapp.domain.model.Room

data class RoomsUiState(
    val data: List<Room> = emptyList()
)
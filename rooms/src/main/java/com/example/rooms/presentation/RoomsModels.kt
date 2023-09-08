package com.example.rooms.presentation

import com.example.rooms.domain.model.Room

data class RoomsUiState(
    val data: List<Room> = emptyList()
)
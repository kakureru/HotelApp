package com.example.rooms.presentation

data class RoomsUiState(
    val hotelName: String = "",
    val rooms: List<RoomItem> = emptyList()
)

sealed class RoomsEffect {
    class Error(val msgRes: Int) : RoomsEffect()
}
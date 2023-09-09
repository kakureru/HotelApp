package com.example.rooms.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotel.domain.HotelRepository
import com.example.rooms.domain.repository.RoomsRepository
import com.example.rooms.presentation.navigation.RoomsNavigation
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class RoomsViewModel(
    private val hotelId: Int,
    private val roomsNavigation: RoomsNavigation,
    private val roomsRepository: RoomsRepository,
    private val hotelRepository: HotelRepository,
) : ViewModel() {

    private val roomsFlow = flow { emit(roomsRepository.getHotelRooms(hotelId)) }
    private val hotelNameFlow = flow { emit(hotelRepository.getHotel().name) }

    val uiState: StateFlow<RoomsUiState> = combine(roomsFlow, hotelNameFlow) { rooms, hotelName ->
        RoomsUiState(
            hotelName = hotelName,
            rooms = rooms.map { it.toRoomItem() }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = RoomsUiState()
    )
    fun onChooseRoomClick(roomId: Int) {
        roomsNavigation.navigateToBooking(roomId)
    }

    fun onBackClick() {
        roomsNavigation.exit()
    }
}
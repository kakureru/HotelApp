package com.example.rooms.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rooms.domain.repository.RoomsRepository
import com.example.rooms.presentation.navigation.RoomsNavigation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RoomsViewModel(
    private val roomsNavigation: RoomsNavigation,
    private val roomsRepository: RoomsRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(RoomsUiState())
    val uiState: StateFlow<RoomsUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { it.copy(data = roomsRepository.getHotelRooms()) }
        }
    }

    fun onChooseRoomClick(roomId: Int) {
        roomsNavigation.navigateToBooking(roomId)
    }

    fun onBackClick() {
        roomsNavigation.exit()
    }
}
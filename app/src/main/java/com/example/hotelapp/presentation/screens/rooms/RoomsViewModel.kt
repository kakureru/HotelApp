package com.example.hotelapp.presentation.screens.rooms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelapp.domain.HotelRepository
import com.example.hotelapp.presentation.Screens
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RoomsViewModel(
    private val router: Router,
    private val hotelRepository: HotelRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(RoomsUiState())
    val uiState: StateFlow<RoomsUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { it.copy(data = hotelRepository.getHotelRooms()) }
        }
    }

    fun onChooseRoomClick(roomId: Int) {
        router.navigateTo(Screens.Booking())
    }
}
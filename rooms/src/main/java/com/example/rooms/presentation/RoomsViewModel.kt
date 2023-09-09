package com.example.rooms.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.runCatchingNonCancellation
import com.example.hotel.domain.HotelRepository
import com.example.rooms.R
import com.example.rooms.domain.repository.RoomsRepository
import com.example.rooms.presentation.navigation.RoomsNavigation
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class RoomsViewModel(
    private val hotelId: Int,
    private val roomsNavigation: RoomsNavigation,
    private val roomsRepository: RoomsRepository,
    private val hotelRepository: HotelRepository,
) : ViewModel() {

    val uiState: StateFlow<RoomsUiState> = combine(
        loadRooms(),
        loadHotelName()
    ) { rooms, hotelName ->
        RoomsUiState(
            hotelName = hotelName,
            rooms = rooms.map { it.toRoomItem() }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = RoomsUiState()
    )

    private val _effects = MutableSharedFlow<RoomsEffect>()
    val effects: SharedFlow<RoomsEffect> = _effects.asSharedFlow()

    private fun loadRooms() = flow {
        runCatchingNonCancellation {
            emit(roomsRepository.getHotelRooms(hotelId))
        }.onFailure {
            _effects.emit(RoomsEffect.Error(R.string.error_failed_to_load_rooms_data))
        }
    }

    private fun loadHotelName() = flow {
        runCatchingNonCancellation {
            emit(hotelRepository.getHotel().name)
        }.onFailure {
            _effects.emit(RoomsEffect.Error(com.example.hotel.R.string.error_failed_to_load_hotel_data))
        }
    }

    fun onChooseRoomClick(roomId: Int) {
        roomsNavigation.navigateToBooking(roomId)
    }

    fun onBackClick() {
        roomsNavigation.exit()
    }
}
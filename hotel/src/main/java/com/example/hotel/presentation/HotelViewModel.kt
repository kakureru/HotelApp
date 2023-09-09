package com.example.hotel.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.runCatchingNonCancellation
import com.example.hotel.R
import com.example.hotel.domain.HotelRepository
import com.example.hotel.presentation.feature.FeatureItem
import com.example.hotel.presentation.navigation.HotelNavigation
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

private val features = listOf( // FIXME
    FeatureItem(
        id = 0,
        name = "Удобства",
        description = "Самое необходимое",
        iconRes = R.drawable.ic_emoji_happy_square,
    ),
    FeatureItem(
        id = 1,
        name = "Что включено",
        description = "Самое необходимое",
        iconRes = R.drawable.ic_tick_square,
    ),
    FeatureItem(
        id = 2,
        name = "Что не включено",
        description = "Самое необходимое",
        iconRes = R.drawable.ic_close_square,
    ),
)

class HotelViewModel(
    private val hotelNavigation: HotelNavigation,
    private val hotelRepository: HotelRepository,
) : ViewModel() {

    val uiState: StateFlow<HotelUiState> = loadHotel()
        .map {
            it.toUiState(features)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = HotelUiState(),
        )

    private val _effects = MutableSharedFlow<HotelEffect>()
    val effects: SharedFlow<HotelEffect> = _effects.asSharedFlow()

    private fun loadHotel() = flow {
        runCatchingNonCancellation {
            emit(hotelRepository.getHotel())
        }.onFailure {
            _effects.emit(HotelEffect.Error(R.string.error_failed_to_load_hotel_data))
        }
    }

    fun onChooseRoomClick() = viewModelScope.launch {
        runCatchingNonCancellation {
            hotelNavigation.navigateToRooms(hotelRepository.getHotel().id)
        }.onFailure {
            _effects.emit(HotelEffect.Error(R.string.error_failed_to_load_hotel_data))
        }
    }

    fun onFeatureClick(featureId: Int) = Unit
}
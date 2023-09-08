package com.example.hotel.presentation

import androidx.lifecycle.ViewModel
import com.example.hotel.domain.HotelRepository
import com.example.hotel.presentation.navigation.HotelNavigation

class HotelViewModel(
    private val hotelNavigation: HotelNavigation,
    private val hotelRepository: HotelRepository,
) : ViewModel() {

    fun onChooseRoomClick() {
        hotelNavigation.navigateToRooms()
    }
}
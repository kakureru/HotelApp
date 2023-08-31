package com.example.hotelapp.presentation.screens.hotel

import androidx.lifecycle.ViewModel
import com.example.hotelapp.domain.HotelRepository
import com.example.hotelapp.presentation.Screens
import com.github.terrakok.cicerone.Router

class HotelViewModel(
    private val router: Router,
    private val hotelRepository: HotelRepository,
) : ViewModel() {

    fun onChooseRoomClick() {
        router.navigateTo(Screens.Rooms())
    }
}
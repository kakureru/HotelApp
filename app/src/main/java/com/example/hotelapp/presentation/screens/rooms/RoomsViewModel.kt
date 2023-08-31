package com.example.hotelapp.presentation.screens.rooms

import androidx.lifecycle.ViewModel
import com.example.hotelapp.domain.HotelRepository
import com.github.terrakok.cicerone.Router

class RoomsViewModel(
    private val router: Router,
    private val hotelRepository: HotelRepository,
) : ViewModel() {
}
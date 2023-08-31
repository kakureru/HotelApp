package com.example.hotelapp.presentation.screens.booking

import androidx.lifecycle.ViewModel
import com.example.hotelapp.domain.HotelRepository
import com.github.terrakok.cicerone.Router

class BookingViewModel(
    private val router: Router,
    private val hotelRepository: HotelRepository,
) : ViewModel() {
}
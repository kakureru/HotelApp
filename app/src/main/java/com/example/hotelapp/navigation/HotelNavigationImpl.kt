package com.example.hotelapp.navigation

import com.example.hotel.presentation.navigation.HotelNavigation
import com.github.terrakok.cicerone.Router

class HotelNavigationImpl(
    private val router: Router,
) : HotelNavigation {
    override fun navigateToRooms(hotelId: Int) {
        router.navigateTo(Screens.Rooms(hotelId))
    }
}
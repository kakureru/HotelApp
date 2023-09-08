package com.example.hotelapp.navigation

import com.example.rooms.presentation.navigation.RoomsNavigation
import com.github.terrakok.cicerone.Router

class RoomsNavigationImpl(
    private val router: Router,
) : RoomsNavigation {
    override fun navigateToBooking(roomId: Int) {
        router.navigateTo(Screens.Booking(roomId))
    }

    override fun exit() {
        router.exit()
    }
}
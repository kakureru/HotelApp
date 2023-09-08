package com.example.hotelapp.navigation

import com.example.booking.presentation.navigation.BookingNavigation
import com.github.terrakok.cicerone.Router

class BookingNavigationImpl(
    private val router: Router,
) : BookingNavigation {

    override fun navigateToPayment() {
        router.navigateTo(Screens.Success())
    }

    override fun exit() {
        router.exit()
    }
}
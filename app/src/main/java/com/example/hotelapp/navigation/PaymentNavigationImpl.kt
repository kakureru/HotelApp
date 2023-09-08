package com.example.hotelapp.navigation

import com.example.payment.presentation.navigation.PaymentNavigation
import com.github.terrakok.cicerone.Router

class PaymentNavigationImpl(
    private val router: Router,
) : PaymentNavigation {
    override fun navigateToHotel() {
        router.navigateTo(Screens.Hotel())
    }

    override fun exit() {
        router.exit()
    }
}
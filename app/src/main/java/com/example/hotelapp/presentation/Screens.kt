package com.example.hotelapp.presentation

import com.example.hotelapp.presentation.screens.booking.BookingFragment
import com.example.hotelapp.presentation.screens.hotel.HotelFragment
import com.example.hotelapp.presentation.screens.rooms.RoomsFragment
import com.example.hotelapp.presentation.screens.success.SuccessFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun Booking() = FragmentScreen {
        BookingFragment()
    }

    fun Hotel() = FragmentScreen {
        HotelFragment()
    }

    fun Rooms() = FragmentScreen {
        RoomsFragment()
    }

    fun Success() = FragmentScreen {
        SuccessFragment()
    }
}
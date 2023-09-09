package com.example.hotelapp.navigation

import com.example.booking.presentation.BookingFragment
import com.example.hotel.presentation.HotelFragment
import com.example.payment.presentation.PaymentFragment
import com.example.rooms.presentation.RoomsFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun Booking(roomId: Int) = FragmentScreen {
        BookingFragment.newInstance(roomId)
    }

    fun Hotel() = FragmentScreen {
        HotelFragment()
    }

    fun Rooms(hotelId: Int) = FragmentScreen {
        RoomsFragment.newInstance(hotelId)
    }

    fun Success() = FragmentScreen {
        PaymentFragment()
    }
}
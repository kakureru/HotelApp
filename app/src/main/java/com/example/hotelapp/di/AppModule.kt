package com.example.hotelapp.di

import com.example.booking.data.repository.BookingRepositoryImpl
import com.example.booking.presentation.BookingViewModel
import com.example.hotel.data.HotelRepositoryImpl
import com.example.hotel.presentation.HotelViewModel
import com.example.hotelapp.navigation.BookingNavigationImpl
import com.example.hotelapp.navigation.HotelNavigationImpl
import com.example.hotelapp.navigation.PaymentNavigationImpl
import com.example.hotelapp.navigation.RoomsNavigationImpl
import com.example.payment.presentation.PaymentViewModel
import com.example.rooms.data.repository.RoomsRepositoryImpl
import com.example.rooms.presentation.RoomsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        BookingViewModel(
            roomId = it.get(),
            bookingNavigation = get<BookingNavigationImpl>(),
            bookingRepository = get<BookingRepositoryImpl>()
        )
    }

    viewModel {
        HotelViewModel(
            hotelNavigation = get<HotelNavigationImpl>(),
            hotelRepository = get<HotelRepositoryImpl>()
        )
    }

    viewModel {
        RoomsViewModel(
            hotelId = it.get(),
            roomsNavigation = get<RoomsNavigationImpl>(),
            roomsRepository = get<RoomsRepositoryImpl>(),
            hotelRepository = get<HotelRepositoryImpl>(),
        )
    }

    viewModel {
        PaymentViewModel(
            paymentNavigation = get<PaymentNavigationImpl>()
        )
    }
}
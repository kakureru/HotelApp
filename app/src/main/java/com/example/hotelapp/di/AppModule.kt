package com.example.hotelapp.di

import com.example.hotelapp.data.repository.HotelRepositoryImpl
import com.example.hotelapp.presentation.screens.booking.BookingViewModel
import com.example.hotelapp.presentation.screens.hotel.HotelViewModel
import com.example.hotelapp.presentation.screens.rooms.RoomsViewModel
import com.example.hotelapp.presentation.screens.success.SuccessViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        BookingViewModel(router = get(), hotelRepository = get<HotelRepositoryImpl>())
    }

    viewModel {
        HotelViewModel(router = get(), hotelRepository = get<HotelRepositoryImpl>())
    }

    viewModel {
        RoomsViewModel(router = get(), hotelRepository = get<HotelRepositoryImpl>())
    }

    viewModel {
        SuccessViewModel(router = get())
    }
}
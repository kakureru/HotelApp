package com.example.hotelapp.di

import com.example.booking.data.api.BookingApiImpl
import com.example.booking.data.repository.BookingRepositoryImpl
import com.example.hotel.data.HotelRepositoryImpl
import com.example.hotel.data.api.HotelApiImpl
import com.example.rooms.data.api.RoomsApiImpl
import com.example.rooms.data.repository.RoomsRepositoryImpl
import org.koin.dsl.module

val dataModule = module {

    single {
        HotelRepositoryImpl(hotelApi = get<HotelApiImpl>())
    }

    single {
        RoomsRepositoryImpl(roomsApi = get<RoomsApiImpl>())
    }

    single {
        BookingRepositoryImpl(bookingApi = get<BookingApiImpl>())
    }
}
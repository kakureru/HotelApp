package com.example.hotelapp.di

import com.example.hotelapp.data.api.HotelApiImpl
import com.example.hotelapp.data.repository.HotelRepositoryImpl
import org.koin.dsl.module

val dataModule = module {

    single {
        HotelRepositoryImpl(hotelApi = get<HotelApiImpl>())
    }
}
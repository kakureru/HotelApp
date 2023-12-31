package com.example.hotelapp.di

import com.example.hotelapp.navigation.BookingNavigationImpl
import com.example.hotelapp.navigation.HotelNavigationImpl
import com.example.hotelapp.navigation.PaymentNavigationImpl
import com.example.hotelapp.navigation.RoomsNavigationImpl
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.koin.dsl.module

val navigationModule = module {

    factory {
        HotelNavigationImpl(router = get())
    }

    factory {
        RoomsNavigationImpl(router = get())
    }

    factory {
        BookingNavigationImpl(router = get())
    }

    factory {
        PaymentNavigationImpl(router = get())
    }

    single {
        Cicerone.create()
    }

    single {
        provideRouter(cicerone = get())
    }

    single {
        provideNavigatorHolder(cicerone = get())
    }
}

fun provideRouter(cicerone: Cicerone<Router>) = cicerone.router
fun provideNavigatorHolder(cicerone: Cicerone<Router>) = cicerone.getNavigatorHolder()
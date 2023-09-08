package com.example.hotelapp.di

import com.example.booking.data.api.BookingApiImpl
import com.example.booking.data.api.BookingApiRetrofit
import com.example.hotel.data.api.HotelApiImpl
import com.example.hotel.data.api.HotelApiRetrofit
import com.example.rooms.data.api.RoomsApiImpl
import com.example.rooms.data.api.RoomsApiRetrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://run.mocky.io/v3/" // TODO remove

val networkModule = module {

    single {
        RoomsApiImpl(roomsApiRetrofit = get())
    }

    single {
        HotelApiImpl(hotelApiRetrofit = get())
    }

    single {
        BookingApiImpl(bookingApiRetrofit = get())
    }

    single {
        provideRoomsApiRetrofit(retrofit = get())
    }

    single {
        provideHotelApiRetrofit(retrofit = get())
    }

    single {
        provideBookingApiRetrofit(retrofit = get())
    }

    single<Retrofit> {
        provideRetrofit(okHttpClient = get())
    }

    single {
        provideHttpClient(httpLoggingInterceptor = get())
    }

    single {
        HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
    }
}

private fun provideRoomsApiRetrofit(retrofit: Retrofit) = retrofit.create(RoomsApiRetrofit::class.java)
private fun provideHotelApiRetrofit(retrofit: Retrofit) = retrofit.create(HotelApiRetrofit::class.java)
private fun provideBookingApiRetrofit(retrofit: Retrofit) = retrofit.create(BookingApiRetrofit::class.java)

private fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit
    .Builder()
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

private fun provideHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) = OkHttpClient
    .Builder()
    .addInterceptor(httpLoggingInterceptor)
    .build()
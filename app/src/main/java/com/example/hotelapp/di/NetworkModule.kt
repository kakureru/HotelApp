package com.example.hotelapp.di

import com.example.hotelapp.data.api.HotelApiImpl
import com.example.hotelapp.data.api.HotelApiRetrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://run.mocky.io/v3/" // TODO remove

val networkModule = module {

    single {
        HotelApiImpl(hotelApiRetrofit = get())
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

    single {
        provideHotelApiRetrofit(retrofit = get())
    }
}

private fun provideHotelApiRetrofit(retrofit: Retrofit) = retrofit.create(HotelApiRetrofit::class.java)

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
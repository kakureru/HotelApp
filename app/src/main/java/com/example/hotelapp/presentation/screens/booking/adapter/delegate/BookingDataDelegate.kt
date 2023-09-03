package com.example.hotelapp.presentation.screens.booking.adapter.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelapp.databinding.ItemBookingDataBinding
import com.example.hotelapp.presentation.recyclerview.delegate.AdapterDelegate
import com.example.hotelapp.presentation.recyclerview.delegate.DelegateItem
import com.example.hotelapp.presentation.screens.booking.adapter.item.BookingDataDelegateItem
import com.example.hotelapp.presentation.screens.booking.model.BookingDataItem

class BookingDataDelegate : AdapterDelegate {

    inner class BookingDataViewHolder(
        private val binding: ItemBookingDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BookingDataItem) {
            with(binding) {
                departureFrom.text = item.departure
                countryCity.text = item.arrivalCountry
                dates.text = item.dates
                nightsCount.text = item.numberOfNights
                hotel.text = item.hotelName
                room.text = item.room
                nutrition.text = item.nutrition
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        BookingDataViewHolder(
            ItemBookingDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: DelegateItem, position: Int) {
        (holder as BookingDataViewHolder).bind(item.content() as BookingDataItem)
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is BookingDataDelegateItem
}
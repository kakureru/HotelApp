package com.example.hotelapp.presentation.screens.booking.adapter.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelapp.databinding.ItemBookingPriceBinding
import com.example.hotelapp.presentation.recyclerview.adapter.AdapterDelegate
import com.example.hotelapp.presentation.recyclerview.adapter.DelegateItem
import com.example.hotelapp.presentation.screens.booking.adapter.item.BookingPriceDelegateItem
import com.example.hotelapp.presentation.screens.booking.model.BookingPriceItem

class BookingPriceDelegate : AdapterDelegate {

    inner class BookingPriceViewHolder(
        private val binding: ItemBookingPriceBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BookingPriceItem) {
            with(binding) {
                tour.text = item.tour
                fuelCharge.text = item.fuel
                serviceCharge.text = item.service
                total.text = item.total
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        BookingPriceViewHolder(
            ItemBookingPriceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: DelegateItem, position: Int) {
        (holder as BookingPriceViewHolder).bind(item.content() as BookingPriceItem)
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is BookingPriceDelegateItem
}
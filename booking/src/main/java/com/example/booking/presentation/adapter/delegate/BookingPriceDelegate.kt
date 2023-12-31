package com.example.booking.presentation.adapter.delegate

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.R
import com.example.booking.databinding.ItemBookingPriceBinding
import com.example.booking.presentation.adapter.item.BookingPriceDelegateItem
import com.example.booking.presentation.model.BookingPriceItem
import com.example.core.recyclerview.delegate.AdapterDelegate
import com.example.core.recyclerview.delegate.DelegateItem

class BookingPriceDelegate : AdapterDelegate {

    inner class BookingPriceViewHolder(
        private val context: Context,
        private val binding: ItemBookingPriceBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BookingPriceItem) {
            with(binding) {
                tour.text = context.resources.getString(R.string.ruble_price, item.tour)
                fuelCharge.text = context.resources.getString(R.string.ruble_price, item.fuel)
                serviceCharge.text = context.resources.getString(R.string.ruble_price, item.service)
                total.text = context.resources.getString(R.string.ruble_price, item.total)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        BookingPriceViewHolder(
            parent.context,
            ItemBookingPriceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: DelegateItem, position: Int) {
        (holder as BookingPriceViewHolder).bind(item.content() as BookingPriceItem)
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is BookingPriceDelegateItem
}
package com.example.hotelapp.presentation.screens.booking.adapter.item

import com.example.hotelapp.presentation.recyclerview.delegate.DelegateItem
import com.example.hotelapp.presentation.screens.booking.model.BookingPriceItem

class BookingPriceDelegateItem(private val value: BookingPriceItem) : DelegateItem {
    override fun content(): Any = value
    override fun id(): Int = -5
    override fun compareToOther(other: DelegateItem): Boolean =
        (other as BookingPriceDelegateItem).content() == value
}
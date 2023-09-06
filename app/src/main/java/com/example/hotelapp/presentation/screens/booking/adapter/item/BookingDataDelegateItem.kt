package com.example.hotelapp.presentation.screens.booking.adapter.item

import com.example.hotelapp.presentation.recyclerview.delegate.DelegateItem
import com.example.hotelapp.presentation.screens.booking.model.BookingDataItem

class BookingDataDelegateItem(private val value: BookingDataItem) : DelegateItem {
    override fun content(): Any = value
    override fun id(): Int = -2
    override fun compareToOther(other: DelegateItem): Boolean =
        (other as BookingDataDelegateItem).content() == value
}
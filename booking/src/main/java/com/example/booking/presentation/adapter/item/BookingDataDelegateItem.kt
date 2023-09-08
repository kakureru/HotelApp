package com.example.booking.presentation.adapter.item

import com.example.booking.presentation.model.BookingDataItem
import com.example.core.recyclerview.delegate.DelegateItem

class BookingDataDelegateItem(private val value: BookingDataItem) : DelegateItem {
    override fun content(): Any = value
    override fun id(): Int = -2
    override fun compareToOther(other: DelegateItem): Boolean =
        (other as BookingDataDelegateItem).content() == value
}
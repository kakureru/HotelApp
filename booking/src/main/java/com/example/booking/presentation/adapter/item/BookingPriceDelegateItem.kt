package com.example.booking.presentation.adapter.item

import com.example.booking.presentation.model.BookingPriceItem
import com.example.core.recyclerview.delegate.DelegateItem

class BookingPriceDelegateItem(private val value: BookingPriceItem) : DelegateItem {
    override fun content(): Any = value
    override fun id(): Int = -5
    override fun compareToOther(other: DelegateItem): Boolean =
        (other as BookingPriceDelegateItem).content() == value
}
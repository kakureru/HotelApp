package com.example.hotelapp.presentation.screens.booking.adapter.item

import com.example.hotelapp.presentation.utils.recyclerview.delegate.DelegateItem
import com.example.hotelapp.presentation.screens.booking.model.TouristItem

class TouristCollapsedDelegateItem(private val value: TouristItem) : DelegateItem {
    override fun content(): Any = value
    override fun id(): Int = value.ordinal
    override fun compareToOther(other: DelegateItem): Boolean =
        (other as TouristCollapsedDelegateItem).content() == value
}
package com.example.hotelapp.presentation.screens.booking.adapter.item

import com.example.hotelapp.presentation.recyclerview.adapter.DelegateItem
import com.example.hotelapp.presentation.screens.booking.model.TouristItem

class TouristDelegateItem(private val value: TouristItem) : DelegateItem {
    override fun content(): Any = value
    override fun id(): Int = value.hashCode()
    override fun compareToOther(other: DelegateItem): Boolean =
        (other as TouristDelegateItem).content() == value
}
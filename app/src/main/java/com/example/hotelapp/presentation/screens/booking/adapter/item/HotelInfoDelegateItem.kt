package com.example.hotelapp.presentation.screens.booking.adapter.item

import com.example.hotelapp.presentation.recyclerview.adapter.DelegateItem
import com.example.hotelapp.presentation.screens.booking.model.HotelInfoItem

class HotelInfoDelegateItem(private val value: HotelInfoItem) : DelegateItem {
    override fun content(): Any = value
    override fun id(): Int = value.hashCode()
    override fun compareToOther(other: DelegateItem): Boolean =
        (other as HotelInfoDelegateItem).content() == value
}
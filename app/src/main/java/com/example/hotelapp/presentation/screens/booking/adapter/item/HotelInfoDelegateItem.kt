package com.example.hotelapp.presentation.screens.booking.adapter.item

import com.example.hotelapp.presentation.recyclerview.delegate.DelegateItem
import com.example.hotelapp.presentation.screens.booking.model.HotelInfoItem

class HotelInfoDelegateItem(private val value: HotelInfoItem) : DelegateItem {
    override fun content(): Any = value
    override fun id(): Int = -1
    override fun compareToOther(other: DelegateItem): Boolean =
        (other as HotelInfoDelegateItem).content() == value
}
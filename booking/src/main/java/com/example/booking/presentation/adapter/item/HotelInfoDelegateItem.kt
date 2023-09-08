package com.example.booking.presentation.adapter.item

import com.example.booking.presentation.model.HotelInfoItem
import com.example.core.recyclerview.delegate.DelegateItem

class HotelInfoDelegateItem(private val value: HotelInfoItem) : DelegateItem {
    override fun content(): Any = value
    override fun id(): Int = -1
    override fun compareToOther(other: DelegateItem): Boolean =
        (other as HotelInfoDelegateItem).content() == value
}
package com.example.hotelapp.presentation.screens.booking.adapter.item

import com.example.hotelapp.presentation.recyclerview.delegate.DelegateItem

class TouristNewDelegateItem : DelegateItem {
    override fun content(): Any = Any()
    override fun id(): Int = -4
    override fun compareToOther(other: DelegateItem): Boolean = true
}
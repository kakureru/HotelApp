package com.example.booking.presentation.adapter.item

import com.example.core.recyclerview.delegate.DelegateItem

class TouristNewDelegateItem : DelegateItem {
    override fun content(): Any = Any()
    override fun id(): Int = -4
    override fun compareToOther(other: DelegateItem): Boolean = true
}
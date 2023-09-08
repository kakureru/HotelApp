package com.example.booking.presentation.adapter.item

import com.example.booking.presentation.model.TouristItem
import com.example.core.recyclerview.delegate.DelegateItem

class TouristExpandedDelegateItem(private val value: TouristItem) : DelegateItem {
    override fun content(): Any = value
    override fun id(): Int = value.ordinal
    override fun compareToOther(other: DelegateItem): Boolean =
        (other as TouristExpandedDelegateItem).content() == value
}
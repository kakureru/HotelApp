package com.example.booking.presentation.adapter.item

import com.example.booking.presentation.model.CustomerInfoItem
import com.example.core.recyclerview.delegate.DelegateItem

class CustomerInfoDelegateItem(private val value: CustomerInfoItem): DelegateItem {
    override fun content(): Any = value
    override fun id(): Int = -3
    override fun compareToOther(other: DelegateItem): Boolean =
        (other as CustomerInfoDelegateItem).content() == value
}
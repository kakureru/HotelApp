package com.example.hotelapp.presentation.screens.booking.adapter.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelapp.databinding.ItemCustomerInfoBinding
import com.example.hotelapp.presentation.utils.recyclerview.delegate.AdapterDelegate
import com.example.hotelapp.presentation.utils.recyclerview.delegate.DelegateItem
import com.example.hotelapp.presentation.screens.booking.adapter.item.CustomerInfoDelegateItem
import com.example.hotelapp.presentation.screens.booking.model.CustomerInfoItem
import com.example.hotelapp.presentation.screens.booking.model.bindState

class CustomerInfoDelegate : AdapterDelegate {

    inner class CustomerInfoViewHolder(
        private val binding: ItemCustomerInfoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CustomerInfoItem) {
            with(binding) {
                etPhone.bindState(item.phone)
                etMail.bindState(item.mail)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        CustomerInfoViewHolder(
            ItemCustomerInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: DelegateItem, position: Int) {
        (holder as CustomerInfoViewHolder).bind(item.content() as CustomerInfoItem)
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is CustomerInfoDelegateItem
}
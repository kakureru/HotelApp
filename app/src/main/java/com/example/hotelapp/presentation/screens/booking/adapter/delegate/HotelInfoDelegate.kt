package com.example.hotelapp.presentation.screens.booking.adapter.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelapp.databinding.ItemHotelInfoBinding
import com.example.hotelapp.presentation.utils.recyclerview.delegate.AdapterDelegate
import com.example.hotelapp.presentation.utils.recyclerview.delegate.DelegateItem
import com.example.hotelapp.presentation.screens.booking.adapter.item.HotelInfoDelegateItem
import com.example.hotelapp.presentation.screens.booking.model.HotelInfoItem

class HotelInfoDelegate(
    private val onAddressClick: () -> Unit,
) : AdapterDelegate {

    inner class HotelInfoViewHolder(
        private val binding: ItemHotelInfoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.info.address.setOnClickListener { onAddressClick() }
        }

        fun bind(item: HotelInfoItem) {
            with(binding.info) {
                ratingLayout.rating.text = item.rating
                name.text = item.name
                address.text = item.address
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        HotelInfoViewHolder(
            ItemHotelInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: DelegateItem, position: Int) {
        (holder as HotelInfoViewHolder).bind(item.content() as HotelInfoItem)
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is HotelInfoDelegateItem
}
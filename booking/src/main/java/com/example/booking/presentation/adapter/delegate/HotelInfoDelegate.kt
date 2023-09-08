package com.example.booking.presentation.adapter.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.booking.databinding.ItemHotelInfoBinding
import com.example.booking.presentation.adapter.item.HotelInfoDelegateItem
import com.example.booking.presentation.model.HotelInfoItem
import com.example.core.recyclerview.delegate.AdapterDelegate
import com.example.core.recyclerview.delegate.DelegateItem

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
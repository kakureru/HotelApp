package com.example.hotelapp.presentation.screens.booking.adapter.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelapp.databinding.ItemTouristExpandedBinding
import com.example.hotelapp.presentation.recyclerview.adapter.AdapterDelegate
import com.example.hotelapp.presentation.recyclerview.adapter.DelegateItem
import com.example.hotelapp.presentation.screens.booking.adapter.item.TouristDelegateItem
import com.example.hotelapp.presentation.screens.booking.model.TouristItem

class TouristExpandedDelegate : AdapterDelegate {

    inner class HotelInfoViewHolder(
        private val binding: ItemTouristExpandedBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TouristItem) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        HotelInfoViewHolder(
            ItemTouristExpandedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: DelegateItem, position: Int) {
        (holder as HotelInfoViewHolder).bind(item.content() as TouristItem)
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is TouristDelegateItem
}
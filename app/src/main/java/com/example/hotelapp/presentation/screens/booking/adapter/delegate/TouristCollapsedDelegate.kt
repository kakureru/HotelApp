package com.example.hotelapp.presentation.screens.booking.adapter.delegate

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelapp.R
import com.example.hotelapp.databinding.ItemTouristCollapsedBinding
import com.example.hotelapp.presentation.screens.booking.adapter.item.TouristCollapsedDelegateItem
import com.example.hotelapp.presentation.screens.booking.model.TouristItem
import com.example.hotelapp.presentation.utils.recyclerview.delegate.AdapterDelegate
import com.example.hotelapp.presentation.utils.recyclerview.delegate.DelegateItem

class TouristCollapsedDelegate(
    private val onExpandClick: (ordinal: Int) -> Unit,
) : AdapterDelegate {
    inner class TouristViewHolder(
        private val context: Context,
        private val binding: ItemTouristCollapsedBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TouristItem) {
            with(binding) {
                titleTourist.text = context.resources.getString(R.string.tourist_ordinal, item.ordinalName)
                btnExpand.setOnClickListener { onExpandClick(item.ordinal) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        TouristViewHolder(
            parent.context,
            ItemTouristCollapsedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: DelegateItem, position: Int) {
        (holder as TouristViewHolder).bind(item.content() as TouristItem)
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is TouristCollapsedDelegateItem
}
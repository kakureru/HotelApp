package com.example.booking.presentation.adapter.delegate

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.booking.databinding.ItemTouristCollapsedBinding
import com.example.booking.presentation.adapter.item.TouristCollapsedDelegateItem
import com.example.booking.presentation.model.TouristItem
import com.example.core.R
import com.example.core.recyclerview.delegate.AdapterDelegate
import com.example.core.recyclerview.delegate.DelegateItem

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
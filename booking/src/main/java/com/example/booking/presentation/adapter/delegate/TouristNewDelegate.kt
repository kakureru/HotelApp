package com.example.booking.presentation.adapter.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.booking.databinding.ItemTouristNewBinding
import com.example.booking.presentation.adapter.item.TouristNewDelegateItem
import com.example.core.recyclerview.delegate.AdapterDelegate
import com.example.core.recyclerview.delegate.DelegateItem

class TouristNewDelegate(
    private val onAddClick: () -> Unit,
) : AdapterDelegate {

    inner class TouristNewViewHolder(
        private val binding: ItemTouristNewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnAdd.setOnClickListener { onAddClick() }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        TouristNewViewHolder(
            ItemTouristNewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: DelegateItem, position: Int) = Unit

    override fun isOfViewType(item: DelegateItem): Boolean = item is TouristNewDelegateItem
}
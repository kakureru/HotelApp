package com.example.core.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.databinding.ItemPeculiarityBinding

class PeculiarityAdapter : ListAdapter<String, PeculiarityAdapter.PeculiaritiesViewHolder>(
    DiffCallback
) {

    inner class PeculiaritiesViewHolder(private val binding: ItemPeculiarityBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) {
            binding.tvPeculiarity.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeculiaritiesViewHolder =
        PeculiaritiesViewHolder(ItemPeculiarityBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: PeculiaritiesViewHolder, position: Int) =
        holder.bind(getItem(position))

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem
            override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
        }
    }
}
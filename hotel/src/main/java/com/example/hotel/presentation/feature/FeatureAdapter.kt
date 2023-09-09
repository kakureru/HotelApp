package com.example.hotel.presentation.feature

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hotel.databinding.ItemFeatureButtonBinding

class FeatureAdapter(
    private val onItemClick: (featureId: Int) -> Unit,
) : ListAdapter<FeatureItem, FeatureAdapter.FeatureViewHolder>(DiffCallback) {

    inner class FeatureViewHolder(private val binding: ItemFeatureButtonBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener { onItemClick(getItem(bindingAdapterPosition).id) }
        }

        fun bind(item: FeatureItem) {
            with(binding) {
                tvName.text = item.name
                tvDescription.text = item.description
                imgIcon.setImageResource(item.iconRes)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureAdapter.FeatureViewHolder =
        FeatureViewHolder(
            ItemFeatureButtonBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )

    override fun onBindViewHolder(holder: FeatureAdapter.FeatureViewHolder, position: Int) =
        holder.bind(getItem(position))

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<FeatureItem>() {
            override fun areItemsTheSame(oldItem: FeatureItem, newItem: FeatureItem) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: FeatureItem, newItem: FeatureItem) = oldItem == newItem
        }
    }
}
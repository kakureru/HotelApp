package com.example.rooms.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.core.R
import com.example.core.recyclerview.PeculiarityAdapter
import com.example.core.recyclerview.PhotoAdapter
import com.example.core.recyclerview.decorator.CirclePagerIndicatorDecoration
import com.example.core.recyclerview.decorator.HorizontalMarginItemDecoration
import com.example.rooms.databinding.ItemRoomBinding
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager

class RoomAdapter(
    private val onChooseRoomClick: (roomId: Int) -> Unit,
) : ListAdapter<RoomItem, RoomAdapter.RoomViewHolder>(DiffCallback) {

    inner class RoomViewHolder(
        private val context: Context,
        private val binding: ItemRoomBinding
        ) : RecyclerView.ViewHolder(binding.root) {

        private val photoAdapter = PhotoAdapter()
        private val peculiarityAdapter = PeculiarityAdapter()

        init {
            with(binding) {
                photoCarousel.listPhoto.adapter = photoAdapter
                listPeculiarities.apply {
                    adapter = peculiarityAdapter
                    layoutManager = FlexboxLayoutManager(context).apply { FlexDirection.ROW; setHasFixedSize(true) }
                }
                photoCarousel.listPhoto.apply {
                    PagerSnapHelper().attachToRecyclerView(this)
                    addItemDecoration(CirclePagerIndicatorDecoration())
                    addItemDecoration(HorizontalMarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_m)))
                }
                btnAction.setOnClickListener { onChooseRoomClick(getItem(adapterPosition).id) }
            }
        }

        fun bind(item: RoomItem) {
            with(binding) {
                roomTitle.text = item.name
                price.text = context.resources.getString(R.string.ruble_price, item.price)
                pricePer.text = item.pricePer
                photoAdapter.submitList(item.imageUrls)
                peculiarityAdapter.submitList(item.peculiarities)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder =
        RoomViewHolder(
            parent.context,
            ItemRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) =
        holder.bind(getItem(position))

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<RoomItem>() {
            override fun areItemsTheSame(oldItem: RoomItem, newItem: RoomItem) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: RoomItem, newItem: RoomItem) = oldItem == newItem
        }
    }
}
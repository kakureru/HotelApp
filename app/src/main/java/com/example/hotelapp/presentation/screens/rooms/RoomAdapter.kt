package com.example.hotelapp.presentation.screens.rooms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelapp.R
import com.example.hotelapp.databinding.ItemRoomBinding
import com.example.hotelapp.domain.model.Room
import com.example.hotelapp.presentation.recyclerview.OnSnapPositionChangeListener
import com.example.hotelapp.presentation.recyclerview.PhotoAdapter
import com.example.hotelapp.presentation.recyclerview.SnapOnScrollListener
import com.example.hotelapp.presentation.recyclerview.attachSnapHelperWithListener
import com.example.hotelapp.presentation.recyclerview.decorator.CirclePagerIndicatorDecoration
import com.example.hotelapp.presentation.recyclerview.decorator.MarginItemDecoration

/**
 * Плохой код
 */

class RoomAdapter(
    private val onChooseRoomClick: (roomId: Int) -> Unit,
) : ListAdapter<Room, RoomAdapter.RoomViewHolder>(DiffCallback) {

    private val onSnapPositionChangeListener = object : OnSnapPositionChangeListener { // FIXME useless
        override fun onSnapPositionChange(position: Int) = Unit
    }

    inner class RoomViewHolder(private val binding: ItemRoomBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnAction.setOnClickListener { onChooseRoomClick(getItem(adapterPosition).id) }
        }

        fun bind(item: Room) {
            with(binding) {
                roomTitle.text = item.name
                price.text = item.price.toString()
                pricePer.text = item.pricePer

                val photoAdapter = PhotoAdapter()
                photoCarousel.listPhoto.adapter = photoAdapter
                photoAdapter.submitList(item.imageUrls)
            }
            binding.photoCarousel.listPhoto.apply {
                attachSnapHelperWithListener(
                    snapHelper = PagerSnapHelper(),
                    behavior = SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL_STATE_IDLE,
                    onSnapPositionChangeListener = onSnapPositionChangeListener,
                )
                addItemDecoration(CirclePagerIndicatorDecoration())
                addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_m)))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder =
        RoomViewHolder(ItemRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) =
        holder.bind(getItem(position))

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Room>() {
            override fun areItemsTheSame(oldItem: Room, newItem: Room) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Room, newItem: Room) = oldItem == newItem
        }
    }
}
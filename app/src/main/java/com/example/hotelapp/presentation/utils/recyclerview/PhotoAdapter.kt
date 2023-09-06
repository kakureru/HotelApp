package com.example.hotelapp.presentation.utils.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.hotelapp.databinding.ItemPhotoCarouselBinding

class PhotoAdapter : ListAdapter<String, PhotoAdapter.PhotoViewHolder>(DiffCallback) {

    inner class PhotoViewHolder(private val binding: ItemPhotoCarouselBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photoUrl: String) {
            binding.photo.load(photoUrl)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder =
        PhotoViewHolder(
            ItemPhotoCarouselBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) =
        holder.bind(getItem(position))

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem
            override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
        }
    }
}
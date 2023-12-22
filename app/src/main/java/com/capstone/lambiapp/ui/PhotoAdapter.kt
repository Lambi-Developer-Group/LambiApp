package com.capstone.lambiapp.ui

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.capstone.lambiapp.R
import com.capstone.lambiapp.data.database.Photo
import com.capstone.lambiapp.databinding.ItemBajuBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PhotoAdapter(private val listener: OnItemClickListener) :
    ListAdapter<Photo, PhotoAdapter.PhotoViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemBajuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentPhoto = getItem(position)
        holder.bind(currentPhoto)
    }

    inner class PhotoViewHolder(private val binding: ItemBajuBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val photo = getItem(position)
                    listener.onItemClick(photo)
                }
            }
        }

        fun bind(photo: Photo) {
            Log.d("hehe", "Loading image from path: ${photo.imagePath}")

            Glide.with(itemView)
                .load(photo.imagePath)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error_placeholder)
                .into(binding.ivItemBaju)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(photo: Photo)
    }
}
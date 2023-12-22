package com.capstone.lambiapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.capstone.lambiapp.R
import com.smarteist.autoimageslider.SliderView
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter : SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {

    private val items = mutableListOf<String?>()

    fun addItem(item: String?) {
        items.add(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?): SliderAdapterVH {
        val inflate =
            LayoutInflater.from(parent?.context).inflate(R.layout.item_slider_card, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(holder: SliderAdapterVH, position: Int) {
        Glide.with(holder.itemView)
            .load(items[position])
            .into(holder.imageViewBackground)
    }

    override fun getCount(): Int {
        return items.size
    }

    inner class SliderAdapterVH(itemView: View) : ViewHolder(itemView) {
        val imageViewBackground: ImageView = itemView.findViewById(R.id.imageView1)
    }
}
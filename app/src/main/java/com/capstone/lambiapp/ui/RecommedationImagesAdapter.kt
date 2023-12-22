package com.capstone.lambiapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.lambiapp.R
import com.capstone.lambiapp.data.response.DataItem

class RecommendationImagesAdapter(private var recommendations: List<DataItem?>?,private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<RecommendationImagesAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(recommendation: DataItem?)
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val frameLayout: FrameLayout = itemView.findViewById(R.id.frame_item)
        val imageView1: ImageView = itemView.findViewById(R.id.iv_item_baju)
        val imageView2: ImageView = itemView.findViewById(R.id.iv_item_baju2)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recommendation, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recommendation = recommendations?.get(position)

        // Load images into ImageView using a library like Glide or Picasso
        Glide.with(holder.itemView.context).load(recommendation?.top?.image).into(holder.imageView1)
        Glide.with(holder.itemView.context).load(recommendation?.bottom?.image).into(holder.imageView2)

        // Set click listeners or other actions if needed

        holder.frameLayout.setOnClickListener {
            onItemClickListener.onItemClick(recommendation)
        }
    }

    override fun getItemCount(): Int {
        return recommendations?.size ?: 0
    }
    fun submitList(newList: List<DataItem?>?) {
        recommendations = newList
        notifyDataSetChanged()
    }
}
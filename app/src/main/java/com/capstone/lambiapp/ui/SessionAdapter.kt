package com.capstone.lambiapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.lambiapp.databinding.ItemSessionBinding

class SessionAdapter(
    private var sessionList: List<String>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<SessionAdapter.SessionViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(sessionID: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSessionBinding.inflate(inflater, parent, false)
        return SessionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SessionViewHolder, position: Int) {
        val sessionID = sessionList[position]
        holder.bind(sessionID)

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(sessionID)
        }
    }

    override fun getItemCount(): Int {
        return sessionList.size
    }

    inner class SessionViewHolder(private val binding: ItemSessionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(sessionID: String) {
            binding.textViewSession.text = sessionID
        }
    }

    fun submitList(newList: List<String>) {
        val diffResult = DiffUtil.calculateDiff(SessionDiffCallback(sessionList, newList))
        sessionList = newList
        diffResult.dispatchUpdatesTo(this)
    }

    private class SessionDiffCallback(
        private val oldList: List<String>,
        private val newList: List<String>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}
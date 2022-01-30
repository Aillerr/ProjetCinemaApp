package com.polytech.applicationcinma.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.polytech.applicationcinma.databinding.RealItemViewBinding
import com.polytech.applicationcinma.model.Realisateur

class MyListAdapterReal(
    private val clickListener: RealListener
) : ListAdapter<Realisateur, MyListAdapterReal.ViewHolder>(RealDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: RealItemViewBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Realisateur, clickListener: RealListener) {
            binding.real = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RealItemViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

}
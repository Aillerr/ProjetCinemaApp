package com.polytech.applicationcinma.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.polytech.applicationcinma.databinding.ActorItemViewBinding
import com.polytech.applicationcinma.model.Acteur

class MyListAdapterActor(
    private val clickListener: ActorListener
) : ListAdapter<Acteur, MyListAdapterActor.ViewHolder>(ActorDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ActorItemViewBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Acteur, clickListener: ActorListener) {
            binding.actor = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ActorItemViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

}
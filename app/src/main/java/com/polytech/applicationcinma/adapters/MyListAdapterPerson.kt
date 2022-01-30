package com.polytech.applicationcinma.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.polytech.applicationcinma.databinding.PersonItemViewBinding
import com.polytech.applicationcinma.model.Personnage

class MyListAdapterPerson(
    private val clickListener: PersonListener
) : ListAdapter<Personnage, MyListAdapterPerson.ViewHolder>(PersonDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: PersonItemViewBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Personnage, clickListener: PersonListener) {
            binding.perso = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PersonItemViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

}
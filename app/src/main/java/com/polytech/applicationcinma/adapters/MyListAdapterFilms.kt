package com.polytech.applicationcinma.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.polytech.applicationcinma.databinding.FilmItemViewBinding
import com.polytech.applicationcinma.model.Film

class MyListAdapterFilms(
    private val clickListener: FilmsListener
) : ListAdapter<Film, MyListAdapterFilms.ViewHolder>(FilmsDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: FilmItemViewBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Film, clickListener: FilmsListener) {
            binding.film = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FilmItemViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

}
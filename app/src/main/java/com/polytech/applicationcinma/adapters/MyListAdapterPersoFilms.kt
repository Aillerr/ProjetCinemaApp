package com.polytech.applicationcinma.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.polytech.applicationcinma.PersoFilmList
import com.polytech.applicationcinma.databinding.FilmItemViewBinding
import com.polytech.applicationcinma.databinding.FilmPersoItemViewBinding
import com.polytech.applicationcinma.model.Film

class MyListAdapterPersoFilms(
    private val clickListener: FilmsPersoListener
) : ListAdapter<PersoFilmList, MyListAdapterPersoFilms.ViewHolder>(FilmsPersoDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: FilmPersoItemViewBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: PersoFilmList, clickListener: FilmsPersoListener) {
            binding.film = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FilmPersoItemViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

}
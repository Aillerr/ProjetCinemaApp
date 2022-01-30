package com.polytech.applicationcinma.adapters

import androidx.recyclerview.widget.DiffUtil
import com.polytech.applicationcinma.model.Film

class FilmsDiffCallback : DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.NoFilm == newItem.NoFilm
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }
}
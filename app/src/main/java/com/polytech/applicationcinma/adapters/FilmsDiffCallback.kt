package com.polytech.applicationcinma.adapters

import androidx.recyclerview.widget.DiffUtil
import com.polytech.applicationcinma.PersoFilmList
import com.polytech.applicationcinma.model.Film

class FilmsDiffCallback : DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.NoFilm == newItem.NoFilm
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }
}

class FilmsPersoDiffCallback : DiffUtil.ItemCallback<PersoFilmList>() {
    override fun areItemsTheSame(oldItem: PersoFilmList, newItem: PersoFilmList): Boolean {
        return oldItem.noFilm == newItem.noFilm
    }

    override fun areContentsTheSame(oldItem: PersoFilmList, newItem: PersoFilmList): Boolean {
        return oldItem == newItem
    }
}
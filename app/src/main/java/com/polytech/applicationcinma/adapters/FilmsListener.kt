package com.polytech.applicationcinma.adapters

import com.polytech.applicationcinma.PersoFilmList
import com.polytech.applicationcinma.model.Film


class FilmsListener(val clickListener: (filmid: Int) -> Unit) {
    fun onClick(film: Film) = clickListener(film.NoFilm)
}

class FilmsPersoListener(val clickListener: (filmid: Int) -> Unit) {
    fun onClick(film: PersoFilmList) = clickListener(film.noFilm)
}
package com.polytech.applicationcinma.adapters

import com.polytech.applicationcinma.model.Personnage


class PersonListener(val clickListener: (pid: Int) -> Unit) {
    fun onClick(perso: Personnage) = clickListener(perso.NoPerso)
}
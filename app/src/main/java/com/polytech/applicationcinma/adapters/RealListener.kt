package com.polytech.applicationcinma.adapters

import com.polytech.applicationcinma.model.Realisateur


class RealListener(val clickListener: (reaid: Int) -> Unit) {
    fun onClick(rea: Realisateur) = clickListener(rea.NoRea)
}
package com.polytech.applicationcinma.adapters

import androidx.recyclerview.widget.DiffUtil
import com.polytech.applicationcinma.model.Film
import com.polytech.applicationcinma.model.Personnage

class PersonDiffCallback : DiffUtil.ItemCallback<Personnage>() {
    override fun areItemsTheSame(oldItem: Personnage, newItem: Personnage): Boolean {
        return oldItem.NoPerso == newItem.NoPerso
    }

    override fun areContentsTheSame(oldItem: Personnage, newItem: Personnage): Boolean {
        return oldItem == newItem
    }
}
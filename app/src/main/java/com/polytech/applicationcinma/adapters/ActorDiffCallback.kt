package com.polytech.applicationcinma.adapters

import androidx.recyclerview.widget.DiffUtil
import com.polytech.applicationcinma.model.Acteur

class ActorDiffCallback : DiffUtil.ItemCallback<Acteur>() {
    override fun areItemsTheSame(oldItem: Acteur, newItem: Acteur): Boolean {
        return oldItem.NoAct == newItem.NoAct
    }

    override fun areContentsTheSame(oldItem: Acteur, newItem: Acteur): Boolean {
        return oldItem == newItem
    }
}
package com.polytech.applicationcinma.adapters

import androidx.recyclerview.widget.DiffUtil
import com.polytech.applicationcinma.model.Realisateur

class RealDiffCallback : DiffUtil.ItemCallback<Realisateur>() {
    override fun areItemsTheSame(oldItem: Realisateur, newItem: Realisateur): Boolean {
        return oldItem.NoRea == newItem.NoRea
    }

    override fun areContentsTheSame(oldItem: Realisateur, newItem: Realisateur): Boolean {
        return oldItem == newItem
    }
}
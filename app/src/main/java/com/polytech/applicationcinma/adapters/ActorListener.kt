package com.polytech.applicationcinma.adapters

import com.polytech.applicationcinma.model.Acteur


class ActorListener(val clickListener: (aid: Int) -> Unit) {
    fun onClick(actor: Acteur) = clickListener(actor.NoAct)
}
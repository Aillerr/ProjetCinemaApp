package com.polytech.applicationcinma.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.polytech.applicationcinma.viewmodel.ActorViewModel

class ActorViewModelFactory(
    private val application: Application,
    private val token: String,
    private val aid: Int
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ActorViewModel::class.java)) {
            return ActorViewModel(application,token,aid) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
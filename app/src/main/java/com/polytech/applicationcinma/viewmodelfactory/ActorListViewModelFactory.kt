package com.polytech.applicationcinma.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.polytech.applicationcinma.dao.UtilisateurDAO
import com.polytech.applicationcinma.viewmodel.ActorListViewModel
import com.polytech.applicationcinma.viewmodel.FilmListViewModel
import com.polytech.applicationcinma.viewmodel.LoginViewModel

class ActorListViewModelFactory(
    private val application: Application,
    private val token: String,
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ActorListViewModel::class.java)) {
            return ActorListViewModel(application,token) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
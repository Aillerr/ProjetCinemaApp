package com.polytech.applicationcinma.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.polytech.applicationcinma.dao.UtilisateurDAO
import com.polytech.applicationcinma.viewmodel.ActorListViewModel
import com.polytech.applicationcinma.viewmodel.FilmListViewModel
import com.polytech.applicationcinma.viewmodel.LoginViewModel
import com.polytech.applicationcinma.viewmodel.RealListViewModel

class RealListViewModelFactory(
    private val application: Application,
    private val token: String,
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RealListViewModel::class.java)) {
            return RealListViewModel(application,token) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
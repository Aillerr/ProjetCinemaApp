package com.polytech.applicationcinma.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.polytech.applicationcinma.dao.UtilisateurDAO
import com.polytech.applicationcinma.viewmodel.HomeViewModel
import com.polytech.applicationcinma.viewmodel.LoginViewModel

class HomeViewModelFactory(
    private val application: Application,
    private val token: String
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(application,token) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
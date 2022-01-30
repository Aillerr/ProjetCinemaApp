package com.polytech.applicationcinma.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.polytech.applicationcinma.dao.UtilisateurDAO
import com.polytech.applicationcinma.viewmodel.FilmViewModel
import com.polytech.applicationcinma.viewmodel.LoginViewModel

class FilmViewModelFactory(
    private val application: Application,
    private val token: String,
    private val fid: Int
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FilmViewModel::class.java)) {
            return FilmViewModel(application,token,fid) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
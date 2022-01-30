package com.polytech.applicationcinma.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.polytech.applicationcinma.dao.UtilisateurDAO
import com.polytech.applicationcinma.viewmodel.RegisterViewModel

class RegisterViewModelFactory(
    private val application: Application,
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
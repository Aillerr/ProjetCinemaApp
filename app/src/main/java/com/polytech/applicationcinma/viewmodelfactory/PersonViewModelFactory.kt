package com.polytech.applicationcinma.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.polytech.applicationcinma.dao.UtilisateurDAO
import com.polytech.applicationcinma.viewmodel.LoginViewModel
import com.polytech.applicationcinma.viewmodel.PersonViewModel

class PersonViewModelFactory(
    private val application: Application,
    private val token: String,
    private val pid: Int
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PersonViewModel::class.java)) {
            return PersonViewModel(application,token,pid) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
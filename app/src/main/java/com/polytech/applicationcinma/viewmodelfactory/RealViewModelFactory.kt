package com.polytech.applicationcinma.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.polytech.applicationcinma.viewmodel.RealViewModel

class RealViewModelFactory(
    private val application: Application,
    private val token: String,
    private val rid: Int
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RealViewModel::class.java)) {
            return RealViewModel(application,token,rid) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
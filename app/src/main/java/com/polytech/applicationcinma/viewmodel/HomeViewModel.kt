package com.polytech.applicationcinma.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide.init
import com.polytech.applicationcinma.MyApi
import com.polytech.applicationcinma.dao.UtilisateurDAO
import com.polytech.applicationcinma.model.Utilisateur
import kotlinx.coroutines.*
import java.lang.Exception

class HomeViewModel(
    application: Application,
    private val token: String = "" // Token
): AndroidViewModel(application) {
    private var viewModelJob = Job()

    init {
        Log.i("HomeViewModel", "created")
    }


    override fun onCleared() {
        super.onCleared()
        Log.i("HomeViewModel", "destroyed")
        viewModelJob.cancel()
    }
}
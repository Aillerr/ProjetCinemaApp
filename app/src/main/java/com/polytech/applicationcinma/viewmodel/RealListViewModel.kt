package com.polytech.applicationcinma.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.polytech.applicationcinma.MyApi
import com.polytech.applicationcinma.dao.UtilisateurDAO
import com.polytech.applicationcinma.model.Acteur
import com.polytech.applicationcinma.model.Film
import com.polytech.applicationcinma.model.Realisateur
import com.polytech.applicationcinma.model.Utilisateur
import kotlinx.coroutines.*
import java.lang.Exception

class RealListViewModel(
    application: Application,
    private val token: String = "", // Token
) : AndroidViewModel(application)
{
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _reals = MutableLiveData<List<Realisateur>>()
    val reals : LiveData<List<Realisateur>>
        get() = _reals

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )


    init {
        Log.i("RealListViewModel", "created")
    }


    private fun getActorsFromAPI() {
        coroutineScope.launch {
            val getPresetsDeferred = MyApi.retrofitService.getReals()
            try {
                val listResult = getPresetsDeferred.await()
                _reals.value = listResult
            }catch (e: Exception) {
                Log.i("API ERROR -- Real List", "Exception with API -- Using local DB")
            }
        }
    }



    override fun onCleared() {
        super.onCleared()
        Log.i("RealListViewModel", "destroyed")
        viewModelJob.cancel()
    }
}
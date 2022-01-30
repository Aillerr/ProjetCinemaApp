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
import com.polytech.applicationcinma.model.Utilisateur
import kotlinx.coroutines.*
import java.lang.Exception

class ActorListViewModel(
    application: Application,
    private val token: String = "", // Token
) : AndroidViewModel(application)
{
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _actors = MutableLiveData<List<Acteur>>()
    val actors : LiveData<List<Acteur>>
        get() = _actors

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )


    init {
        Log.i("ActorListViewModel", "created")
    }


    private fun getActorsFromAPI() {
        coroutineScope.launch {
            val getPresetsDeferred = MyApi.retrofitService.getActors()
            try {
                val listResult = getPresetsDeferred.await()
                _actors.value = listResult
            }catch (e: Exception) {
                Log.i("API ERROR -- ActorList", "Exception with API -- Using local DB")
            }
        }
    }



    override fun onCleared() {
        super.onCleared()
        Log.i("ActorListViewModel", "destroyed")
        viewModelJob.cancel()
    }
}
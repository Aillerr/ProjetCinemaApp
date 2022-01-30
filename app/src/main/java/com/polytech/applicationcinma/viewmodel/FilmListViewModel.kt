package com.polytech.applicationcinma.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.polytech.applicationcinma.MyApi
import com.polytech.applicationcinma.PersoFilmList
import com.polytech.applicationcinma.dao.UtilisateurDAO
import com.polytech.applicationcinma.model.Film
import com.polytech.applicationcinma.model.Utilisateur
import kotlinx.coroutines.*
import java.lang.Exception

class FilmListViewModel(
    application: Application,
    private val token: String = "", // Token
) : AndroidViewModel(application)
{
    private val baseStr:String = "Bearer "
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _films = MutableLiveData<List<PersoFilmList>>()
    val films: LiveData<List<PersoFilmList>>
        get() = _films

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )


    init {
        Log.i("FilmListViewModel", "created")
        getFilmsFromAPI()
    }


    private fun getFilmsFromAPI() {
        coroutineScope.launch {
            val getPresetsDeferred = MyApi.retrofitService.getFilms(baseStr+token)
            try {
                val listResult = getPresetsDeferred.await()
                _films.value = listResult
            }catch (e: Exception) {
                Log.i("API ERROR -- Film List", "Exception with API -- Using local DB")
            }
        }
    }



    override fun onCleared() {
        super.onCleared()
        Log.i("FilmListViewModel", "destroyed")
        viewModelJob.cancel()
    }
}
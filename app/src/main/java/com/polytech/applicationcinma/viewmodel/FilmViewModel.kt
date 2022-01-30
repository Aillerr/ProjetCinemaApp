package com.polytech.applicationcinma.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.polytech.applicationcinma.MyApi
import com.polytech.applicationcinma.dao.UtilisateurDAO
import com.polytech.applicationcinma.model.Film
import com.polytech.applicationcinma.model.Utilisateur
import kotlinx.coroutines.*

class FilmViewModel(
    application: Application,
    private val token: String = "", // Token
    private val fid: Int = 0 // Film ID
) : AndroidViewModel(application)
{
    private val imgURL: String = ""
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _film = MutableLiveData<Film>()
    val film: LiveData<Film>
        get() = _film
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )


    private fun filmFromAPI() {
        coroutineScope.launch {
            val filmDeferred = MyApi.retrofitService.getFilm(fid)
            try {
                val filmResult = filmDeferred.await()
                film.value?.Titre = filmResult.Titre
                film.value?.Budget = filmResult.Budget


                film.value?.Image = imgURL + filmResult.Image
                _apiOK.value = true
            }catch (e: Exception) {
                Log.i("API ERROR -- Film", "Exception with API")
                return@launch
            }
        }
    }

    init {
        Log.i("FilmViewModel", "created")
        filmFromAPI()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("FilmViewModel", "destroyed")
        viewModelJob.cancel()
    }


    private val _apiOK = MutableLiveData<Boolean?>()

    val apiOK: MutableLiveData<Boolean?>
        get() = _apiOK

    fun done_apiOK() {
        _apiOK.value = null
    }

    private val _navigateToHomeFragment = MutableLiveData<Long?>()

    val navigateToHomeFragment: MutableLiveData<Long?>
        get() = _navigateToHomeFragment

    fun doneNavigating() {
        _navigateToHomeFragment.value = null
    }
}
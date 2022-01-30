package com.polytech.applicationcinma.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.polytech.applicationcinma.MyApi
import com.polytech.applicationcinma.model.Film
import kotlinx.coroutines.*

class FilmViewModel(
    application: Application,
    private val token: String = "", // Token
    private val fid: Int = 0 // Film ID
) : AndroidViewModel(application)
{
    private val baseStr:String = "Bearer "
    private val imgURL: String = "http://cinema.erebz.fr:80/img/films/"
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _film = MutableLiveData<Film>()
    val film: LiveData<Film>
        get() = _film
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )
    private val _real = MutableLiveData<String>()
    val real: LiveData<String>
        get() = _real
    private val _cat = MutableLiveData<String>()
    val cat: LiveData<String>
        get() = _cat

    private fun filmFromAPI() {
        coroutineScope.launch {
            val filmDeferred = MyApi.retrofitService.getFilm(fid,baseStr+token)
            try {
                val filmResult = filmDeferred.await()
                _film.value?.Titre = filmResult.titre
                _film.value?.Budget = filmResult.budget
                _film.value?.Duree = filmResult.duree
                _film.value?.DateSortie = filmResult.dateSortie
                _film.value?.MontantRecette = filmResult.montantRecette
                _film.value?.DateSortie = filmResult.dateSortie
                _real.value = filmResult.realisateur.NomRea + " " + filmResult.realisateur.PrenRea
                _cat.value = filmResult.categorie.LibelleCat.toString()
                _film.value?.Image = imgURL + filmResult.image
                _apiOK.value = true
            }catch (e: Exception) {
                Log.i("API ERROR -- Film", "Exception with API")
                return@launch
            }
        }
    }

    init {
        Log.i("FilmViewModel", "created")
        _film.value = Film()
        _cat.value = ""
        _real.value = ""
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
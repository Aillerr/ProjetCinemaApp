package com.polytech.applicationcinma.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.polytech.applicationcinma.MyApi
import com.polytech.applicationcinma.model.Personnage
import kotlinx.coroutines.*

class PersonViewModel(
    application: Application,
    private val token: String = "", // Token
    private val pid: Int = 0 // Film ID
) : AndroidViewModel(application)
{
    private val baseStr:String = "Bearer "
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _perso = MutableLiveData<Personnage>()
    val perso: LiveData<Personnage>
        get() = _perso
    private val _actor = MutableLiveData<String>()
    val actor: LiveData<String>
        get() = _actor
    private val _film = MutableLiveData<String>()
    val film: LiveData<String>
        get() = _film
    var fid: Int = 0
    var aid: Int = 0
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    /**
     * Login with API
     */
    private fun persoFromAPI() {
        coroutineScope.launch {
            val loginDeferred = MyApi.retrofitService.getPerson(pid,baseStr+token)
            try {
                val persResult = loginDeferred.await()
                perso.value!!.NoPerso = persResult.noPerso
                perso.value!!.NomPers = persResult.nomPers
                _actor.value=persResult.acteur.NomAct
                _film.value=persResult.film.titre
                fid = persResult.film.noFilm
                aid = persResult.acteur.NoAct
                _apiOK.value = true
            }catch (e: Exception) {
                Log.i("API ERROR -- Film", "Exception with API")
                return@launch
            }
        }
    }


    init {
        Log.i("PersonViewModel", "created")
        _perso.value = Personnage()
        _film.value = ""
        _actor.value = ""
        persoFromAPI()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("PersonViewModel", "destroyed")
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
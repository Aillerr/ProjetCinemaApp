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
import com.polytech.applicationcinma.model.Personnage
import com.polytech.applicationcinma.model.Utilisateur
import kotlinx.coroutines.*

class ActorViewModel(
    application: Application,
    private val token: String = "", // Token
    private val aid: Int = 0 // Film ID
) : AndroidViewModel(application)
{
    private val baseStr:String = "Bearer "
    private val imgURL: String = "http://cinema.erebz.fr:80/img/realisateurs/"
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _actor = MutableLiveData<Acteur>()
    val actor: LiveData<Acteur>
        get() = _actor
    private val _personnages = MutableLiveData<List<Personnage>>()
    val personnages: LiveData<List<Personnage>>
        get() = _personnages
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    /**
     * Login with API
     */
    private fun actorFromAPI() {
        coroutineScope.launch {
            val loginDeferred = MyApi.retrofitService.getActor(aid,baseStr+token)
            try {
                val actRes = loginDeferred.await()
                _actor.value!!.NomAct = actRes.nomAct
                _actor.value!!.PrenAct = actRes.prenAct
                _actor.value!!.DateDeces = actRes.dateDeces
                _actor.value!!.DateNaiss = actRes.dateNaiss
                _actor.value?.image = imgURL + actRes.image
                _personnages.value = actRes.personnages
                _apiOK.value = true
            }catch (e: Exception) {
                Log.i("API ERROR -- Film", "Exception with API")
                return@launch
            }
        }
    }


    init {
        Log.i("ActorViewModel", "created")
        _actor.value = Acteur()
        _personnages.value = ArrayList()
        actorFromAPI()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ActorViewModel", "destroyed")
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
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
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _perso = MutableLiveData<Personnage>()
    val perso: LiveData<Personnage>
        get() = _perso
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    /**
     * Login with API
     */
    private fun persoFromAPI() {
        coroutineScope.launch {
            val loginDeferred = MyApi.retrofitService.getPerson(pid,token)
            try {
                val filmResult = loginDeferred.await()
                perso.value?.NomPers = filmResult.NomPers
                _apiOK.value = true
            }catch (e: Exception) {
                Log.i("API ERROR -- Film", "Exception with API")
                return@launch
            }
        }
    }


    init {
        Log.i("PersonViewModel", "created")
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
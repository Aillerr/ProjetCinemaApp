package com.polytech.applicationcinma.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.polytech.applicationcinma.MyApi
import com.polytech.applicationcinma.dao.UtilisateurDAO
import com.polytech.applicationcinma.model.Film
import com.polytech.applicationcinma.model.Realisateur
import com.polytech.applicationcinma.model.Utilisateur
import kotlinx.coroutines.*

class RealViewModel(
    application: Application,
    private val token: String = "", // Token
    private val rid: Int = 0 // Film ID
) : AndroidViewModel(application)
{
    private val imgURL: String = ""
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _real = MutableLiveData<Realisateur>()
    val real: LiveData<Realisateur>
        get() = _real
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )


    private fun realFromAPI() {
        coroutineScope.launch {
            val realDeferred = MyApi.retrofitService.getReal(rid)
            try {
                val realResult = realDeferred.await()

                _apiOK.value = true
            }catch (e: Exception) {
                Log.i("API ERROR -- Real", "Exception with API")
                return@launch
            }
        }
    }


    init {
        Log.i("RealViewModel", "created")
        realFromAPI()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("RealViewModel", "destroyed")
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
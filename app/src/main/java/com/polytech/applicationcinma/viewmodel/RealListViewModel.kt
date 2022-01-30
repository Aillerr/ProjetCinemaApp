package com.polytech.applicationcinma.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.polytech.applicationcinma.MyApi
import com.polytech.applicationcinma.PersoRealList
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
    private val baseStr:String = "Bearer "
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _reals = MutableLiveData<List<Realisateur>>()
    val reals : LiveData<List<Realisateur>>
        get() = _reals

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )


    init {
        Log.i("RealListViewModel", "created")
        _reals.value = ArrayList()
        getRealsFromAPI()
    }


    private fun getRealsFromAPI() {
        coroutineScope.launch {
            val getPresetsDeferred = MyApi.retrofitService.getReals(baseStr+token)
            try {
                var tmplist = ArrayList<Realisateur>()
                val listResult = getPresetsDeferred.await()
                for(tmp in listResult) {
                    var newReal = Realisateur()
                    newReal.NoRea = tmp.noRea
                    newReal.NomRea = tmp.nomRea
                    newReal.PrenRea = tmp.prenRea
                    tmplist.add(newReal)
                }
                _reals.value = tmplist
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
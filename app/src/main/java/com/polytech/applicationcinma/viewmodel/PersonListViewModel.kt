package com.polytech.applicationcinma.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.polytech.applicationcinma.MyApi
import com.polytech.applicationcinma.dao.UtilisateurDAO
import com.polytech.applicationcinma.model.Acteur
import com.polytech.applicationcinma.model.Personnage
import com.polytech.applicationcinma.model.Realisateur
import com.polytech.applicationcinma.model.Utilisateur
import kotlinx.coroutines.*
import java.lang.Exception

class PersonListViewModel(
    application: Application,
    private val token: String = "", // Token
) : AndroidViewModel(application)
{
    private val baseStr:String = "Bearer "
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _persons = MutableLiveData<List<Personnage>>()
    val persons : LiveData<List<Personnage>>
        get() = _persons

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )


    init {
        Log.i("PersonListViewModel", "created")
        _persons.value = ArrayList()
        getPersonsFromAPI()
    }


    private fun getPersonsFromAPI() {
        coroutineScope.launch {
            val getPresetsDeferred = MyApi.retrofitService.getPersons(baseStr+token)
            try {
                var tmplist = ArrayList<Personnage>()
                val listResult = getPresetsDeferred.await()
                for(tmp in listResult) {
                    var newPers = Personnage()
                    newPers.NomPers = tmp.nomPers
                    newPers.NoPerso = tmp.noPerso
                    tmplist.add(newPers)
                }
                _persons.value = tmplist
            }catch (e: Exception) {
                Log.i("API ERROR -- PersonList", "Exception with API -- Using local DB")
            }
        }
    }



    override fun onCleared() {
        super.onCleared()
        Log.i("PersonListViewModel", "destroyed")
        viewModelJob.cancel()
    }
}
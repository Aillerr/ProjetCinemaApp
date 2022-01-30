package com.polytech.applicationcinma.viewmodel

import com.polytech.applicationcinma.MyApi
import com.polytech.applicationcinma.dao.UtilisateurDAO
import com.polytech.applicationcinma.model.Utilisateur
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import java.lang.Exception

class RegisterViewModel(
    application: Application,
) : AndroidViewModel(application)
{
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _user = MutableLiveData<Utilisateur>()
    val _pwdCheck = MutableLiveData<String>()
    val user: LiveData<Utilisateur>
        get() = _user
    val pwdCheck: LiveData<String>
        get() = _pwdCheck

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
        Log.i("RegisterViewModel", "created")
        initializeUser()
    }

    private fun initializeUser() {
        uiScope.launch {
            _user.value = Utilisateur()
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("RegisterViewModel", "destroyed")
        viewModelJob.cancel()
    }

    private val _navigateToHomeFragment = MutableLiveData<String?>()

    val navigateToHomeFragment: MutableLiveData<String?>
        get() = _navigateToHomeFragment

    fun doneNavigating() {
        _navigateToHomeFragment.value = null
    }

    /**
     * Error manager when registering
     * If it is null, no error
     * Each value corresponds to an error
     */
    private val _errorRegistering = MutableLiveData<Long?>()

    val errorRegistering: MutableLiveData<Long?>
        get() = _errorRegistering

    private fun noError() {
        _errorRegistering.value = null
    }

    fun onValidateRegister() {
        uiScope.launch {

            val user = user.value ?: return@launch
            val pwdCheck = pwdCheck.value

            if(user.NomUtil.isNullOrEmpty()) {
                _errorRegistering.value = 8
                return@launch
            }

            if(user.MotPasse.isNullOrEmpty()) {
                _errorRegistering.value = 7
                return@launch
            }

            if(pwdCheck.isNullOrEmpty()) {
                _errorRegistering.value = 9
                return@launch
            }
            registerFromAPI(user)

        }
    }


    /**
     * Register with API -- If no API, register into app DB
     */
    private fun registerFromAPI(user: Utilisateur) {
        coroutineScope.launch {
            val registerDeferred = MyApi.retrofitService.register(user)
            try {
                val registerResult = registerDeferred.await()
                _navigateToHomeFragment.value = registerResult.token
            }catch (e: Exception) {
                Log.i("API ERROR -- Login", "Exception with API -- Using local DB")
                _errorRegistering.value = 0
                return@launch
            }
        }
    }
}
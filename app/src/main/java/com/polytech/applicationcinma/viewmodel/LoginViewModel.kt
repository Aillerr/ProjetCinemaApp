package com.polytech.applicationcinma.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.polytech.applicationcinma.MyApi
import com.polytech.applicationcinma.dao.UtilisateurDAO
import com.polytech.applicationcinma.model.Utilisateur
import kotlinx.coroutines.*

class LoginViewModel(
    application: Application,
) : AndroidViewModel(application)
{
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _user = MutableLiveData<Utilisateur>()
    val user: LiveData<Utilisateur>
        get() = _user
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    /**
     * Login with API
     */
    private fun loginFromAPI(user: Utilisateur) {
        coroutineScope.launch {
            val loginDeferred = MyApi.retrofitService.login(user)
            try {
                val loginResult = loginDeferred.await()
                if(loginResult.response) {
                    noError()
                    _navigateToHomeFragment.value = loginResult.token
                }else{
                    _errorLogin.value = true
                    return@launch
                }
            }catch (e: Exception) {
                Log.i("API ERROR -- Login", "Exception with API -- Using local DB")
                _errorLogin.value = true
                return@launch
            }
        }
    }


    init {
        Log.i("LoginViewModel", "created")
        initializeUser()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("LoginViewModel", "destroyed")
        viewModelJob.cancel()
    }

    private fun initializeUser() {
        uiScope.launch {
            _user.value = Utilisateur()
        }
    }


    private val _navigateToHomeFragment = MutableLiveData<String?>()

    val navigateToHomeFragment: MutableLiveData<String?>
        get() = _navigateToHomeFragment

    fun doneNavigating() {
        _navigateToHomeFragment.value = null
    }

    private val _errorLogin = MutableLiveData<Boolean?>()

    val errorLogin: MutableLiveData<Boolean?>
        get() = _errorLogin

    private fun noError() {
        _errorLogin.value = null
    }

    fun onValidateIdentity() {
        uiScope.launch {

            val user = user.value ?: return@launch

            if(user.NomUtil.isNullOrEmpty())
                return@launch

            if(user.MotPasse.isNullOrEmpty())
                return@launch

            loginFromAPI(user)
        }
    }
}
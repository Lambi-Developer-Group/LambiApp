package com.capstone.lambiapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _sessionID = MutableLiveData<String>()
    val sessionID: LiveData<String> get() = _sessionID

    fun setSessionID(sessionID: String) {
        _sessionID.value = sessionID
    }
}
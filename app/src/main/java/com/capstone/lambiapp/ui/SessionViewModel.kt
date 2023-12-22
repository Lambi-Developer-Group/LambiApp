package com.capstone.lambiapp.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.lambiapp.data.request.LoginRequest
import com.capstone.lambiapp.data.request.SessionRequest
import com.capstone.lambiapp.data.response.SessionResponse
import com.capstone.lambiapp.data.response.TokenResponse
import com.capstone.lambiapp.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SessionViewModel : ViewModel() {
    private val _sessionResult = MutableLiveData<Boolean>()
    val sessionResult: LiveData<Boolean> get() = _sessionResult
    private val _loading = MutableLiveData<Boolean>()
    private val _sessionResponse = MutableLiveData<SessionResponse>()
    val sessionResponse: LiveData<SessionResponse> get() = _sessionResponse
    private val _sessionID = MutableLiveData<String>()
    val sessionID: LiveData<String> get() = _sessionID
    val loading: LiveData<Boolean> get() = _loading

    fun getSession(idToken: String, context: Context) {

        _loading.value = true

        val apiService = ApiConfig.getApiService(context)
        val client = apiService.sendSession(SessionRequest(idToken))

        client.enqueue(object : Callback<SessionResponse> {
            override fun onResponse(
                call: Call<SessionResponse>,
                response: Response<SessionResponse>
            ) {

                _loading.value = false
                if (response.isSuccessful) {

                    // Set the response data
                    _sessionResponse.postValue(response.body())
                    _sessionResult.postValue(true)

                }
            }

            override fun onFailure(call: Call<SessionResponse>, t: Throwable) {
                // Handle failure if needed
                _sessionResult.value = false
                _loading.value = false
            }
        })
    }
}
package com.capstone.lambiapp.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.lambiapp.data.request.LoginRequest
import com.capstone.lambiapp.data.response.TokenResponse
import com.capstone.lambiapp.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel(){
 private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun getToken(idToken: String, context: Context) {

        _loading.value = true

        val apiService = ApiConfig.getApiService(context)
        val client = apiService.login(LoginRequest(idToken))

        client.enqueue(object : Callback<TokenResponse> {
            override fun onResponse(
                call: Call<TokenResponse>,
                response: Response<TokenResponse>
            ) {

                _loading.value = false
                if (response.isSuccessful) {
                    _loginResult.value = true
                }
            }

            override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                // Handle failure if needed
                _loginResult.value = false
                _loading.value = false
            }
        })
    }
}


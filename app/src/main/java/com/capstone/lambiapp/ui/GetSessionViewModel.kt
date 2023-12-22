package com.capstone.lambiapp.ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.lambiapp.data.model.SessionItem
import com.capstone.lambiapp.data.repository.SessionRepository
import com.capstone.lambiapp.data.request.SessionRequest
import com.capstone.lambiapp.data.response.GetSessionResponse
import com.capstone.lambiapp.data.response.SessionResponse
import com.capstone.lambiapp.data.retrofit.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetSessionViewModel : ViewModel() {
    private val _sessionList = MutableLiveData<List<String>>()
    val sessionList: LiveData<List<String>> get() = _sessionList

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun getSession(idToken: String, context: Context) {
        _loading.value = true

        val apiService = ApiConfig.getApiService(context)
        val client = apiService.getSession(SessionRequest(idToken))

        client.enqueue(object : Callback<GetSessionResponse> {
            override fun onResponse(
                call: Call<GetSessionResponse>,
                response: Response<GetSessionResponse>
            ) {
                _loading.value = false
                if (response.isSuccessful) {
                    val getSessionResponse = response.body()
                    val sessionIDs = getSessionResponse?.sessionID.orEmpty()
                    _sessionList.postValue(sessionIDs as List<String>?)
                }
            }

            override fun onFailure(call: Call<GetSessionResponse>, t: Throwable) {
                _loading.value = false
            }
        })
    }
}




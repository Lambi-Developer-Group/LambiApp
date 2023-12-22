package com.capstone.lambiapp.data.repository

import android.app.Application
import com.capstone.lambiapp.data.request.SessionRequest
import com.capstone.lambiapp.data.response.GetSessionResponse
import com.capstone.lambiapp.data.retrofit.ApiConfig
import com.capstone.lambiapp.data.retrofit.ApiService
import retrofit2.Call

class SessionRepository(application: Application) {
    private val apiService: ApiService = ApiConfig.getApiService(application)

    suspend fun getSession(idToken: String): Call<GetSessionResponse> {
        return apiService.getSession(SessionRequest(idToken))
    }
}
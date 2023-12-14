package com.capstone.lambiapp.data.retrofit

import com.capstone.lambiapp.data.request.LoginRequest
import com.capstone.lambiapp.data.response.TokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/login")
    fun login(
        @Body token: LoginRequest
    ) : Call<TokenResponse>
}
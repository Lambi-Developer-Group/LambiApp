package com.capstone.lambiapp.data.retrofit

import android.graphics.Color
import com.capstone.lambiapp.data.request.LoginRequest
import com.capstone.lambiapp.data.request.RecommendationsImageRequest
import com.capstone.lambiapp.data.request.RecommendationsRequest
import com.capstone.lambiapp.data.request.SessionRequest
import com.capstone.lambiapp.data.response.GetSessionResponse
import com.capstone.lambiapp.data.response.ImageResponse
import com.capstone.lambiapp.data.response.RecommendationsImageResponse
import com.capstone.lambiapp.data.response.RecommendationsResponse
import com.capstone.lambiapp.data.response.SessionResponse
import com.capstone.lambiapp.data.response.TokenResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @POST("auth/login")
    fun login(
        @Body token: LoginRequest
    ) : Call<TokenResponse>


    @POST("sessions/new")
    fun sendSession(
        @Body token: SessionRequest
    ) : Call<SessionResponse>

    @POST("sessions/")
    fun getSession(
        @Body token: SessionRequest
    ): Call<GetSessionResponse>

    @Multipart
    @POST("images/")
    fun uploadImage(
        @Part file: MultipartBody.Part,
        @Part ("color")color: String,
        @Part ("sessionID") sessionID: String
    ): Call<ImageResponse>

    @POST("recommendations/")
    fun getRecommendationsId(
        @Body sessionId: RecommendationsRequest
    ) : Call<RecommendationsResponse>

    @POST("recommendations/images")
    fun getRecommendationImages(
        @Body request : RecommendationsImageRequest,
    ) : Call<RecommendationsImageResponse>
}
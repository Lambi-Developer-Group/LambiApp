package com.capstone.lambiapp.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.lambiapp.data.request.RecommendationsImageRequest
import com.capstone.lambiapp.data.request.SessionRequest
import com.capstone.lambiapp.data.response.RecommendationsImageResponse
import com.capstone.lambiapp.data.response.SessionResponse
import com.capstone.lambiapp.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendationImageViewModel : ViewModel() {
    private val _recommendationImages = MutableLiveData<RecommendationsImageResponse>()
    val recommendationImages: LiveData<RecommendationsImageResponse> get() = _recommendationImages

    fun getRecommendationImages(sessionId: String, recommendationId: String, context: Context) {
        val request = RecommendationsImageRequest(sessionId, recommendationId)
        val apiService = ApiConfig.getApiService(context)
        val client = apiService.getRecommendationImages(request)
        client.enqueue(object : Callback<RecommendationsImageResponse> {
            override fun onResponse(
                call: Call<RecommendationsImageResponse>,
                response: Response<RecommendationsImageResponse>
            ) {

                if (response.isSuccessful) {

                    // Set the response data
                    _recommendationImages.postValue(response.body())


                }
            }

            override fun onFailure(call: Call<RecommendationsImageResponse>, t: Throwable) {
                // Handle failure if needed

            }
        })
    }
}

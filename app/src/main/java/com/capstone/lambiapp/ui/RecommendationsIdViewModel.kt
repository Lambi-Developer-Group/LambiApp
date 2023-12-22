package com.capstone.lambiapp.ui

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.lambiapp.data.request.RecommendationsRequest
import com.capstone.lambiapp.data.response.RecommendationsResponse
import com.capstone.lambiapp.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendationsIdViewModel : ViewModel() {
    private val _recommendationId = MutableLiveData<String?>()
    val recommendationId: LiveData<String?> get() = _recommendationId

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun getRecommendationsId(sessionId: String, context: Context) {
        ApiConfig.getApiServiceRecommendation(context).getRecommendationsId(RecommendationsRequest(sessionId))
            .enqueue(object : Callback<RecommendationsResponse> {
                override fun onResponse(
                    call: Call<RecommendationsResponse>,
                    response: Response<RecommendationsResponse>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        val recommendationId = data?.recommendationID
                        _recommendationId.postValue(recommendationId)
                        Handler(Looper.getMainLooper()).postDelayed({
                            _recommendationId.postValue(recommendationId)
                        }, 12000)
                    } else {
                        _errorMessage.value = "Failed to get Recommendations id"
                    }
                }

                override fun onFailure(call: Call<RecommendationsResponse>, t: Throwable) {
                    _errorMessage.value = "Network request failed. Please check your internet connection."
                }
            })
    }
}
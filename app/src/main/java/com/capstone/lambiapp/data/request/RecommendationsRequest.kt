package com.capstone.lambiapp.data.request

import com.google.gson.annotations.SerializedName

data class RecommendationsRequest (
    @field:SerializedName("sessionId")
    val sessionId : String? = null
)
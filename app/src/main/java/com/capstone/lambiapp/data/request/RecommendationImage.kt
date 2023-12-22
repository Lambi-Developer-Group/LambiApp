package com.capstone.lambiapp.data.request

import com.google.gson.annotations.SerializedName

data class RecommendationsImageRequest (
    @field:SerializedName("sessionId")
    val sessionId : String? = null,

    @field:SerializedName("recommendationId")
    val recommendationId : String? = null
)
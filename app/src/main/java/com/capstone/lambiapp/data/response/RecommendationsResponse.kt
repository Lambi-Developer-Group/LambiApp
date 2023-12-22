package com.capstone.lambiapp.data.response

import com.google.gson.annotations.SerializedName

data class RecommendationsResponse(

	@field:SerializedName("data")
	val data: RecommendationData? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class RecommendationData(

	@field:SerializedName("recommendationID")
	val recommendationID: String? = null
)

package com.capstone.lambiapp.data.response

import com.google.gson.annotations.SerializedName

data class GetSessionResponse(

	@field:SerializedName("sessionID")
	val sessionID: List<String?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)

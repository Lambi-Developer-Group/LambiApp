package com.capstone.lambiapp.data.response

import com.google.gson.annotations.SerializedName

data class SessionResponse(
	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("sessionID")
	val sessionID: String? = null


)

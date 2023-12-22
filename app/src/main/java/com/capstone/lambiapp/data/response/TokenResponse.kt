package com.capstone.lambiapp.data.response

import com.google.gson.annotations.SerializedName

data class TokenResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("user")
	val user: String? = null
)

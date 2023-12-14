package com.capstone.lambiapp.data.request

import com.google.gson.annotations.SerializedName

data class LoginRequest (
    @field:SerializedName("token")
    val token : String? = null
)
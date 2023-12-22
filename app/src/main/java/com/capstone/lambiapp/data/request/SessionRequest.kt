package com.capstone.lambiapp.data.request

import com.google.gson.annotations.SerializedName

data class SessionRequest (
    @field:SerializedName("token")
    val token : String? = null
)


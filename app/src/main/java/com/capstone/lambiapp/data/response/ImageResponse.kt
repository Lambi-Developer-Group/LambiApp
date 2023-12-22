package com.capstone.lambiapp.data.response

import com.google.gson.annotations.SerializedName

data class ImageResponse(

	@field:SerializedName("data")
	val data: DataImages? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataImages(

	@field:SerializedName("fileName")
	val fileName: String? = null,

	@field:SerializedName("imageID")
	val imageID: String? = null,

	@field:SerializedName("publicUrl")
	val publicUrl: String? = null
)

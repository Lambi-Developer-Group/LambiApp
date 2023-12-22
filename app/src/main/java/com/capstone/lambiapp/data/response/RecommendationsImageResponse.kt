package com.capstone.lambiapp.data.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class RecommendationsImageResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)

@Parcelize
data class Top(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("color_hex")
	val colorHex: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("type")
	val type: String? = null
) : Parcelable
@Parcelize
data class Bottom(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("color_hex")
	val colorHex: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("type")
	val type: String? = null
): Parcelable

@Parcelize
data class DataItem(

	@field:SerializedName("top")
	val top: Top? = null,

	@field:SerializedName("bottom")
	val bottom: Bottom? = null,

	@field:SerializedName("recommendation_id")
	val recommendationId: Int? = null,

	@field:SerializedName("message")
	val message: String? = null
) :Parcelable

package com.mrwhoknows.krishinetworktask.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MandiResponse(
    @Json(name = "code")
    val code: Int,
    @Json(name = "data")
    val body: Data,
    @Json(name = "status")
    val status: String
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "fav_mandi")
        val favMandi: List<Mandi>,
        @Json(name = "other_mandi")
        val otherMandi: List<Mandi>
    )
}
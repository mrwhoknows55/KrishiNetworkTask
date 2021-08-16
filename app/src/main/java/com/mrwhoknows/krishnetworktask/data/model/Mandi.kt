package com.mrwhoknows.krishnetworktask.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "mandi")
@JsonClass(generateAdapter = true)
data class Mandi(
    @Json(name = "crop_id")
    val cropId: Int,
    @Json(name = "district")
    val district: String,
    @Json(name = "district_id")
    val districtId: Int,
    @Json(name = "hindi_name")
    val hindiName: String,
    @PrimaryKey
    @Json(name = "id")
    val id: Int,
    @Json(name = "image")
    val image: String,
    @Json(name = "km")
    val km: Double,
    @Json(name = "last_date")
    val lastDate: String,
    @Json(name = "lat")
    val lat: Double,
    @Json(name = "lng")
    val lng: Double,
    @Json(name = "location")
    val location: String,
    @Json(name = "market")
    val market: String,
    @Json(name = "meters")
    val meters: Double,
    @Json(name = "state")
    val state: String,
    @Json(name = "url_str")
    val urlStr: String
)

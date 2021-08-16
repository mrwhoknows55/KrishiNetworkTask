package com.mrwhoknows.krishinetworktask.api

import com.mrwhoknows.krishinetworktask.data.model.MandiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MandiApi {

    companion object {
        const val BASE_URL = "https://thekrishi.com/test/"
    }

    @GET("mandi")
    suspend fun getMandies(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("ver") version: Int = 89,
        @Query("lang") language: String = "hi",
        @Query("crop_id") cropId: Int
    ): MandiResponse


}
package com.mrwhoknows.krishnetworktask.di

import com.mrwhoknows.krishnetworktask.api.MandiApi
import com.mrwhoknows.krishnetworktask.api.MandiApi.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideMandiApi(retrofit: Retrofit): MandiApi = retrofit.create(MandiApi::class.java)


}
package com.mrwhoknows.krishinetworktask.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.mrwhoknows.krishinetworktask.api.MandiApi
import com.mrwhoknows.krishinetworktask.api.MandiApi.Companion.BASE_URL
import com.mrwhoknows.krishinetworktask.data.database.MandiDatabase
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

    private const val HOME_PREFS = "HOME_PREFS"

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

    @Provides
    @Singleton
    fun provideDatabase(app: Application): MandiDatabase =
        Room.databaseBuilder(app, MandiDatabase::class.java, "mandi_database").build()

    @Provides
    @Singleton
    fun provideHomePrefs(app: Application): SharedPreferences =
        app.getSharedPreferences(HOME_PREFS, Context.MODE_PRIVATE)


}
package com.mrwhoknows.krishinetworktask.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mrwhoknows.krishinetworktask.data.model.Mandi

@Database(entities = [Mandi::class], version = 1)
abstract class MandiDatabase : RoomDatabase() {

    abstract fun getMandiDao(): MandiDao
}
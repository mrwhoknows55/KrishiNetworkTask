package com.mrwhoknows.krishinetworktask.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mrwhoknows.krishinetworktask.data.model.Mandi
import kotlinx.coroutines.flow.Flow

@Dao
interface MandiDao {

    @Query("SELECT * FROM mandi")
    fun getAllMandies(): Flow<List<Mandi>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMandies(restraunts: List<Mandi>)

    @Query("DELETE FROM mandi")
    suspend fun deleteAllMandies()
}
package com.mrwhoknows.krishinetworktask.data

import androidx.room.withTransaction
import com.mrwhoknows.krishinetworktask.api.MandiApi
import com.mrwhoknows.krishinetworktask.data.database.MandiDatabase
import com.mrwhoknows.krishinetworktask.util.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class MandiRepository @Inject constructor(
    private val api: MandiApi,
    private val db: MandiDatabase
) {

    private val mandiDao = db.getMandiDao()

    fun getMandies() = networkBoundResource(
        query = {
            mandiDao.getAllMandies()
        },
        fetch = {
            delay(2000)
            api.getMandies(
                latitude = 28.44108136,
                longitude = 77.0526054,
                cropId = 10
            ).body.otherMandi
        },
        savedFetchResult = { mandies ->
            db.withTransaction {
                mandiDao.deleteAllMandies()
                mandiDao.insertMandies(mandies)
            }
        }
    )


}
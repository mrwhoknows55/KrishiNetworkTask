package com.mrwhoknows.krishinetworktask.data

import android.content.SharedPreferences
import androidx.room.withTransaction
import com.mrwhoknows.krishinetworktask.api.MandiApi
import com.mrwhoknows.krishinetworktask.data.database.MandiDatabase
import com.mrwhoknows.krishinetworktask.util.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class MandiRepository @Inject constructor(
    private val api: MandiApi,
    private val db: MandiDatabase,
    private val homePrefs: SharedPreferences
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

    fun saveName(name: String) {
        homePrefs.edit().also {
            it.putString(NAME_KEY, name)
            it.apply()
        }
    }

    fun getName() = homePrefs.getString(NAME_KEY, null)

    fun saveEmail(email: String) {
        homePrefs.edit().also {
            it.putString(EMAIL_KEY, email)
            it.apply()
        }
    }

    fun getEmail() = homePrefs.getString(EMAIL_KEY, null)

    fun saveEncodedImg(encoded: String) {
        homePrefs.edit().also {
            it.putString(IMG_KEY, encoded)
            it.apply()
        }
    }

    fun getImageEncoded(): String? = homePrefs.getString(IMG_KEY, "")

    companion object {
        private const val IMG_KEY = "IMG_KEY"
        private const val NAME_KEY = "NAME_KEY"
        private const val EMAIL_KEY = "EMAIL_KEY"
    }
}
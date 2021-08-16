package com.mrwhoknows.krishinetworktask.ui.home

import android.graphics.BitmapFactory
import android.util.Base64
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mrwhoknows.krishinetworktask.data.MandiRepository
import com.mrwhoknows.krishinetworktask.data.model.HomeData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MandiRepository
) : ViewModel() {

    private val _homeData = MutableLiveData<HomeData>()
    val homeData: LiveData<HomeData> = _homeData

    fun saveData(name: String, email: String) {
        repository.saveName(name)
        repository.saveEmail(email)
        getData()
    }

    fun saveImageUri(encoded: String) {
        repository.saveEncodedImg(encoded)
        getData()
    }

    private fun getData() {
        val it = repository.getImageEncoded() ?: ""
        val decodedBytes = Base64.decode(it.substring(it.indexOf(",") + 1), Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)

        _homeData.value =
            HomeData(repository.getName(), repository.getEmail(), bitmap)
    }

    init {
        getData()
    }


}
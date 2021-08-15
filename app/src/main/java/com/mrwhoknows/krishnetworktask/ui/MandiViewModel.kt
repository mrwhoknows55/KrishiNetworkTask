package com.mrwhoknows.krishnetworktask.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrwhoknows.krishnetworktask.api.MandiApi
import com.mrwhoknows.krishnetworktask.data.model.Mandi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MandiViewModel"

@HiltViewModel
class MandiViewModel @Inject constructor(
    api: MandiApi
) : ViewModel() {

    val mandies = MutableLiveData<List<Mandi>>()

    init {
        Log.d(TAG, "Initialized: ")
        viewModelScope.launch {
            try {
                val result =
                    api.getMandies(latitude = 28.44108136, longitude = 77.0526054, cropId = 10)
                delay(3000)
                Log.d(TAG, result.toString())
                mandies.value = result.body.otherMandi
            } catch (throwable: Throwable) {
                Log.e(TAG, throwable.stackTraceToString())
            }
        }
    }

}
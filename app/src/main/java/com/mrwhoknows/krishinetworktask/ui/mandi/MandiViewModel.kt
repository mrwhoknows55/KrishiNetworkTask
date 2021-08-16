package com.mrwhoknows.krishinetworktask.ui.mandi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mrwhoknows.krishinetworktask.data.MandiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MandiViewModel @Inject constructor(
    repository: MandiRepository
) : ViewModel() {

    val mandies = repository.getMandies().asLiveData()
}
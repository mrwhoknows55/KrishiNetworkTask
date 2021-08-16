package com.mrwhoknows.krishnetworktask.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mrwhoknows.krishnetworktask.data.MandiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MandiViewModel @Inject constructor(
    repository: MandiRepository
) : ViewModel() {

    val mandies = repository.getMandies().asLiveData()
}
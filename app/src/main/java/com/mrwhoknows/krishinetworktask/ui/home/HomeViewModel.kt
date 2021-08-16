package com.mrwhoknows.krishinetworktask.ui.home

import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    fun saveData(name: String, email: String) {
        println("$name, $email")
    }

}
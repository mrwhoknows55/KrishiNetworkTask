package com.mrwhoknows.krishnetworktask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.mrwhoknows.krishnetworktask.databinding.ActivityMandiBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MandiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMandiBinding
    private val viewModel: MandiViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMandiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.mandies.observe(this) { result ->
            println(result.data)
            println(result.error)
        }
    }
}
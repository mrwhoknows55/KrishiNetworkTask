package com.mrwhoknows.krishinetworktask.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mrwhoknows.krishinetworktask.databinding.ActivityHomeBinding
import com.mrwhoknows.krishinetworktask.ui.mandi.MandiActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fetchBtn.setOnClickListener {
            Intent(this, MandiActivity::class.java).also {
                startActivity(it)
            }
        }

    }
}
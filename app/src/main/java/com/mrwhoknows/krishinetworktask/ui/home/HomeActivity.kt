package com.mrwhoknows.krishinetworktask.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.mrwhoknows.krishinetworktask.databinding.ActivityHomeBinding
import com.mrwhoknows.krishinetworktask.ui.mandi.MandiActivity
import java.util.regex.Pattern

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var name: String
    private lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.submitBtn.setOnClickListener {
            if (getInput()) {
                viewModel.saveData(name, email)
                binding.resultTextView.text = "Name: $name\nEmail: $email"
            }
        }

        binding.fetchBtn.setOnClickListener {
            Intent(this, MandiActivity::class.java).also {
                startActivity(it)
            }
        }

    }


    private fun getInput(): Boolean {
        name = binding.nameTextInputLayout.editText?.text.toString()
        email = binding.emailTextInputLayout.editText?.text.toString()

        if (name.isEmpty()) {
            binding.nameTextInputLayout.error = "Please Enter Valid Name"
            return false
        } else {
            binding.nameTextInputLayout.error = null
        }

        if (email.isEmpty()
            || !Pattern.matches("[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+", email)
        ) {
            binding.emailTextInputLayout.error = "Please Enter Valid Email"
            return false
        } else {
            binding.emailTextInputLayout.error = null
        }
        return true
    }
}
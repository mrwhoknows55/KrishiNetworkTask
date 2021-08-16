package com.mrwhoknows.krishinetworktask.ui.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar
import com.mrwhoknows.krishinetworktask.databinding.ActivityHomeBinding
import com.mrwhoknows.krishinetworktask.ui.mandi.MandiActivity
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream
import java.util.regex.Pattern

private const val IMG_REQ_ID = 1

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var name: String
    private lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.selectImgBtn.setOnClickListener {
            if (
                ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 10
            )

            val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                type = "image/*"
            }
            startActivityForResult(Intent.createChooser(intent, "Select Image"), IMG_REQ_ID)
        }


        binding.submitBtn.setOnClickListener {
            if (getInput()) {
                viewModel.saveData(name, email)
            }
        }

        binding.fetchBtn.setOnClickListener {
            Intent(this, MandiActivity::class.java).also {
                startActivity(it)
            }
        }

        viewModel.homeData.observe(this) { homeData ->
            if (!homeData.name.isNullOrEmpty() && !homeData.email.isNullOrEmpty()) {
                binding.resultTextView.text = "Name: ${homeData.name}\nEmail: ${homeData.email}"
            }

            homeData.image?.also {
                binding.imageView.setImageBitmap(it)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == IMG_REQ_ID && resultCode == RESULT_OK) {
            try {
                val uri = data?.data as Uri
                val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
                val outputStream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                val encoded: String =
                    Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)
                viewModel.saveImageUri(encoded)

            } catch (e: Exception) {
                e.printStackTrace()
                Snackbar.make(
                    binding.root,
                    e.localizedMessage ?: "Something Went Wrong",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
}
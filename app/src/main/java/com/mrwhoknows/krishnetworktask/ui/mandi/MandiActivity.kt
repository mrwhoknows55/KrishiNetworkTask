package com.mrwhoknows.krishnetworktask.ui.mandi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mrwhoknows.krishnetworktask.databinding.ActivityMandiBinding
import com.mrwhoknows.krishnetworktask.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MandiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMandiBinding
    private val viewModel: MandiViewModel by viewModels()
    private val adapter: MandiAdapter by lazy { MandiAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMandiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            mandiRecyclerView.apply {
                adapter = this@MandiActivity.adapter
                layoutManager = LinearLayoutManager(this@MandiActivity)
            }

            viewModel.mandies.observe(this@MandiActivity) { result ->
                adapter.submitList(result.data)

                progressBar.isVisible =
                    result is Resource.Loading && result.data.isNullOrEmpty()

                if (result is Resource.Error && result.data.isNullOrEmpty()) {
                    Snackbar.make(
                        root,
                        result.error?.message ?: "Something Went Wrong",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
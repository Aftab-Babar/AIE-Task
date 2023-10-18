package com.example.aietaskapi.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aietaskapi.adapter.HomeAdapter
import com.example.aietaskapi.databinding.ActivityMainBinding
import com.example.aietaskapi.helper.AllConstants
import com.example.aietaskapi.helper.AllConstants.gone
import com.example.aietaskapi.helper.AllConstants.show
import com.example.aietaskapi.helper.AllConstants.showToast
import com.example.aietaskapi.helper.MyResult
import com.example.aietaskapi.model.Employee
import com.example.aietaskapi.services.ForegroundService
import com.example.aietaskapi.viewmodel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: DetailsViewModel by viewModels()
    private lateinit var adapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val apiService = RetrofitClient.createApiService()
//        val detailsRepository = DetailsRepository(apiService, BaseApp.database.detailResponseDao())
//        val viewModelFactory = DetailsViewModelFactory(detailsRepository)
//        viewModel = ViewModelProvider(this, viewModelFactory)[DetailsViewModel::class.java]

        observeLogin()

    }

    private fun startServices() {
        startService(Intent(this@MainActivity, ForegroundService::class.java))
    }

    private fun stopServices() {
        ForegroundService.stopService(this)
    }

    private fun observeLogin() {
        binding.btnFetch.setOnClickListener { fetchData() }
        viewModel.detailsFromDatabase.observe(this) { details ->
            if (details.isEmpty()) {
                fetchData()
            }
            initGridView(details)
            Log.e("checkResp", details.toString() + ":  ")

            // Update your UI with the list of details from the database
        }
    }

    private fun fetchData() {
        binding.progressBar.show()
        AllConstants.saveCurrentDateTimeToSharedPreferences(this)
        startServices()
        viewModel.loginResult.observe(this) { mresult ->
            Log.e("checkResp", mresult.toString() + ":  ")
            when (mresult) {
                is MyResult.Success -> {
                    Log.e("checkResp", "Done")
                    showToast("Data fetched from server successfully")
                    // val data:List<Employee> = mresult.data
                    //initGridView(data)
                    initGridView(mresult.data)
                    binding.progressBar.gone()
                    stopServices()
                    // Handle successful login and data storage
                }

                is MyResult.Error -> {
                    // Handle login error
                    binding.progressBar.gone()
                    stopServices()
                    showToast(mresult.message + " Error")
                }

                else -> {}
            }
        }
    }

    private fun initGridView(list: List<Employee>) {
        binding.apply {
            homeRV.layoutManager = GridLayoutManager(this@MainActivity, 1)
            adapter = HomeAdapter(::click, this@MainActivity)
            homeRV.adapter = adapter // Set the correct adapter here
            adapter.submitList(list)
        }
    }

    private fun click(i: Int) {

    }
}

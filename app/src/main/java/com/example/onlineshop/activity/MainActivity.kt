package com.example.onlineshop.activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlineshop.Adapter.BestSellerAdpater
import com.example.onlineshop.Adapter.CategoryAdapter
import com.example.onlineshop.R
import com.example.onlineshop.ViewModel.MainViewModel
import com.example.onlineshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val  viewModel= MainViewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCategories()
        initBestSeller()

    }


    private fun initBestSeller() {
        binding.progressBarBestSeller.visibility= View.VISIBLE
        viewModel.bestSeller.observe(this, Observer {
            binding.rvBestSellers.layoutManager= LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false
            )
            binding.rvBestSellers.adapter= BestSellerAdpater(it)
            binding.progressBarBestSeller.visibility= View.GONE
        })
    }
    private fun initCategories() {
        binding.progressBarCategories.visibility= View.VISIBLE

        viewModel.category.observe(this, Observer {
            binding.rvCategories.layoutManager= LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.rvCategories.adapter= CategoryAdapter(it)
            binding.progressBarCategories.visibility= View.GONE
        })
    }
}
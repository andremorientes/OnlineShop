package com.example.onlineshop.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlineshop.Adapter.BestSellerAdapter
import com.example.onlineshop.Adapter.CategoryAdapter
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
        bottomNavigation()

    }

    private fun bottomNavigation() {
        binding.carrinho.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

    }


    private fun initBestSeller() {
        binding.progressBarBestSeller.visibility= View.VISIBLE
        viewModel.bestSeller.observe(this, Observer {
            binding.rvBestSellers.layoutManager= LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false
            )
            binding.rvBestSellers.adapter= BestSellerAdapter(it)
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
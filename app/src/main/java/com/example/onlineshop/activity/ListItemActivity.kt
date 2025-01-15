package com.example.onlineshop.activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlineshop.Adapter.ListItemAdapter
import com.example.onlineshop.R
import com.example.onlineshop.ViewModel.MainViewModel
import com.example.onlineshop.databinding.ActivityListItemBinding

class ListItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListItemBinding
    private val viewModel = MainViewModel()
    private var id: String = ""
    private var title: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListItemBinding.inflate(layoutInflater)
        setContentView(binding.root)


        getBundle()

        initList()


    }

    private fun initList(){
        binding.apply {
            progressBarItemList.visibility=View.VISIBLE
            viewModel.loadItem(id).observe(this@ListItemActivity, Observer{
                rvListItem.layoutManager=
                    LinearLayoutManager(this@ListItemActivity, LinearLayoutManager.VERTICAL, false)
                rvListItem.adapter= ListItemAdapter(it)

                progressBarItemList.visibility= View.GONE
            })

            binding.ivBack.setOnClickListener {
                finish()

            }
        }
    }

    private fun getBundle(){
        id= intent.getStringExtra("id")!!
        title=intent.getStringExtra("title")!!

        binding.categrieTitle.text=title
    }
}
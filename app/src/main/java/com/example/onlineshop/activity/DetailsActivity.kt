package com.example.onlineshop.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.onlineshop.Adapter.PicListAdapter
import com.example.onlineshop.Adapter.SizeListAdapter
import com.example.onlineshop.Helper.ManagmentCart
import com.example.onlineshop.databinding.ActivityDetailsBinding
import com.example.onlineshop.model.ItemModels

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var item: ItemModels
    private  var numberOrder= 1
    private lateinit var managmentCart: ManagmentCart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        managmentCart= ManagmentCart(this)
        getBundle()
        initList()

    }

    private fun initList() {
        val colorList= ArrayList<String>()
        for (imageUrl in item.picUrl){
            colorList.add(imageUrl)

        }
        Glide.with(this)
            .load(colorList[0])
            .into(binding.imgPic)

        binding.rvListPic.adapter= PicListAdapter(colorList,binding.imgPic)
        binding.rvListPic.layoutManager=
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val sizeList= ArrayList<String>()
        for (size in item.size){
            sizeList.add(size)

        }

        binding.rvListSize.adapter= SizeListAdapter(sizeList)
        binding.rvListSize.layoutManager=
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    }


    private fun getBundle(){
        item=(intent.getSerializableExtra("object") as? ItemModels?) !!

        binding.apply {
            tvTitle.text= item.title
            tvDescriptionDetails.text= item.description
            tvPrice.text= "$"+item.price
            tvRating.text=item.rating.toString()

            btnAddCart.setOnClickListener {
                item.numberInCart= numberOrder
                managmentCart.insertItems(item)
            }

        }


        binding.btnBack.setOnClickListener {
            startActivity(Intent(
                this, MainActivity::class.java))


        }

        binding.btnCart.setOnClickListener {

        }



    }
}
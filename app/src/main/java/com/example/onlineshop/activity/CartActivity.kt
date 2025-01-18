package com.example.onlineshop.activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlineshop.Adapter.CartAdapter
import com.example.onlineshop.Helper.ChangeNumberItemsListener
import com.example.onlineshop.Helper.ManagmentCart
import com.example.onlineshop.R
import com.example.onlineshop.databinding.ActivityCartBinding
import java.math.BigDecimal
import java.math.RoundingMode

class CartActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCartBinding
    private lateinit var managmentCart : ManagmentCart
    private var tax: Double=0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart= ManagmentCart(this)
        seVariable()

        initCarlist()
        calculationCart()

    }

    private fun initCarlist() {


        binding.rvCart.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.rvCart.adapter= CartAdapter(managmentCart.getListCart(), this,object : ChangeNumberItemsListener{
            override fun onChanged() {
                calculationCart()
            }

        })
    }

    private fun calculationCart() {
        val percentTax = BigDecimal("0.02")
        val delivery = BigDecimal("15.0")

        // Total fee from the cart
        val totalFee = BigDecimal(managmentCart.getTotalFee())

        // Calculate tax
        val tax = totalFee.multiply(percentTax).setScale(2, RoundingMode.HALF_UP)

        // Calculate total
        val total = totalFee.add(tax).add(delivery).setScale(2, RoundingMode.HALF_UP)

        // Calculate item total
        val itemTotal = totalFee.setScale(2, RoundingMode.HALF_UP)


        binding.apply {
            tvDeliver.text = "$delivery"
            tvTotalTax.text = "$tax"
            tvTotalSub.text = "$itemTotal"
            tvTotal.text = "$total"
        }

    }

    private fun seVariable() {
        binding.btnBack.setOnClickListener {
                finish()
        }
    }
}
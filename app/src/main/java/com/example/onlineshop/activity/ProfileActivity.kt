package com.example.onlineshop.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.onlineshop.R
import com.example.onlineshop.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val  auth = FirebaseAuth.getInstance()
        binding.apply {

            btnBack.setOnClickListener {
                finish()
            }

            btnLogOut.setOnClickListener {
                auth.signOut()
                finish()
                startActivity(Intent(this@ProfileActivity, LoginActivity::class.java))
            }
        }

    }
}
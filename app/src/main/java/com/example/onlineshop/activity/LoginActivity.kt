package com.example.onlineshop.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.onlineshop.Factory.AuthViewModelFactory
import com.example.onlineshop.R
import com.example.onlineshop.Repository.AuthRepository
import com.example.onlineshop.ViewModel.AuthViewModel
import com.example.onlineshop.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var viewModel: AuthViewModel

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = AuthRepository()
        val factory = AuthViewModelFactory(repository)
        viewModel= ViewModelProvider(this,factory).get(AuthViewModel::class.java)

        binding.apply {


            btnlogin.setOnClickListener {
                val email = tieEmail.text.toString()
                val password = tiePassword.text.toString()

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(this@LoginActivity, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                binding.progresBarLogin.visibility = View.VISIBLE
                viewModel.login(email, password)
            }

        }

        viewModel.loginStatus.observe(this, Observer { result ->
            binding.progresBarLogin.visibility = View.GONE

            result.onSuccess {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }.onFailure {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        })
    }



}
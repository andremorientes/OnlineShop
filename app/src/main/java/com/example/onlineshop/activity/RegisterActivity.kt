package com.example.onlineshop.activity

import android.content.Intent
import android.os.Bundle
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
import com.example.onlineshop.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = AuthRepository()
        val factory = AuthViewModelFactory(repository)
        viewModel= ViewModelProvider(this,factory).get(AuthViewModel::class.java)

        binding.apply {


            btnSignUp.setOnClickListener {
                val name= tieName.text.toString()
                val email= tieEmail.text.toString()
                val password= tiePassword.text.toString()
                if (name.isEmpty() ||email.isEmpty() || password.isEmpty()){
                 Toast.makeText(this@RegisterActivity,"Please fill all fields", Toast.LENGTH_SHORT).show()
                }else{
                    viewModel.register(email, password)
                }
            }

            btnSignIn.setOnClickListener {
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                finish()
            }

        }
        viewModel.registrationStatus.observe(this, Observer { result->

            result.onSuccess {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }.onFailure {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }

        })



    }
}
package com.example.onlineshop.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.onlineshop.Repository.AuthRepository
import com.example.onlineshop.ViewModel.AuthViewModel

class AuthViewModelFactory(private  val repository: AuthRepository): ViewModelProvider.Factory {
    override fun <T: ViewModel> create (modelClass: Class<T>):T{
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)){
            return AuthViewModel(repository) as T

        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
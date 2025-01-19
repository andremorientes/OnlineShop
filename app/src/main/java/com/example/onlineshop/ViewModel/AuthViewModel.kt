package com.example.onlineshop.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlineshop.Repository.AuthRepository

class AuthViewModel(
    private val authRepository: AuthRepository
):ViewModel() {

    private val _registrationStatus = MutableLiveData<Result<String>>()
    val registrationStatus: LiveData<Result<String>> get() = _registrationStatus

    private val _loginStatus = MutableLiveData<Result<String>>()
    val loginStatus: LiveData<Result<String>> get() = _loginStatus


    fun register(email: String, password: String) {
        authRepository.register(email, password).observeForever { result ->
            _registrationStatus.value = result
        }
    }

    // Função para login do usuário
    fun login(email: String, password: String) {
        authRepository.login(email, password).observeForever { result ->
            _loginStatus.value = result
        }
    }
}
package com.example.onlineshop.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth

class AuthRepository {
    private val auth: FirebaseAuth= FirebaseAuth.getInstance()

    fun register(email: String, password: String): LiveData<Result<String>>{

        val result= MutableLiveData<Result<String>>()
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener { task->
                result.value= if (task.isSuccessful){
                    Result.success("User registered successfully")
                }else{
                    Result.failure(task.exception ?: Exception("Registration failed"))
                }
            }
        return result
    }

    fun login(email: String, password: String): LiveData<Result<String>> {
        val result = MutableLiveData<Result<String>>()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    result.postValue(Result.success("Login successful"))
                } else {
                    result.postValue(Result.failure(task.exception ?: Exception("Login failed")))
                }
            }
        return result
    }

}
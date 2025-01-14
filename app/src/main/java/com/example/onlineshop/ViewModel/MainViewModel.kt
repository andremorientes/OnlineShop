package com.example.onlineshop.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.onlineshop.Repository.MainRepository
import com.example.onlineshop.model.CategoryModel
import com.example.onlineshop.model.ItemModels

class MainViewModel(): ViewModel() {

    private val repository= MainRepository()

    val category: LiveData<MutableList<CategoryModel>> = repository.loadCategory()

    val bestSeller: LiveData<MutableList<ItemModels>> = repository.loadBestSeller()
}
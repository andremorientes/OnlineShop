package com.example.onlineshop.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.onlineshop.model.CategoryModel
import com.example.onlineshop.model.ItemModels
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class MainRepository {

    private val firebaseDatabase= FirebaseDatabase.getInstance()

    fun loadCategory(): LiveData<MutableList<CategoryModel>>{
        val categoryLiveData= MutableLiveData<MutableList<CategoryModel>>()
        val ref= firebaseDatabase.getReference("Category")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<CategoryModel>()
                for(chilldSnapshot in snapshot.children){
                    val list = chilldSnapshot.getValue(CategoryModel::class.java)
                    if(list != null) {
                        lists.add(list)
                    }
                }

                categoryLiveData.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
        return categoryLiveData

    }


     fun loadBestSeller(): LiveData<MutableList<ItemModels>>{
        val bestSellerLiveData= MutableLiveData<MutableList<ItemModels>>()
        val ref= firebaseDatabase.getReference("BestSeller")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                 val lists= mutableListOf<ItemModels>()
                for (chilldSnapshot in snapshot.children){
                    val list= chilldSnapshot.getValue(ItemModels::class.java)
                    if (list != null) {
                        lists.add(list)
                    }

                }
                bestSellerLiveData.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        return bestSellerLiveData

    }


    fun loadItem(categoryId: String): LiveData<MutableList<ItemModels>>{
        val itemLiveData= MutableLiveData<MutableList<ItemModels>>()
        val ref= firebaseDatabase.getReference("Items")

        val query: Query = ref.orderByChild("categoryId").equalTo(categoryId)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<ItemModels>()
                for (chilldSnapshot in snapshot.children){
                    val list= chilldSnapshot.getValue(ItemModels::class.java)
                    if (list != null) {
                        lists.add(list)
                    }

                }
                itemLiveData.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        return itemLiveData

    }
}
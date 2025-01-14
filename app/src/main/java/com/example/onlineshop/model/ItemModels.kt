package com.example.onlineshop.model

import java.io.Serializable

data class ItemModels(
    val title:String="",
    val description:String="",
    val logo:String="",
    val categoryId:String="",
    val picUrl: ArrayList<String> = ArrayList(),
    val size: ArrayList<String> = ArrayList(),
    val price: Double=0.0,
    var rating: Double=0.0,
    val numberInChart: Int=0

): Serializable

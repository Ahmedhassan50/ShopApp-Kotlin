package com.example.kotlinShopApp.db.model

import com.google.gson.annotations.SerializedName

data class CategoryModel(
    val status:Boolean,
    @SerializedName("data")
    val categoriesData:CategoryData,
)
data class CategoryData(
    val data:List<Category>
)
data class Category(
    val id:Int,
    val name:String,
    val image:String
)

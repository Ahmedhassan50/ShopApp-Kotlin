package com.example.kotlinShopApp.db.model

import com.google.gson.annotations.SerializedName

data class SearchModel (
    val status:Boolean,

    @SerializedName("data")
    val searchData:SearData
)

data class SearData(
    val data:List<Product>
)
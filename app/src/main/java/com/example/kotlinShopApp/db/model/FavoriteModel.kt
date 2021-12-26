package com.example.kotlinShopApp.db.model

import com.google.gson.annotations.SerializedName

data class FavoriteModel(
    val status:Boolean,
    @SerializedName("data")
    val favoriteData:FavoriteData
)
data class FavoriteData(
    val data:List<Favorite>
)

data class Favorite(
    val id:Int,
    val product:Product
)

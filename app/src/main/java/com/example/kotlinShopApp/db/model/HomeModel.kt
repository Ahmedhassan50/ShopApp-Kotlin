package com.example.kotlinShopApp.db.model

import com.google.gson.annotations.SerializedName

data class HomeModel(
    val status:Boolean,
    val data:HomeData
)

data class HomeData (
    val banners:List<BannerItem>,
    val products:List<Product>
        )

data class BannerItem(
    val id:Int,
    @SerializedName("image")
    val BannerImage:String,
)

data class Product(
    val id:Int,
    val price:Double,
    @SerializedName("old_price")
    val oldPrice:Double,
    val discount:Int,
    @SerializedName("image")
    val productImage:String,
    val name:String,
    val description:String,
    @SerializedName("in_favorites")
    val isFavorite:Boolean

)
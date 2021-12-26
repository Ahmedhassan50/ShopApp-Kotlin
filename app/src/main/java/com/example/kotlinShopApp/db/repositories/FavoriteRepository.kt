package com.example.kotlinShopApp.db.repositories

import com.example.kotlinShopApp.db.remote.FavoriteInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoriteRepository(private  val api:FavoriteInterface) {

    suspend fun getFavorite()= withContext(Dispatchers.IO){
        api.getFavorites()
    }
    suspend fun addTOFavorite(productId:Int)= withContext(Dispatchers.IO){

        api.addToFavorite(body = mapOf("product_id" to productId))
    }



}
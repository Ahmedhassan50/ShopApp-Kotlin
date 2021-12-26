package com.example.kotlinShopApp.db.repositories

import com.example.kotlinShopApp.db.remote.HomeInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository(private val api:HomeInterface) {

    suspend fun getHomeData()= withContext(Dispatchers.IO){
        api.getHomeData()
    }
    suspend fun getCategoriesData()= withContext(Dispatchers.IO){
        api.getCategories()
    }
}
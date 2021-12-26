package com.example.kotlinShopApp.db.repositories

import com.example.kotlinShopApp.db.remote.SearchInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchRepository(private val api:SearchInterface) {

    suspend fun search(text:String)= withContext(Dispatchers.IO){
        api.search(body= mapOf("text" to text))
    }


}
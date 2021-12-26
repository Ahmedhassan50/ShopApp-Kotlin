package com.example.kotlinShopApp.db.repositories

import com.example.kotlinShopApp.db.remote.AuthInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository (private val api:AuthInterface){

    suspend fun login(email:String,password:String)= withContext(Dispatchers.IO){
        api.login(userCredential=mapOf("email" to email,"password" to password))
    }

}
package com.example.kotlinShopApp.db.remote

import com.example.kotlinShopApp.db.model.AppLanguage
import com.example.kotlinShopApp.db.model.LoginModel
import com.example.kotlinShopApp.utils.LOGIN
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthInterface {

    @Headers(
        "Content-Type:application/json"
    )
    @POST(LOGIN)
    suspend fun login(
        @Header("lang")lang:String?= AppLanguage.local.toString(),
        @Body userCredential:Map<String,String>):Response<LoginModel>
}
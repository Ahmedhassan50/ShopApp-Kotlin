package com.example.kotlinShopApp.db.remote

import com.example.kotlinShopApp.db.model.AppLanguage
import com.example.kotlinShopApp.db.model.SearchModel
import com.example.kotlinShopApp.db.model.UserData
import com.example.kotlinShopApp.utils.SEARCH
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface SearchInterface {
    @Headers(

        "Content-Type:application/json",
    )
    @POST(SEARCH)
    suspend fun search(
        @Header("lang")lang:String?= AppLanguage.local.toString(),
        @Header("Authorization")token:String? = UserData.user?.token, @Body body:Map<String,String>):Response<SearchModel>

}
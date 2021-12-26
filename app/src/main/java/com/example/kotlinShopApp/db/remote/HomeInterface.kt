package com.example.kotlinShopApp.db.remote

import com.example.kotlinShopApp.db.model.AppLanguage
import com.example.kotlinShopApp.db.model.CategoryModel
import com.example.kotlinShopApp.db.model.HomeModel
import com.example.kotlinShopApp.db.model.UserData
import com.example.kotlinShopApp.utils.CATEGORIES
import com.example.kotlinShopApp.utils.HOME
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface HomeInterface {
    @Headers(

        "Content-Type:application/json",
    )
    @GET(HOME)
    suspend fun getHomeData(
        @Header("lang")lang:String?=AppLanguage.local.toString(),
        @Header("Authorization")token:String? =UserData.user?.token, ):Response<HomeModel>

    @Headers(

        "Content-Type:application/json",
    )
    @GET(CATEGORIES)
    suspend fun getCategories(
        @Header("lang")lang:String?=AppLanguage.local.toString(),
        @Header("Authorization")token:String? =UserData.user?.token):Response<CategoryModel>
}
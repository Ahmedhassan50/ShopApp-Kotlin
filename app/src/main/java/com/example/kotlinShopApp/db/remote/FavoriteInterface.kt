package com.example.kotlinShopApp.db.remote

import com.example.kotlinShopApp.db.model.AppLanguage
import com.example.kotlinShopApp.db.model.FavoriteModel
import com.example.kotlinShopApp.db.model.UserData
import com.example.kotlinShopApp.utils.FAVORITES
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface FavoriteInterface {
    @Headers(

        "Content-Type:application/json",
    )
    @GET(FAVORITES)
    suspend fun getFavorites (
        @Header("lang")lang:String?= AppLanguage.local.toString(),
        @Header("Authorization")token:String? = UserData.user?.token):Response<FavoriteModel>

    @Headers(

        "Content-Type:application/json",
    )
    @POST(FAVORITES)
    suspend fun addToFavorite(
        @Header("lang")lang:String?=AppLanguage.local.toString(),
        @Header("Authorization")token:String? = UserData.user?.token ,@Body body:Map<String,Int>):Response<JsonObject>

}
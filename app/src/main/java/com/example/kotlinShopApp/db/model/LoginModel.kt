package com.example.kotlinShopApp.db.model

data class LoginModel (
    val status:Boolean,
    val message:String,
    val data:UserModel
)

data class UserModel(
    val id:Int,
    val name:String,
    val email:String,
    val phone:String,
    val image:String,
    val token:String

)
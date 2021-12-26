package com.example.kotlinShopApp.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinShopApp.db.model.LoginModel
import com.example.kotlinShopApp.db.model.UserData
import com.example.kotlinShopApp.db.remote.ApiService
import com.example.kotlinShopApp.db.remote.AuthInterface
import com.example.kotlinShopApp.db.repositories.AuthRepository
import com.example.kotlinShopApp.ui.auth.login.LoginFragment
import com.example.kotlinShopApp.utils.ServiceStatus
import com.example.kotlinShopApp.utils.StatusInterface
import kotlinx.coroutines.launch
import java.lang.Exception

class AuthViewModel :ViewModel(){
    private var authRepository:AuthRepository
    private var statusInterface:StatusInterface?=null

     var errorMessage:String?=null


    fun addContext(context:LoginFragment){
        statusInterface=context
    }

    init {
        val serviceInstance=ApiService.getRetrofitBuilder().create(AuthInterface::class.java)
        authRepository= AuthRepository(serviceInstance)

    }

    fun login(email:String,password:String)=viewModelScope.launch {
        statusInterface?.getStatus(ServiceStatus.LOADING)
    try {
        val result= authRepository.login(email,password)
        if (result.isSuccessful){
            if (result.body()!=null){

                val userLogin =result.body() as LoginModel
                if (userLogin.status ){

                    UserData.user =userLogin.data
                    statusInterface?.getStatus(ServiceStatus.DONE)

                }else{

                    errorMessage=userLogin.message
                    statusInterface?.getStatus(ServiceStatus.ERROR)

                }

            }
        }else{

            errorMessage=result.message()
            statusInterface?.getStatus(ServiceStatus.ERROR)
        }

    }catch (e:Exception){

        errorMessage=e.message
        statusInterface?.getStatus(ServiceStatus.ERROR)
    }


    }

}
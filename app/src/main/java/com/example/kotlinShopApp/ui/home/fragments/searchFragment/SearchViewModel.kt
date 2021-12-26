package com.example.kotlinShopApp.ui.home.fragments.searchFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinShopApp.db.model.FavoriteModel
import com.example.kotlinShopApp.db.model.SearchModel
import com.example.kotlinShopApp.db.remote.ApiService
import com.example.kotlinShopApp.db.remote.SearchInterface
import com.example.kotlinShopApp.db.repositories.SearchRepository
import com.example.kotlinShopApp.ui.auth.login.LoginFragment
import com.example.kotlinShopApp.utils.ServiceStatus
import com.example.kotlinShopApp.utils.StatusInterface
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchViewModel :ViewModel(){

    private var searchRepository:SearchRepository
    private var statusInterface: SearchStatus?=null


    fun addContext(context: SearchStatus){
        statusInterface =context
    }

    init {
        val serviceInstance=ApiService.getRetrofitBuilder().create(SearchInterface::class.java)
        searchRepository= SearchRepository(serviceInstance)
    }

    fun search(text:String)=viewModelScope.launch {
        try {
            statusInterface?.getStatus(ServiceStatus.LOADING)
            val result= searchRepository.search(text)
            if (result.isSuccessful){
                if (result.body()!=null){
                    statusInterface?.getStatus(ServiceStatus.DONE)
                    statusInterface?.searchResult( result.body() as SearchModel)

                }
            }

        }catch (e:Exception){

        }

    }

}
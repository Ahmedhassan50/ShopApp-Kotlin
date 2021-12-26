package com.example.kotlinShopApp.ui.home.fragments.favoriteFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinShopApp.db.model.FavoriteModel
import com.example.kotlinShopApp.db.remote.ApiService
import com.example.kotlinShopApp.db.remote.FavoriteInterface
import com.example.kotlinShopApp.db.repositories.FavoriteRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class FavoriteViewModel:ViewModel() {

    private var favoriteRepository:FavoriteRepository

    private var _favoriteData=MutableLiveData<FavoriteModel?>()
    val favoriteData:LiveData<FavoriteModel?>get() = _favoriteData

    init {
        val serviceInstance= ApiService.getRetrofitBuilder().create(FavoriteInterface::class.java)
        favoriteRepository= FavoriteRepository(serviceInstance)
    }
fun clear(){
    _favoriteData.postValue(null)
}

    fun getFavorites()=viewModelScope.launch {
        try{
            val result=favoriteRepository.getFavorite()
            if (result.isSuccessful){
                if (result.body()!=null){
                    _favoriteData.postValue(result.body())
                }
            }

        }catch (e:Exception){

        }
    }
    fun addToFavorites(id:Int)=viewModelScope.launch {

        try {
            val result = favoriteRepository.addTOFavorite(id)
            if (result.isSuccessful){
                if (result.body()!=null){
                    Log.i("favorite",result.body().toString())
                }
            }
        }catch (e:Exception){

        }
    }


}
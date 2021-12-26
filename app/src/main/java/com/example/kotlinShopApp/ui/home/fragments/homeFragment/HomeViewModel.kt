package com.example.kotlinShopApp.ui.home.fragments.homeFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinShopApp.db.model.Category
import com.example.kotlinShopApp.db.model.CategoryModel
import com.example.kotlinShopApp.db.model.HomeModel
import com.example.kotlinShopApp.db.remote.ApiService
import com.example.kotlinShopApp.db.remote.HomeInterface
import com.example.kotlinShopApp.db.repositories.HomeRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel :ViewModel() {

    private var homeRepository:HomeRepository

    private var _homeData=MutableLiveData<HomeModel?>()
    val homeData:LiveData<HomeModel?> get() = _homeData
    var categoriesList:List<Category> = emptyList()

    init {
        val serviceInstance=ApiService.getRetrofitBuilder().create(HomeInterface::class.java)
        homeRepository= HomeRepository(serviceInstance)
    }

    fun clear(){
        _homeData.postValue(null)
    }





    fun getHomeData()=viewModelScope.launch {
        try {
            val result=homeRepository.getHomeData()
            val categoryResult=homeRepository.getCategoriesData()
            if (categoryResult.isSuccessful){
                if (categoryResult.body()!=null){
                    val categoryModel=categoryResult.body() as CategoryModel
                    categoriesList= categoryModel.categoriesData.data
                }
            }
            if (result.isSuccessful){
                if (result.body()!=null){
                    _homeData.postValue(result.body())
                }
            }


        }catch (e:Exception){

        }
    }


}
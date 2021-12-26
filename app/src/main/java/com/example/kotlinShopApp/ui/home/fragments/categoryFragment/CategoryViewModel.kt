package com.example.kotlinShopApp.ui.home.fragments.categoryFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinShopApp.db.model.CategoryModel
import com.example.kotlinShopApp.db.remote.ApiService
import com.example.kotlinShopApp.db.remote.HomeInterface
import com.example.kotlinShopApp.db.repositories.HomeRepository
import kotlinx.coroutines.launch
import retrofit2.create
import java.lang.Exception

class CategoryViewModel:ViewModel() {

    private var homeRepository: HomeRepository

    private var _categoryData=MutableLiveData<CategoryModel?>()
    val categoryData:LiveData<CategoryModel?> get() = _categoryData

    init {
        val serviceInstance=ApiService.getRetrofitBuilder().create(HomeInterface::class.java)
        homeRepository= HomeRepository(serviceInstance)
    }

    fun clear(){
        _categoryData.postValue(null)
    }

    fun getCategories()=viewModelScope.launch {
        try{
            val result=homeRepository.getCategoriesData()
            if (result.isSuccessful){
                if (result.body()!=null){
                    _categoryData.postValue(result.body())
                }
            }
        }catch (e:Exception){

        }
    }

}
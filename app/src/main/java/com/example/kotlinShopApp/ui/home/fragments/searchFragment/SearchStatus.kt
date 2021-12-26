package com.example.kotlinShopApp.ui.home.fragments.searchFragment

import com.example.kotlinShopApp.db.model.SearchModel
import com.example.kotlinShopApp.utils.ServiceStatus

interface SearchStatus {
    fun getStatus(status: ServiceStatus)

    fun searchResult(result:SearchModel)
}
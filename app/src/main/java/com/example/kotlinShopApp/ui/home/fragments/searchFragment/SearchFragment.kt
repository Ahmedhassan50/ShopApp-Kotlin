package com.example.kotlinShopApp.ui.home.fragments.searchFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinShopApp.databinding.FragmentSearchBinding
import com.example.kotlinShopApp.db.model.Product
import com.example.kotlinShopApp.db.model.SearchModel
import com.example.kotlinShopApp.utils.ServiceStatus

class SearchFragment :Fragment(),SearchStatus{
    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchAdapter= SearchAdapter()
        viewModel=ViewModelProvider(requireActivity())[SearchViewModel::class.java]
        viewModel.addContext(this)
        binding.searchRv.layoutManager=LinearLayoutManager(requireContext())
        binding.searchRv.adapter=searchAdapter

        binding.searchEditText.addTextChangedListener {
            viewModel.search(binding.searchEditText.text.toString())


        }


        }

    override fun getStatus(status: ServiceStatus) {
        when(status){
            ServiceStatus.LOADING->binding.searchProgressIndicator.visibility=View.VISIBLE
            ServiceStatus.DONE->binding.searchProgressIndicator.visibility=View.INVISIBLE
            ServiceStatus.ERROR->{}
        }

    }

    override fun searchResult(result: SearchModel) {
        searchAdapter.setList(result.searchData.data as MutableList<Product>)
    }

}

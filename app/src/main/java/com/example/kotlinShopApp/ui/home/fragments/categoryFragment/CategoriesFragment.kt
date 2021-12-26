package com.example.kotlinShopApp.ui.home.fragments.categoryFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinShopApp.R
import com.example.kotlinShopApp.databinding.FragmentCategoriesBinding

class CategoriesFragment:Fragment() {

    private lateinit var binding:FragmentCategoriesBinding
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var viewModel: CategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentCategoriesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryAdapter= CategoryAdapter()
        viewModel=ViewModelProvider(requireActivity())[CategoryViewModel::class.java]
        binding.categoryRV.layoutManager=LinearLayoutManager(requireContext())
        binding.categoryRV.adapter=categoryAdapter
        binding.categoryRV.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))

        viewModel.getCategories()
        binding.categoryProgressBar.visibility=View.VISIBLE
        viewModel.categoryData.observe(viewLifecycleOwner){
            if (it!=null){
                categoryAdapter.setList(it.categoriesData.data)
                binding.categoryProgressBar.visibility=View.GONE
            }
            viewModel.clear()
        }

    }




}
package com.example.kotlinShopApp.ui.home.fragments.homeFragment

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.kotlinShopApp.databinding.FragmentHomeBinding
import com.example.kotlinShopApp.db.model.BannerItem
import com.example.kotlinShopApp.db.model.Product
import com.example.kotlinShopApp.ui.home.fragments.favoriteFragment.FavoriteViewModel

class HomeFragment:Fragment() {

    lateinit var binding: FragmentHomeBinding
    private val handler = Handler()
    lateinit var bannerAdapter: BannerAdapter
    lateinit var viewModel: HomeViewModel
    lateinit var productAdapter: ProductAdapter
    lateinit var favoriteModel: FavoriteViewModel
    lateinit var categoryHomeAdapter:CategoriesHomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding=FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteModel=ViewModelProvider(requireActivity())[FavoriteViewModel::class.java]
        bannerAdapter=BannerAdapter()
        productAdapter= ProductAdapter(::addToFavorite)
        categoryHomeAdapter= CategoriesHomeAdapter()
        viewModel=ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        //productRv
        binding.homeRV.layoutManager=GridLayoutManager(requireActivity(),2)
        binding.homeRV.isNestedScrollingEnabled=false
        binding.homeRV.adapter=productAdapter

        //categoriesRv
        binding.categoryHomeRV.layoutManager=LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
        binding.categoryHomeRV.adapter=categoryHomeAdapter

        viewModel.getHomeData()
        binding.homeProgressBar.visibility=View.VISIBLE
        viewModel.homeData.observe(viewLifecycleOwner){
            if (it!=null){
                bannerAdapter.setList(it.data.banners as ArrayList<BannerItem>)
                productAdapter.setList(it.data.products as ArrayList<Product>)
                categoryHomeAdapter.setList(viewModel.categoriesList)
                binding.homeProgressBar.visibility=View.GONE
                binding.newProductTv.visibility=View.VISIBLE
                binding.categoryHomeTV.visibility=View.VISIBLE
            }
            viewModel.clear()
        }


        val adapter=bannerAdapter
        binding.bannerViewer.adapter = adapter
        binding.bannerViewer.clipChildren=false
        binding.bannerViewer.clipToPadding=false
        binding.bannerViewer.getChildAt(0).overScrollMode=RecyclerView.OVER_SCROLL_NEVER
        binding.bannerViewer.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(bannerRun)
                handler.postDelayed(bannerRun, 3000)

            }

        })

    }
     private  fun addToFavorite(productId:Int){

        favoriteModel.addToFavorites(productId)
     }


    val bannerRun: Runnable = Runnable {

        if (binding.bannerViewer.currentItem == bannerAdapter.itemCount-1) {
            binding.bannerViewer.currentItem = 0
        } else {
            binding.bannerViewer.currentItem = binding.bannerViewer.currentItem + 1
        }

    }
    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(bannerRun)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(bannerRun, 3000)
    }

}
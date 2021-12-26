package com.example.kotlinShopApp.ui.home.fragments.favoriteFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinShopApp.R
import com.example.kotlinShopApp.databinding.FragmentFavoritesBinding
import com.example.kotlinShopApp.db.model.Favorite

class FavoritesFragment:Fragment() {

    private lateinit var binding:FragmentFavoritesBinding
    private lateinit var favoritesAdapter: FavoritesAdapter
    private lateinit var viewModel:FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentFavoritesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoritesAdapter=FavoritesAdapter(::addToFavorite)
        viewModel=ViewModelProvider(requireActivity())[FavoriteViewModel::class.java]
        binding.favoritesRV.layoutManager=LinearLayoutManager(requireContext())
        binding.favoritesRV.adapter=favoritesAdapter
        binding.favoritesRV.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        viewModel.getFavorites()
        binding.favoriteProgressBar.visibility=View.VISIBLE

        viewModel.favoriteData.observe(viewLifecycleOwner){
            if (it!=null){
                if (it.favoriteData.data.isEmpty()){
                    binding.favoritesRV.visibility=View.GONE
                    binding.noFavorites.visibility=View.VISIBLE
                    binding.favoriteProgressBar.visibility=View.GONE
                }else{
                    favoritesAdapter.setList(it.favoriteData.data as MutableList<Favorite>)
                    binding.favoriteProgressBar.visibility=View.GONE
                }
            }
            viewModel.clear()
        }



    }


    private fun addToFavorite(id:Int){
        viewModel.addToFavorites(id)
    }
}
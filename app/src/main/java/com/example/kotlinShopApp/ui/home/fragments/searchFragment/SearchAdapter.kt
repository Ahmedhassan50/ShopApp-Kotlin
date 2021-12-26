package com.example.kotlinShopApp.ui.home.fragments.searchFragment

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinShopApp.R
import com.example.kotlinShopApp.db.model.Favorite
import com.example.kotlinShopApp.db.model.Product


class SearchAdapter :RecyclerView.Adapter<SearchAdapter.SearchViewHolder>(){


    private var searchList:MutableList<Product> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(searchList:MutableList<Product>){
        this.searchList=searchList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.SearchViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val view:View=inflater.inflate(R.layout.favorite_item,parent,false)

        return SearchViewHolder(view)
    }


    override fun onBindViewHolder(holder: SearchAdapter.SearchViewHolder, position: Int) {
        val searchItem=searchList[position]
        holder.bind(searchItem)

    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    inner class SearchViewHolder(view: View):RecyclerView.ViewHolder(view){

        private val searchName: TextView =view.findViewById(R.id.favoriteProductName)
        private val searchProductImage: ImageView =view.findViewById(R.id.favoriteImage)
        private val searchOLdPrice: TextView =view.findViewById(R.id.productOldPrice)
        private val searchPrice: TextView =view.findViewById(R.id.productPrice)
        val searchIcon: CheckBox =view.findViewById(R.id.favoriteIcon)

        fun bind(searchItem: Product){
            searchName.text=searchItem.name
            Glide.with(searchProductImage.rootView)
                .load(searchItem.productImage)
                .into(searchProductImage)

            searchPrice.text=searchItem.price.toString()
            if(searchItem.discount!=0){
                searchOLdPrice.visibility=View.VISIBLE
                searchOLdPrice.text=searchItem.oldPrice.toString()
                searchOLdPrice.paintFlags=searchOLdPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }


        }

    }
}
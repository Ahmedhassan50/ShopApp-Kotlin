package com.example.kotlinShopApp.ui.home.fragments.favoriteFragment

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

class FavoritesAdapter(private val addToFavorite:(productId:Int) -> Unit ) :RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>(){

    private var favoritesList:MutableList<Favorite> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(favoritesList:MutableList<Favorite>){
        this.favoritesList=favoritesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view:View=inflater.inflate(R.layout.favorite_item,parent,false)

        return FavoritesViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val favorite=favoritesList[position]
        holder.bind(favorite)
        holder.favoriteIcon.setOnClickListener {
           favoritesList.removeAt(position)
            notifyDataSetChanged()
            addToFavorite(favorite.product.id)
        }
    }

    override fun getItemCount(): Int {
        return favoritesList.size
    }

    inner class FavoritesViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val favoriteName:TextView=view.findViewById(R.id.favoriteProductName)
        private val favoriteProductImage:ImageView=view.findViewById(R.id.favoriteImage)
        private val productOLdPrice:TextView=view.findViewById(R.id.productOldPrice)
        private val productPrice:TextView=view.findViewById(R.id.productPrice)
        val favoriteIcon:CheckBox=view.findViewById(R.id.favoriteIcon)

     fun bind(favorite: Favorite){
         favoriteName.text=favorite.product.name
         Glide.with(favoriteProductImage.rootView)
             .load(favorite.product.productImage)
             .into(favoriteProductImage)

         productPrice.text=favorite.product.price.toString()
         if(favorite.product.discount!=0){
             productOLdPrice.visibility=View.VISIBLE
             productOLdPrice.text=favorite.product.oldPrice.toString()
             productOLdPrice.paintFlags=productOLdPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
         }
         favoriteIcon.isChecked=true

     }
    }
}
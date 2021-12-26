package com.example.kotlinShopApp.ui.home.fragments.homeFragment

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinShopApp.R
import com.example.kotlinShopApp.db.model.Product
import com.example.kotlinShopApp.ui.home.fragments.favoriteFragment.FavoriteViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProductAdapter(private val addToFavorite:(productId:Int) -> Unit ) :RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

    private var productList:ArrayList<Product> = ArrayList()

  @SuppressLint("NotifyDataSetChanged")
  fun setList(productList: ArrayList<Product>){
      this.productList=productList
      notifyDataSetChanged()
  }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val view:View=inflater.inflate(R.layout.product_item,parent,false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product=productList[position]

        holder.bind(product)
        holder.favoriteIcon.setOnClickListener {
            addToFavorite(product.id)
        }
    }

    override fun getItemCount(): Int {
       return productList.size
    }





    inner class ProductViewHolder(view:View):RecyclerView.ViewHolder(view){
      private val productImage:ImageView=view.findViewById(R.id.productImage)
      private val productName:TextView=view.findViewById(R.id.productName)
      private val productOLdPrice:TextView=view.findViewById(R.id.productOldPrice)
      private val productPrice:TextView=view.findViewById(R.id.productPrice)
      val favoriteIcon:CheckBox=view.findViewById(R.id.favoriteHomeIcon)


        fun bind(product: Product){
            Glide.with(productImage.rootView)
                .load(product.productImage)
                .into(productImage)
            productName.text=product.name
            productPrice.text=product.price.toString()

            if(product.discount!=0){
                productOLdPrice.visibility=View.VISIBLE
                productOLdPrice.text=product.oldPrice.toString()
                productOLdPrice.paintFlags=productOLdPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
            favoriteIcon.isChecked=product.isFavorite


        }

    }

}
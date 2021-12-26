package com.example.kotlinShopApp.ui.home.fragments.categoryFragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinShopApp.R
import com.example.kotlinShopApp.db.model.Category

class CategoryAdapter :RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){

    private var categoriesList:List<Category> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(categoryList:List<Category>){
        categoriesList =categoryList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
       val inflater=LayoutInflater.from(parent.context)
       val view:View=inflater.inflate(R.layout.category_item,parent,false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category=categoriesList[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    inner class CategoryViewHolder(view: View):RecyclerView.ViewHolder(view){
        private  val categoryImage:ImageView= view.findViewById(R.id.categoryImage)
        private val categoryName:TextView= view.findViewById(R.id.categoryName)
        fun bind(category:Category){
            Glide.with(categoryImage.rootView)
                .load(category.image)
                .into(categoryImage)
            categoryName.text=category.name
        }
    }
}
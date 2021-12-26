package com.example.kotlinShopApp.ui.home.fragments.homeFragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinShopApp.R
import com.example.kotlinShopApp.db.model.BannerItem

class BannerAdapter:RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {
    private var bannerList:ArrayList<BannerItem> =ArrayList()


     @SuppressLint("NotifyDataSetChanged")
     fun setList(bannerList:ArrayList<BannerItem>){
         this.bannerList=bannerList
         notifyDataSetChanged()
     }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view:View=inflater.inflate(R.layout.banner_item,parent,false)
        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val bannerItem=bannerList[position]
        holder.bind(bannerItem)
    }

    override fun getItemCount(): Int {
        return bannerList.size
    }


    inner class BannerViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val bannerImage:ImageView= view.findViewById(R.id.bannerImage)

        fun bind(bannerItem:BannerItem){
            Glide.with(bannerImage.rootView)
                .load(bannerItem.BannerImage)
                .into(bannerImage)

        }
    }

}
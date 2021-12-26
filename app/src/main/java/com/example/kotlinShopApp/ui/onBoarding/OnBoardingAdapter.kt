package com.example.kotlinShopApp.ui.onBoarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinShopApp.R
import com.example.kotlinShopApp.db.model.OnBoardingItem

class OnBoardingAdapter : RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private val onBoardingItems = listOf<OnBoardingItem>(
        OnBoardingItem(R.drawable.onboarding, "OnBoarding Title 1", "OnBoarding Body 1"),
        OnBoardingItem(R.drawable.onboarding, "OnBoarding Title 2", "OnBoarding Body 2") ,
         OnBoardingItem (R.drawable.onboarding, "OnBoarding Title 3", "OnBoarding Body 3")

    )


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.onboarding_item, parent, false)
        return OnBoardingViewHolder(view)

    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        val onBoardingItem =onBoardingItems[position]
        holder.bind(onBoardingItem)
    }

    override fun getItemCount(): Int {
        return onBoardingItems.size
    }


    inner class OnBoardingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val onBoardingImage: ImageView = view.findViewById(R.id.OnBoardingImage)
        private val onBoardingTitle: TextView = view.findViewById(R.id.onBoardingTitle)
        private val onBoardingBody: TextView = view.findViewById(R.id.onBoardingBody)

        fun bind(onBoardingItem: OnBoardingItem) {
            onBoardingImage.setImageResource(onBoardingItem.image)
            onBoardingTitle.text = onBoardingItem.title
            onBoardingBody.text = onBoardingItem.body


        }
    }

}
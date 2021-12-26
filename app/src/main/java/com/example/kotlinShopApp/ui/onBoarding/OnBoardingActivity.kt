package com.example.kotlinShopApp.ui.onBoarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.kotlinShopApp.databinding.ActivityOnBoardingBinding
import com.example.kotlinShopApp.ui.auth.AuthActivity
import com.example.kotlinShopApp.ui.auth.login.LoginFragment

class OnBoardingActivity : AppCompatActivity() {
   private lateinit var binding: ActivityOnBoardingBinding

   private val onBoardingAdapter:OnBoardingAdapter by lazy{
       OnBoardingAdapter()
   }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.OnBoardingViewPager.adapter=onBoardingAdapter
        binding.dotsIndicator.setViewPager2(binding.OnBoardingViewPager)
        binding.floatingActionButton.setOnClickListener{
            if (binding.OnBoardingViewPager.currentItem+1 < onBoardingAdapter.itemCount){
                binding.OnBoardingViewPager.currentItem=binding.OnBoardingViewPager.currentItem+1
            }else{
                val intent = Intent(this, AuthActivity::class.java)
                startActivity(intent)
                finish()
            }

        }

        binding.skip.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}
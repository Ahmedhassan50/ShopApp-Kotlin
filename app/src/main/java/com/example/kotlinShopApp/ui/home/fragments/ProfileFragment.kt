package com.example.kotlinShopApp.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.kotlinShopApp.databinding.FragmentProfileBinding
import com.example.kotlinShopApp.db.model.UserData

class ProfileFragment:Fragment(){
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(binding.profileImage.rootView).load(UserData.user?.image).into(binding.profileImage)
        binding.profileName.setText(UserData.user?.name)
        binding.profileEmail.setText(UserData.user?.email)
        binding.profilerPhone.setText(UserData.user?.phone)


    }

}
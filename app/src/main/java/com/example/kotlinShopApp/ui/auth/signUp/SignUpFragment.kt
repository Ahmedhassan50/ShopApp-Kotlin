package com.example.kotlinShopApp.ui.auth.signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlinShopApp.databinding.SignUpFragmentBinding


class SignUpFragment:Fragment() {
    private  lateinit var binding: SignUpFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= SignUpFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }
}
package com.example.kotlinShopApp.ui.home.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlinShopApp.R
import com.example.kotlinShopApp.databinding.FragmentSettingsBinding
import com.example.kotlinShopApp.db.model.AppLanguage
import com.example.kotlinShopApp.ui.home.HomeActivity
import java.util.*


class SettingsFragment :Fragment(){

    private lateinit var binding:FragmentSettingsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding= FragmentSettingsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when(AppLanguage.local.toString()){
            "ar"-> binding.languageToggle.check(binding.arabic.id)
            "en"-> binding.languageToggle.check(binding.english.id)
        }


        binding.languageToggle.addOnButtonCheckedListener{toggleButtomGrouP,checkId,isChecked->

            if (isChecked){
                when(checkId){
                    R.id.arabic ->changeLanguage("ar")
                    R.id.english->changeLanguage("en")
                }

            }


        }

    }


    private fun changeLanguage(lang:String){
        val locale= Locale(lang)
        Locale.setDefault(locale)
        val config =requireActivity().baseContext.resources.configuration
        config.setLocale(locale)
        requireActivity().createConfigurationContext(config)
        requireActivity().baseContext.resources.updateConfiguration(config,requireActivity().baseContext.resources.displayMetrics)
        AppLanguage.local=locale
        val intent=Intent(requireContext(),HomeActivity::class.java)
        intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }


}
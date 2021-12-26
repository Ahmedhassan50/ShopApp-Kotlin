package com.example.kotlinShopApp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinShopApp.db.model.AppLanguage
import com.example.kotlinShopApp.db.model.UserData
import com.example.kotlinShopApp.db.model.UserModel
import com.example.kotlinShopApp.ui.auth.AuthActivity
import com.example.kotlinShopApp.ui.home.HomeActivity
import com.example.kotlinShopApp.ui.onBoarding.OnBoardingActivity
import com.google.gson.Gson
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val locale=Locale("en")
        Locale.setDefault(locale)
        val config =baseContext.resources.configuration
        config.setLocale(locale)
        createConfigurationContext(config)
        baseContext.resources.updateConfiguration(config,baseContext.resources.displayMetrics)
        AppLanguage.local=locale



        val sharedPref=getSharedPreferences("OnBoarding", MODE_PRIVATE)
        val isFirstTime=sharedPref.getBoolean("firstTime",true)
        if (isFirstTime){
            val editor=sharedPref.edit()
            editor.putBoolean("firstTime",false)
            editor.apply()
            val intent =Intent(this,OnBoardingActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            val sharedPref2=getSharedPreferences("user", MODE_PRIVATE)
            val userData=sharedPref2.getString("userData",null)
            if (userData!=null){
                UserData.user=Gson().fromJson(userData,UserModel::class.java)
                val intent=Intent(this,HomeActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent =Intent(this,AuthActivity::class.java)
                startActivity(intent)
                finish()
            }

        }


    }
}
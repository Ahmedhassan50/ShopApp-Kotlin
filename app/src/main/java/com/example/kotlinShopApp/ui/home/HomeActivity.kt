package com.example.kotlinShopApp.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.bumptech.glide.Glide
import com.example.kotlinShopApp.MainActivity
import com.example.kotlinShopApp.R
import com.example.kotlinShopApp.databinding.ActivityHomeBinding
import com.example.kotlinShopApp.db.model.UserData
import com.example.kotlinShopApp.ui.auth.AuthActivity
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {
    private lateinit var binding:ActivityHomeBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navFragmentHost=supportFragmentManager.findFragmentById(R.id.homeNavHost) as NavHostFragment
         navController=navFragmentHost.navController


        appBarConfiguration= AppBarConfiguration(
            setOf(R.id.homeFragment,
            R.id.categoriesFragment,
            R.id.favoritesFragment,
            R.id.settingsFragment
        ),binding.drawerLayout)
        binding.bottomNavBar.setupWithNavController(navController)
        setSupportActionBar(binding.toolBar)
        binding.navView.itemIconTintList=null
        binding.navView.setupWithNavController(navController)
        setupActionBarWithNavController(navController,appBarConfiguration)


     //set Header Data
     val header=binding.navView.getHeaderView(0)
     val userImage:ImageView=header.findViewById(R.id.drawerImage)
     Glide.with(userImage.rootView).load(UserData.user?.image).into(userImage)
     header.findViewById<TextView>(R.id.drawer_user_name).text=UserData.user?.name
     header.findViewById<TextView>(R.id.drawer_user_email).text=UserData.user?.email




        binding.navView.menu.findItem(R.id.logout).setOnMenuItemClickListener {
            val sharedPref=getSharedPreferences("user", MODE_PRIVATE)
            sharedPref.edit().clear().apply()
            UserData.user=null
            val intent=Intent(this@HomeActivity, AuthActivity::class.java)
            intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

            return@setOnMenuItemClickListener true
        }





    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tool_bar_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController)||super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)||super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if(binding.drawerLayout.isOpen){
            binding.drawerLayout.close()
        }else{
            super.onBackPressed()
        }
    }

}
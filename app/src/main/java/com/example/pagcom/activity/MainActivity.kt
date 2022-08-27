package com.example.pagcom.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.pagcom.R
import com.example.pagcom.databinding.ActivityMainBinding
import com.example.pagcom.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {


    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelMain: MainViewModel by viewModels()
        viewModelMain.firstConfig(this)
        viewModelMain.context = this

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this

        navController = findNavController(R.id.nav_host_fragment)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 123) {
            val viewModelMain: MainViewModel by viewModels()
            val bitmap = data?.extras?.get("data") as Bitmap
            viewModelMain.bitmap.value = bitmap
        } else {
            super.onActivityResult(requestCode, resultCode, data)

        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    fun navToCompanies() = navController.navigate(R.id.stockExchangeFragment)
    fun navToRegistration() = navController.navigate(R.id.registrationFragment)
    fun navToCam() = navController.navigate(R.id.camFragment)
    fun navToGPS() = navController.navigate(R.id.gpsFragment)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_menu_exchange -> {
                navToCompanies()
                return true
            }
            R.id.item_menu_register -> {
                navToRegistration()
                return true
            }
            R.id.item_menu_cam -> {
                navToCam()
                return true
            }
            R.id.item_menu_gps -> {
                navToGPS()
                return true
            }
        }
        return true
    }

    override fun onBackPressed() {}
}









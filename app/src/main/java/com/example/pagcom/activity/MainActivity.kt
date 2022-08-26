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

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this

        val navHostFragment =
            Navigation.findNavController(this, R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == 123) {
            val viewModelMain: MainViewModel by viewModels()
            val bitmap = data?.extras?.get("data") as Bitmap
            viewModelMain.setImage(bitmap)
        }

        super.onActivityResult(requestCode, resultCode, data)
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

// debtorsViewHolder.binding.txtLetter.setBackground(CircleUtil.getRandomCircle(context,position));
//public class CircleUtil {
//    public static Drawable getRandomCircle(Context context, int position){
//        Drawable drawable = context.getResources().getDrawable(R.drawable.circle_red);
//        GradientDrawable shapeDrawable = (GradientDrawable) drawable;
//        int[] colors = context.getResources().getIntArray(R.array.colors);
//
//        int times = times(position);
//        Log.e("TAG","times "+times+" position"+position);
//        shapeDrawable.setColor(colors[position - times*15]);
//        return shapeDrawable;
//    }
//
//    public static int getColor(Context context) {
//        int[] colors = context.getResources().getIntArray(R.array.colors);
//        Random random = new Random();
//        return colors[random.nextInt(colors.length-1)];
//    }
//
//    public static int times(int number) {
//        if (number == 0) {
//            return 0;
//
//        }
//        int i = 0;
//        while (true) {
//
//
//            if (number < 15) {
//                if (i == 0)
//                    return 0;
//                return i;
//            }
//            number = (number - 15);
//            i++;
//
//        }
//    }
//}










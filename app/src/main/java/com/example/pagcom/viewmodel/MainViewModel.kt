package com.example.pagcom.viewmodel

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.pagcom.web.CompanieWebClient
import com.example.pagcom.web.model.CompaniesResponse
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private lateinit var navController: NavController
    private lateinit var context: AppCompatActivity
    var bitmap: MutableLiveData<Bitmap> = MutableLiveData<Bitmap>()
    var regisName: MutableLiveData<String> = MutableLiveData<String>()
    var regisTel: MutableLiveData<String> = MutableLiveData<String>()
    var regisCPF: MutableLiveData<String> = MutableLiveData<String>()

    fun firstConfig(context: AppCompatActivity) {
        this.context = context
        checkPermissions()
    }

    private fun checkPermissions() {
        val checkSelfPermission =
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.INTERNET
            ) == PackageManager.PERMISSION_GRANTED

        if (!checkSelfPermission) {
            ActivityCompat.shouldShowRequestPermissionRationale(
                context,
                Manifest.permission.INTERNET
            )
        }
    }

    fun listCompanies(callback: (List<CompaniesResponse>?) -> Unit) {
        viewModelScope.launch {
            launch {
                val listCompanies = CompanieWebClient().getAll()
                callback.invoke(listCompanies)
                Log.e("MainActivity", "$listCompanies")
            }
        }
    }

    fun capture() {
        val cam_intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        context.startActivityForResult(cam_intent, 123)
    }

    fun save() {
        if (regisName.value.isNullOrBlank() ||
            regisTel.value.isNullOrBlank() ||
            regisCPF.value.isNullOrBlank()
        ) {
            Toast.makeText( context, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
        }else{
            regisName.value = ""
            regisTel.value = ""
            regisCPF.value = ""
            Toast.makeText( context, "Usuário Cadastrado com Sucesso", Toast.LENGTH_SHORT).show()
        }
    }

    fun getLocation() {
        if(Build.MODEL == "A910"){

        }else{

        }
    }

    fun setImage(bitmap: Bitmap) {
        this.bitmap.value = bitmap
    }

}
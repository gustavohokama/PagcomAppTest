package com.example.pagcom.viewmodel

import android.Manifest
import android.Manifest.permission.INTERNET
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Location
import android.location.LocationManager
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
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private lateinit var navController: NavController
    lateinit var context: AppCompatActivity
    val bitmap: MutableLiveData<Bitmap> = MutableLiveData<Bitmap>()
    val regisName: MutableLiveData<String> = MutableLiveData<String>()
    val regisTel: MutableLiveData<String> = MutableLiveData<String>()
    val regisCPF: MutableLiveData<String> = MutableLiveData<String>()
    val location: MutableLiveData<String> = MutableLiveData<String>()


    fun firstConfig(context: AppCompatActivity) {
        this.context = context
        checkPermissions()
    }

    private fun checkPermissions() {
        if (!internetPermission()) {
            ActivityCompat.requestPermissions(
                context,
                arrayOf(Manifest.permission.INTERNET),
                111
            )
        }

        if (!cameraPermission()) {
            ActivityCompat.requestPermissions(
                context,
                arrayOf(Manifest.permission.CAMERA),111
            )
        }

        if (!locationPerminssion()) {
            ActivityCompat.requestPermissions(
                context,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),111
            )
            ActivityCompat.requestPermissions(
                context,
                arrayOf( Manifest.permission.ACCESS_COARSE_LOCATION), 111
            )
        }

    }

    private fun internetPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.INTERNET
        ) == PackageManager.PERMISSION_GRANTED

    }

    private fun cameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun locationPerminssion(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
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
        if (regisName.value.isNullOrEmpty() ||
            regisTel.value.isNullOrEmpty() ||
            regisCPF.value.isNullOrEmpty()
        ) {
            Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
        } else if (regisTel.value?.length != 11) {
            Toast.makeText(context, "Telefone Preenchido Incorretament", Toast.LENGTH_SHORT).show()
        } else if (regisCPF.value?.length != 11) {
            Toast.makeText(context, "CPF Preenchido Incorretament", Toast.LENGTH_SHORT).show()

        } else {
            regisName.value = ""
            regisTel.value = ""
            regisCPF.value = ""
            Toast.makeText(context, "Usuário Cadastrado com Sucesso", Toast.LENGTH_SHORT).show()
        }
    }

    fun configLocation() {
        if (Build.MODEL == "A910") {
            location.value = "Fiz algumas pesquisas, e constatei que nos terminais de pagamento" +
                    "não possui GPS, por isso não é possível acessar a localização via Google." +
                    "Continuando minhas pesquisas, consegui observar que pode haver uma maneira" +
                    "de recuperar a localização via chip, porém necessitaria de estudo bem mais" +
                    "avançado."
        } else {
//            val location =
//                LocationServices.getFusedLocationProviderClient(context).lastLocation
            object : LocationCallback(){
                override fun onLocationResult(locationResult: LocationResult) {
                    if (locationResult != null){
                        location.value = "Sua Localização é $locationResult"
                    }
                }
            }
        }
    }
}
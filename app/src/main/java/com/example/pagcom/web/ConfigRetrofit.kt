package com.example.pagcom.web

import com.example.pagcom.web.service.Companies
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ConfigRetrofit {

    private val retrofitConfig: Retrofit = Retrofit.Builder()
        .baseUrl("https://api-cotacao-b3.labdo.it/api/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val retrofit = retrofitConfig.create(Companies::class.java)

}

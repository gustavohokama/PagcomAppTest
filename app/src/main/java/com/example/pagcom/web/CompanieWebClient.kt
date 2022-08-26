package com.example.pagcom.web

import android.util.Log
import com.example.pagcom.web.model.CompaniesResponse
import com.example.pagcom.web.service.Companies

class CompanieWebClient{

    private val companiesService: Companies = ConfigRetrofit().retrofit

    suspend fun getAll(): List<CompaniesResponse>?{
        return try{
            val companiesResponse = companiesService.getAll()
            companiesResponse.map{
                it
            }
        } catch (e: Exception){
            Log.e("CompanieWebClient", "Error on getAll ", e)
            null
        }
    }

}
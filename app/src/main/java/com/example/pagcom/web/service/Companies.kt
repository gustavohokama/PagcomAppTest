package com.example.pagcom.web.service

import com.example.pagcom.web.model.CompaniesResponse
import retrofit2.http.GET

interface Companies {

    @GET("carteira")
    suspend fun getAll(): List<CompaniesResponse>
}
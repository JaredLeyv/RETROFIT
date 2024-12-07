package com.example.retro.Retrofit

import com.example.retro.models.Retrofit.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://10.0.2.2:5031/" // Base URL for your API

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Converts JSON responses
            .build()
            .create(ApiService::class.java) // Creates the API service instance
    }
}

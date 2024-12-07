package com.example.retro.models.Retrofit

import com.example.retro.models.Product
import retrofit2.http.*

interface ApiService {
    @GET("api/Products")
    suspend fun getProducts(): List<Product>

    @POST("api/Products")
    suspend fun addProduct(@Body product: Product): Product

    @PUT("api/Products/{id}")
    suspend fun updateProduct(@Path("id") id: Int, @Body product: Product): Product

    @DELETE("api/Products/{id}")
    suspend fun deleteProduct(@Path("id") id: Int)
}

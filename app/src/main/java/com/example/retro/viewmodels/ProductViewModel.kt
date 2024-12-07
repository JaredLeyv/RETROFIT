package com.example.retro.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retro.Retrofit.RetrofitInstance
import com.example.retro.models.Product
import kotlinx.coroutines.launch
import kotlin.random.Random

class ProductViewModel : ViewModel() {
    var products = mutableStateOf<List<Product>>(emptyList())

    fun generateUniqueId(): Int {
        val existingIds = products.value.map { it.id }.toSet()
        var newId: Int
        do {
            newId = Random.nextInt(1, 966)
        } while (newId in existingIds)
        return newId
    }

    fun fetchProducts() {
        viewModelScope.launch {
            try {
                products.value = RetrofitInstance.api.getProducts()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addProduct(product: Product) {
        viewModelScope.launch {
            try {
                RetrofitInstance.api.addProduct(product)
                fetchProducts()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateProduct(product: Product) {
        viewModelScope.launch {
            try {
                RetrofitInstance.api.updateProduct(product.id, product)
                fetchProducts()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteProduct(id: Int) {
        viewModelScope.launch {
            try {
                RetrofitInstance.api.deleteProduct(id)
                products.value = products.value.filter { it.id != id }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

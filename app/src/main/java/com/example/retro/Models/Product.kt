package com.example.retro.models

data class Product(
    val id: Int,           // Unique identifier for the product
    val name: String,      // Product name
    val description: String, // Detailed description
    val category: String,  // Product category
    val price: Double,     // Unit price
    val quantity: Int      // Inventory quantity
)

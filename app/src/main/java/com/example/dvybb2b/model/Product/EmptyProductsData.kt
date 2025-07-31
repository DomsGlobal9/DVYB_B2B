package com.example.dvybb2b.model.Product

data class EmptyProductsData(
    val id: String,
    val name: String,
    val price: Double,
    val imageUrl: String = ""
)

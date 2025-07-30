package com.example.dvybb2b.model.Product


data class RecentlyAddedProduct(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val quantity: Int,
    val sold: Int,
    val gender: String,
    val category: String
)
data class Category(val name: String, val count: Int)


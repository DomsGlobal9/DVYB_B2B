package com.example.dvybb2b.model.Product


data class ProductCategory(
    val name: String,
    val count: Int
)

data class CategoryWise(
    val name: String,
    val count: Int,
    val imageRes: Int
)

data class ProductPerformance(
    val name: String,
    val imageRes: Int
)

data class ProductOverview(
    val totalCount: Int,
    val lastUpdated: String,
)

data class Product(
    val id: Int,
    val title: String,
    val price: String,
    val imageAssetPath: String,
    val reviews: String
)

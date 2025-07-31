package com.example.dvybb2b.model.Product

import androidx.annotation.DrawableRes

data class ProductDress(
    val ProductId : String,
    val name: String,
    @DrawableRes val imageRes: Int
)
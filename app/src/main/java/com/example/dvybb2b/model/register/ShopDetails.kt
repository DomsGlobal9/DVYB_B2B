package com.example.dvybb2b.model.register

data class ShopDetails(
    val shopName: String = "",
    val address: String = "",
    val state: String = "",
    val city: String = "",
    val pincode: String = "",
    val gstin: String = "",
    val pan: String = "",
    val noGst: Boolean = false
)

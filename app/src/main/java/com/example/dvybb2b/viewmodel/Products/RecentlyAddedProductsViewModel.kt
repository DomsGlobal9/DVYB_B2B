package com.example.dvybb2b.viewmodel.Products

import com.example.dvybb2b.model.Product.RecentlyAddedProduct
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel


class RecentlyAddedProductsViewModel : ViewModel() {
    private val allProducts = listOf(
        RecentlyAddedProduct(1, "Black Floral Top", "https://via.placeholder.com/150", 240, 320, "Women", "Stitched"),
        RecentlyAddedProduct(2, "Party Wear Kurthi", "https://via.placeholder.com/150", 240, 320, "Women", "Un-Stitched"),
        RecentlyAddedProduct(3, "White Kurthi Set", "https://via.placeholder.com/150", 150, 450, "Women", "Un-Stitched"),
        RecentlyAddedProduct(4, "Polo T-shirt", "https://via.placeholder.com/150", 240, 320, "Men", "Stitched"),
        RecentlyAddedProduct(5, "Denim Shirt", "https://via.placeholder.com/150", 240, 320, "Men", "Un-Stitched"),
        RecentlyAddedProduct(6, "Red Lehenga", "https://via.placeholder.com/150", 300, 180, "Kids", "Accessories"),
        RecentlyAddedProduct(7, "Ethnic Lehenga Set", "https://via.placeholder.com/150", 90, 210, "Kids", "Un-Stitched"),
        RecentlyAddedProduct(8, "Black Floral Top", "https://via.placeholder.com/150", 240, 320, "Women", "Stitched"),
        RecentlyAddedProduct(9, "Party Wear Kurthi", "https://via.placeholder.com/150", 240, 320, "Women", "Un-Stitched"),
        RecentlyAddedProduct(10, "White Kurthi Set", "https://via.placeholder.com/150", 150, 450, "Women", "Un-Stitched"),
        RecentlyAddedProduct(11, "Polo T-shirt", "https://via.placeholder.com/150", 240, 320, "Men", "Stitched"),
        RecentlyAddedProduct(12, "Denim Shirt", "https://via.placeholder.com/150", 240, 320, "Men", "Un-Stitched"),
        RecentlyAddedProduct(13, "Red Lehenga", "https://via.placeholder.com/150", 300, 180, "Kids", "Accessories"),
        RecentlyAddedProduct(14, "Ethnic Lehenga Set", "https://via.placeholder.com/150", 90, 210, "Kids", "Un-Stitched"),
        RecentlyAddedProduct(15, "Black Floral Top", "https://via.placeholder.com/150", 240, 320, "Women", "Stitched"),
        RecentlyAddedProduct(16, "Party Wear Kurthi", "https://via.placeholder.com/150", 240, 320, "Women", "Un-Stitched"),
        RecentlyAddedProduct(17, "White Kurthi Set", "https://via.placeholder.com/150", 150, 450, "Women", "Un-Stitched"),
        RecentlyAddedProduct(18, "Polo T-shirt", "https://via.placeholder.com/150", 240, 320, "Men", "Stitched"),
        RecentlyAddedProduct(19, "Denim Shirt", "https://via.placeholder.com/150", 240, 320, "Men", "Un-Stitched"),
        RecentlyAddedProduct(20, "Red Lehenga", "https://via.placeholder.com/150", 300, 180, "Kids", "Accessories"),
        RecentlyAddedProduct(21, "Ethnic Lehenga Set", "https://via.placeholder.com/150", 90, 210, "Kids", "Un-Stitched")
    )

    var selectedGender by mutableStateOf("Women")
    var selectedCategory by mutableStateOf("All")
    var searchQuery by mutableStateOf("")

    val categories: List<String> = listOf("All", "Stitched", "Un-Stitched", "Accessories")

    val filteredProducts: List<RecentlyAddedProduct>
        get() = allProducts.filter {
            (selectedCategory == "All" || it.category == selectedCategory) &&
                    it.gender == selectedGender &&
                    it.title.contains(searchQuery, ignoreCase = true)
        }

    fun updateGender(gender: String) {
        selectedGender = gender
    }

    fun updateSearch(query: String) {
        searchQuery = query
    }

    fun updateCategory(cat: String) {
        selectedCategory = cat
    }
}

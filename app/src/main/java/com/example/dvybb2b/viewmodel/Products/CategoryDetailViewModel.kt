package com.example.dvybb2b.viewmodel.Products



import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.dvybb2b.model.Product.Product
import com.example.dvybb2b.model.Product.productsMap


class CategoryDetailViewModel : ViewModel() {

    var searchQuery by mutableStateOf("")
        private set

    var filteredProducts by mutableStateOf<List<Product>>(emptyList())
        private set

    private var allProducts: List<Product> = emptyList()

    fun initialize(gender: String, categoryName: String) {
        allProducts = productsMap[gender]?.get(categoryName) ?: emptyList()
        filteredProducts = allProducts
    }

    fun updateSearch(query: String) {
        searchQuery = query
        filteredProducts = if (query.isBlank()) {
            allProducts
        } else {
            allProducts.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
    }
}

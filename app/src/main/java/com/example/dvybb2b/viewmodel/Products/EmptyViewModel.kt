package com.example.dvybb2b.viewmodel.Products


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductViewModel : ViewModel() {
    private val _productList = MutableStateFlow<List<String>>(emptyList())
    val productList = _productList.asStateFlow()
}
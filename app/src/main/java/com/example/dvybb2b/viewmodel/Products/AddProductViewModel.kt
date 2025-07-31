package com.example.dvybb2b.viewmodel.Products


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class ProductState(
    val category: String = "",
    val productType: String = "",
    val dressType: String = "",
    val material: String = "",
    val design: String = "",
    val price: String = ""
)

class AddProductViewModel : ViewModel() {

    private val _product = MutableStateFlow(ProductState())
    val product: StateFlow<ProductState> = _product

    private val _currentTab = MutableStateFlow(0)
    val currentTab: StateFlow<Int> = _currentTab

    fun updateCategory(category: String) {
        _product.update { it.copy(category = category) }
    }

    fun updateProductType(type: String) {
        _product.update { it.copy(productType = type) }
    }

    fun updateDressType(dress: String) {
        _product.update { it.copy(dressType = dress) }
    }

    fun updateMaterial(material: String) {
        _product.update { it.copy(material = material) }
    }

    fun updateDesign(design: String) {
        _product.update { it.copy(design = design) }
    }

    fun updatePrice(price: String) {
        _product.update { it.copy(price = price) }
    }

    fun setTab(index: Int) {
        _currentTab.value = index
    }

    fun nextTab() {
        _currentTab.update { it + 1 }
    }

    fun previousTab() {
        _currentTab.update { it - 1 }
    }
}

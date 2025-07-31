package com.example.myapplication.ui.theme.AddOneProduct.ViewModel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SizeAndPriceViewModel : ViewModel() {
    var selectedSizes by mutableStateOf(setOf<String>())
    var price by mutableStateOf("")
    var selectedColors by mutableStateOf(mutableListOf<String>())
    var units by mutableStateOf(mutableMapOf<String, MutableMap<String, String>>())

    val allSizes = listOf("S", "M", "L", "XL", "XXL")
    val allColors = listOf("Red", "Pastel", "Bright", "Dark")

    fun toggleSize(size: String) {
        selectedSizes = if (selectedSizes.contains(size)) {
            selectedSizes - size
        } else {
            selectedSizes + size
        }
    }

    fun addColor(color: String) {
        if (!selectedColors.contains(color)) {
            selectedColors.add(color)
            units[color] = mutableMapOf()
        }
    }

    fun setUnit(size: String, color: String, value: String) {
        units.getOrPut(color) { mutableMapOf() }[size] = value
    }

    fun onPriceChange(newPrice: String) {
        price = newPrice
    }
}

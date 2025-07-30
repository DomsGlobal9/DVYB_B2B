package com.example.dvybb2b.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.example.dvybb2b.R
import com.example.dvybb2b.model.Product.ProductDress


class DressPreviewViewModel : ViewModel() {

    // Read-only dress list (static, not MutableState since it doesn’t change)
    val dressList = listOf(
        ProductDress("Dress 1", R.drawable.preview),
        ProductDress("Dress 2", R.drawable.preview4),
        ProductDress("Dress 3", R.drawable.preview1),
        ProductDress("Dress 4", R.drawable.preview4)
    )

    // Selected dress state
    private val _selectedDress = mutableStateOf(dressList[0])
    val selectedDress: State<ProductDress> = _selectedDress

    // Function to update selected dress
    fun selectDress(dress: ProductDress) {
        _selectedDress.value = dress
    }
}

package com.example.dvybb2b.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.example.dvybb2b.R
import com.example.dvybb2b.model.Product.ProductDress


class DressPreviewViewModel : ViewModel() {

    // Read-only dress list (static, not MutableState since it doesn’t change)
    val dressList = listOf(
        ProductDress("0","Dress 1", R.drawable.preview),
        ProductDress("1","Dress 2", R.drawable.preview4),
        ProductDress("3","Dress 3", R.drawable.preview1),
        ProductDress("4","Dress 4", R.drawable.preview4)
    )

    // Selected dress state
    private val _selectedDress = mutableStateOf<ProductDress?>(null)
    val selectedDress: State<ProductDress?> = _selectedDress


    // Function to update selected dress
    fun selectDress(dress: ProductDress) {
        _selectedDress.value = dress
    }

    fun loadDressById(productId: String) {
        // Example: Fetch from repository or local mock list
        val dress = dressList.find { it.ProductId == productId } // Or Firestore, Room, etc.
        _selectedDress.value = dress
    }

}

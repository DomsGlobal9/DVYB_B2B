package com.example.dvybb2b.viewmodel.Products


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.dvybb2b.R
import com.example.dvybb2b.model.Product.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue



class ProductsViewModel : ViewModel() {

    val overview = ProductOverview(
        totalCount = 120,
        lastUpdated = "28/07/2025"
    )

    val recentlyAdded = listOf(
        Category("Women", 120),
        Category("Men", 120),
        Category("Kids", 120)
    )

    val topProducts = listOf(
        ProductPerformance("Kids top and bottom", imageRes = R.drawable.kids_combo),
        ProductPerformance("Hoodies", imageRes = R.drawable.hoodies),
        ProductPerformance("Kids bottom wear", imageRes = R.drawable.kids_bottom)
    )

    val categoryWise = listOf(
        CategoryWise("Sarees", 17,imageRes = R.drawable.kids_combo),
        CategoryWise("Kids Wear", 13,imageRes = R.drawable.kids_combo),
        CategoryWise("Mens Wear", 12,imageRes = R.drawable.kids_combo),
        CategoryWise("Lehenga", 12,imageRes = R.drawable.kids_combo)
    )

    // Add this property to hold selected gender
    var selectedGender by mutableStateOf("Women")
        private set

    fun updateGender(gender: String) {
        selectedGender = gender
    }

    // You can optionally filter products too:
    val filteredRecent: List<Category>
        get() = recentlyAdded.filter { it.name == selectedGender }


}

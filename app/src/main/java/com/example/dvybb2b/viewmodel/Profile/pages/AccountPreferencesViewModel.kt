package com.example.dvybb2b.viewmodel.Profile.pages

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel



class AccountPreferencesViewModel : ViewModel() {

    val categories = listOf("Women", "Men", "Kids", "Accessories")
    val types = listOf("Type 1", "Type 2", "Type 3")
    val dressTypes = listOf("Dress Type 1", "Dress Type 2", "Dress Type 3")
    val materials = listOf("Material Type 1", "Material Type 2", "Material Type 3")

    var isEditable by mutableStateOf(false)
    var isInitialSelection by mutableStateOf(true)

    var selectedCategory by mutableStateOf<String?>(null)
    var selectedTypes by mutableStateOf<List<String>>(emptyList())
    var selectedDresses by mutableStateOf<List<String>>(emptyList())
    var selectedMaterials by mutableStateOf<List<String>>(emptyList())

    var showCategoryOptions by mutableStateOf(false)
    var showTypeOptions by mutableStateOf(false)
    var showDressOptions by mutableStateOf(false)
    var showMaterialOptions by mutableStateOf(false)

    fun saveInitialPreferences() {
        if (selectedCategory != null &&
            selectedTypes.isNotEmpty() &&
            selectedDresses.isNotEmpty() &&
            selectedMaterials.isNotEmpty()
        ) {
            isInitialSelection = false
        }
    }

    fun toggleEditMode() {
        isEditable = !isEditable
    }

    fun saveChanges() {
        isEditable = false
    }
}

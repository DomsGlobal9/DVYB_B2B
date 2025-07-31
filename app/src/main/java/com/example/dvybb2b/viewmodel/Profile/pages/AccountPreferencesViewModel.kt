package com.example.dvyb.ui.theme.home.profile.pages

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class AccountPreferencesState(
    val isEditable: Boolean = false,
    val isInitialSelection: Boolean = true,
    val selectedCategory: String? = null,
    val selectedTypes: List<String> = emptyList(),
    val selectedDresses: List<String> = emptyList(),
    val selectedMaterials: List<String> = emptyList(),
    val showCategoryOptions: Boolean = false,
    val showTypeOptions: Boolean = false,
    val showDressOptions: Boolean = false,
    val showMaterialOptions: Boolean = false
)

class AccountPreferencesViewModel : ViewModel() {

    private val _state = MutableStateFlow(AccountPreferencesState())
    val state: StateFlow<AccountPreferencesState> = _state

    val categories = listOf("Women", "Men", "Kids", "Accessories")
    val types = listOf("Type 1", "Type 2", "Type 3")
    val dressTypes = listOf("Dress Type 1", "Dress Type 2", "Dress Type 3")
    val materials = listOf("Material Type 1", "Material Type 2", "Material Type 3")

    fun toggleEditable() {
        _state.update { it.copy(isEditable = !it.isEditable) }
    }

    fun setEditable(value: Boolean) {
        _state.update { it.copy(isEditable = value) }
    }

    fun setCategory(category: String) {
        _state.update { it.copy(selectedCategory = category, showCategoryOptions = false) }
        checkInitialSelectionComplete()
    }

    fun setTypes(types: List<String>) {
        _state.update { it.copy(selectedTypes = types) }
        checkInitialSelectionComplete()
    }

    fun setDresses(dresses: List<String>) {
        _state.update { it.copy(selectedDresses = dresses) }
        checkInitialSelectionComplete()
    }

    fun setMaterials(materials: List<String>) {
        _state.update { it.copy(selectedMaterials = materials) }
        checkInitialSelectionComplete()
    }

    fun toggleDropdown(type: String) {
        _state.update {
            when (type) {
                "category" -> it.copy(showCategoryOptions = !it.showCategoryOptions)
                "type" -> it.copy(showTypeOptions = !it.showTypeOptions)
                "dress" -> it.copy(showDressOptions = !it.showDressOptions)
                "material" -> it.copy(showMaterialOptions = !it.showMaterialOptions)
                else -> it
            }
        }
    }

    fun setDropdown(type: String, expanded: Boolean) {
        _state.update {
            when (type) {
                "category" -> it.copy(showCategoryOptions = expanded)
                "type" -> it.copy(showTypeOptions = expanded)
                "dress" -> it.copy(showDressOptions = expanded)
                "material" -> it.copy(showMaterialOptions = expanded)
                else -> it
            }
        }
    }

    private fun checkInitialSelectionComplete() {
        _state.update {
            if (it.selectedCategory != null &&
                it.selectedTypes.isNotEmpty() &&
                it.selectedDresses.isNotEmpty() &&
                it.selectedMaterials.isNotEmpty()
            ) {
                it.copy(isInitialSelection = false)
            } else it
        }
    }
}
package com.example.dvybb2b.viewmodel.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dvybb2b.model.register.ShopDetails
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterShopDetailsViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()

    private val _shopDetails = MutableStateFlow(ShopDetails())
    val shopDetails: StateFlow<ShopDetails> = _shopDetails

    fun updateField(field: (ShopDetails) -> ShopDetails) {
        _shopDetails.value = field(_shopDetails.value)
    }

    fun saveShopDetails(onSuccess: () -> Unit, onError: (String) -> Unit) {
        val shop = _shopDetails.value
        if (shop.shopName.isBlank() || shop.address.isBlank() || shop.state.isBlank()) {
            onError("Please fill all mandatory fields")
            return
        }

        viewModelScope.launch {
            db.collection("shops")
                .add(shop)
                .addOnSuccessListener { onSuccess() }
                .addOnFailureListener { e -> onError(e.message ?: "Error saving data") }
        }
    }
}

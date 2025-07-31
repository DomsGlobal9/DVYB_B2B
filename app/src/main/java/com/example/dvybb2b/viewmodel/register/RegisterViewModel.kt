
package com.example.dvybb2b.viewmodel.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()


    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private val _phone = MutableStateFlow("")
    val phone: StateFlow<String> = _phone

    private val _state = MutableStateFlow("")
    val state: StateFlow<String> = _state

    private val _city = MutableStateFlow("")
    val city: StateFlow<String> = _city

    private val _address = MutableStateFlow("")
    val address: StateFlow<String> = _address

    fun onNameChange(newName: String) {
        _name.value = newName
    }

    fun onPhoneChange(newPhone: String) {
        _phone.value = newPhone
    }

    fun onStateChange(newState: String) {
        _state.value = newState
    }

    fun onCityChange(newCity: String) {
        _city.value = newCity
    }

    fun onAddressChange(newAddress: String) {
        _address.value = newAddress
    }

    fun saveVendorToFirebase(onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val data = mapOf(
                "name" to name.value,
                "phone" to phone.value,
                "state" to state.value,
                "city" to city.value,
                "address" to address.value,
                "status" to "pending"
            )

            db.collection("vendor_registrations")
                .add(data)
                .addOnSuccessListener { onResult(true) }
                .addOnFailureListener { onResult(false) }
        }
    }
    val shopName = MutableStateFlow("")
    val shopAddress = MutableStateFlow("")
    val shopState = MutableStateFlow("")
    val pincode = MutableStateFlow("")
    val gst = MutableStateFlow("")
    val pan = MutableStateFlow("")

    fun onShopNameChange(value: String) { shopName.value = value }
    fun onShopAddressChange(value: String) { shopAddress.value = value }
    fun onShopStateChange(value: String) { shopState.value = value }
    fun onPincodeChange(value: String) { pincode.value = value }
    fun onGstChange(value: String) { gst.value = value }
    fun onPanChange(value: String) { pan.value = value }

}

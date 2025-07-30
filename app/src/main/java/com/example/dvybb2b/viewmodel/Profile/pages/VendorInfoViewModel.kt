package com.example.dvybb2b.viewmodel.Profile.pages

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.dvybb2b.model.Profile.VendorInfo

class VendorInfoViewModel : ViewModel() {
    var vendorInfo = mutableStateOf(VendorInfo())
        private set

    fun updateVendorName(name: String) {
        vendorInfo.value = vendorInfo.value.copy(name = name)
    }

    fun updateVendorPhone(phone: String) {
        vendorInfo.value = vendorInfo.value.copy(phone = phone)
    }

    fun updateVendorEmail(email: String) {
        vendorInfo.value = vendorInfo.value.copy(email = email)
    }

    fun updateVendorPassword(password: String) {
        vendorInfo.value = vendorInfo.value.copy(password = password)
    }

    fun updateVendorAddress(address: String) {
        vendorInfo.value = vendorInfo.value.copy(address = address)
    }
}
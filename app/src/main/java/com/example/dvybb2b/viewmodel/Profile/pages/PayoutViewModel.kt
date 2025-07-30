package com.example.dvybb2b.viewmodel.Profile.pages

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PayoutViewModel : ViewModel() {

    private val _bankName = MutableStateFlow("")
    val bankName: StateFlow<String> = _bankName

    private val _accountNumber = MutableStateFlow("")
    val accountNumber: StateFlow<String> = _accountNumber

    private val _ifscCode = MutableStateFlow("")
    val ifscCode: StateFlow<String> = _ifscCode

    fun updateBankName(newValue: String) {
        _bankName.value = newValue
    }

    fun updateAccountNumber(newValue: String) {
        _accountNumber.value = newValue
    }

    fun updateIfscCode(newValue: String) {
        _ifscCode.value = newValue
    }
}
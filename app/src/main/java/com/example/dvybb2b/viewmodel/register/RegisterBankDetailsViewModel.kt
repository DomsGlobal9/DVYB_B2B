package com.example.dvybb2b.viewmodel.register

import androidx.lifecycle.ViewModel
import com.example.dvybb2b.model.register.BankDetailsState
import com.example.dvybb2b.model.register.ValidationErrors
import com.example.dvybb2b.model.register.validateBankDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RegisterBankDetailsViewModel : ViewModel() {

    private val _bankDetailsState = MutableStateFlow(BankDetailsState())
    val bankDetailsState: StateFlow<BankDetailsState> = _bankDetailsState

    private val _validationErrors = MutableStateFlow(ValidationErrors())
    val validationErrors: StateFlow<ValidationErrors> = _validationErrors

    private val _isBankDropdownExpanded = MutableStateFlow(false)
    val isBankDropdownExpanded: StateFlow<Boolean> = _isBankDropdownExpanded

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _bankList = MutableStateFlow<List<String>>(emptyList())
    val bankList: StateFlow<List<String>> = _bankList

    fun getBankList() {
        _bankList.value = listOf("SBI", "HDFC", "ICICI", "Axis Bank", "Kotak")
    }

    fun onBankDropdownExpandedChange(expanded: Boolean) {
        _isBankDropdownExpanded.value = expanded
    }

    fun onFieldChange(update: BankDetailsState.() -> BankDetailsState) {
        _bankDetailsState.update { it.update() }
    }

    fun validate(): Boolean {
        val state = _bankDetailsState.value
        val errors = validateBankDetails(state)
        _validationErrors.value = errors
        return !errors.hasErrors
    }

    fun startLoading() {
        _isLoading.value = true
    }

    fun stopLoading() {
        _isLoading.value = false
    }

    fun clearValidationForField(field: String) {
        val currentErrors = _validationErrors.value
        _validationErrors.value = when (field) {
            "bank" -> currentErrors.copy(bankError = null)
            "branch" -> currentErrors.copy(branchError = null)
            "accountHolder" -> currentErrors.copy(accountHolderError = null)
            "accountNumber" -> currentErrors.copy(accountNumberError = null)
            "confirmAccount" -> currentErrors.copy(confirmAccountNumberError = null)
            "ifsc" -> currentErrors.copy(ifscError = null)
            else -> currentErrors
        }
    }
}

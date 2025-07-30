package com.example.dvybb2b.model.register

fun validateBankDetails(state: BankDetailsState): ValidationErrors {
    return ValidationErrors(
        bankError = if (state.selectedBank.isBlank()) "Please select a bank" else null,
        branchError = if (state.branchName.isBlank()) "Branch name is required" else null,
        accountHolderError = if (state.accountHolderName.isBlank()) "Account holder name is required" else null,
        accountNumberError = if (state.accountNumber.isBlank()) "Account number is required" else null,
        confirmAccountNumberError = if (state.confirmAccountNumber != state.accountNumber)
            "Account numbers do not match" else null,
        ifscError = if (state.ifscCode.isBlank()) "IFSC code is required" else null
    )
}

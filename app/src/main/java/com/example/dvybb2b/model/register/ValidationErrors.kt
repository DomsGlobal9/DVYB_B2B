package com.example.dvybb2b.model.register

data class ValidationErrors(
    val bankError: String? = null,
    val branchError: String? = null,
    val accountHolderError: String? = null,
    val accountNumberError: String? = null,
    val confirmAccountNumberError: String? = null,
    val ifscError: String? = null
) {
    val hasErrors: Boolean
        get() = listOf(
            bankError, branchError, accountHolderError,
            accountNumberError, confirmAccountNumberError, ifscError
        ).any { it != null }
}

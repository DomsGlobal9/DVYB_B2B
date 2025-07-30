package com.example.dvybb2b.model.register

data class BankDetailsState(
    val selectedBank: String = "",
    val branchName: String = "",
    val accountHolderName: String = "",
    val accountNumber: String = "",
    val confirmAccountNumber: String = "",
    val ifscCode: String = ""
)


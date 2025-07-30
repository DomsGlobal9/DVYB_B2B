package com.example.dvybb2b.model.register

data class PersonalDetailsState(
    val name: String = "",
    val phone: String = "",
    val state: String = "",
    val city: String = "",
    val address: String = "",
    val error: String? = null
)


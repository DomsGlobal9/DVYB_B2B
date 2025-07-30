package com.example.dvybb2b.viewmodel.register


import androidx.lifecycle.ViewModel
import com.example.dvybb2b.model.register.PersonalDetailsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RegisterPersonalDetailsViewModel : ViewModel() {
    private val _state = MutableStateFlow(PersonalDetailsState())
    val state: StateFlow<PersonalDetailsState> = _state

    fun updateName(name: String) = _state.update { it.copy(name = name) }
    fun updatePhone(phone: String) = _state.update { it.copy(phone = phone) }
    fun updateState(state: String) = _state.update { it.copy(state = state) }
    fun updateCity(city: String) = _state.update { it.copy(city = city) }
    fun updateAddress(address: String) = _state.update { it.copy(address = address) }

    fun validate(): Any {
        val current = _state.value
        return when {
            current.name.isBlank() -> setError("Name is required")
            current.phone.length != 10 -> setError("Enter valid 10-digit number")
            current.state.isBlank() -> setError("Select a state")
            current.city.isBlank() -> setError("City is required")
            current.address.isBlank() -> setError("Address is required")
            else -> {
                clearError()
                true
            }
        }
    }

    private fun setError(msg: String) = _state.update { it.copy(error = msg) }
    private fun clearError() = _state.update { it.copy(error = null) }
}
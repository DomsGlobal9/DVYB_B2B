package com.example.dvybb2b.viewmodel.register


import android.app.Activity
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dvybb2b.Data.repository.FirebaseRepository
import com.example.dvybb2b.utils.UiState
import com.google.firebase.firestore.FieldValue
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: FirebaseRepository
) : ViewModel() {

    var verificationId by mutableStateOf("")
    var tempDocId = UUID.randomUUID().toString()

    val uiState = mutableStateOf<UiState>(UiState.Idle)

    fun sendOtp(phone: String, activity: Activity) {
        repository.sendOtp(
            phone, activity,
            onCodeSent = { id, _ -> verificationId = id },
            onVerificationCompleted = { credential ->
                // auto complete case
            },
            onVerificationFailed = { e ->
                // show error
            }
        )
    }

    fun verifyOtpAndFinalize(otp: String, onSuccess: () -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            uiState.value = UiState.Loading
            val verified = repository.verifyOtp(verificationId, otp)
            if (verified) {
                val updated = repository.updateVerificationStatus(tempDocId)
                if (updated) onSuccess() else onError()
            } else {
                onError()
            }
            uiState.value = UiState.Idle
        }
    }

    fun saveTempData(data: Map<String, Any>, onSaved: () -> Unit) {
        viewModelScope.launch {
            val payload = data.toMutableMap()
            payload["status"] = "pending"
            payload["timestamp"] = FieldValue.serverTimestamp()
            val saved = repository.storeTemporaryVendorData(tempDocId, payload)
            if (saved) onSaved()
        }
    }
}

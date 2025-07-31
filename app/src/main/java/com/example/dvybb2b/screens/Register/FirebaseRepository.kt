package com.example.dvybb2b.Data.repository

import android.app.Activity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.UUID
import java.util.concurrent.TimeUnit


class FirebaseRepository {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    // Send OTP
    fun sendOtp(
        phone: String,
        activity: Activity,
        onCodeSent: (String, PhoneAuthProvider.ForceResendingToken) -> Unit,
        onVerificationCompleted: (PhoneAuthCredential) -> Unit,
        onVerificationFailed: (FirebaseException) -> Unit
    ) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phone)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    onVerificationCompleted(credential)
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    onVerificationFailed(e)
                }

                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    onCodeSent(verificationId, token)
                }
            }).build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    suspend fun verifyOtp(verificationId: String, otp: String): Boolean {
        return try {
            val credential = PhoneAuthProvider.getCredential(verificationId, otp)
            val result = auth.signInWithCredential(credential).await()
            result.user != null
        } catch (e: Exception) {
            false
        }
    }

    suspend fun storeTemporaryVendorData(tempId: String, data: Map<String, Any>): Boolean {
        return try {
            db.collection("vendor_registrations").document(tempId).set(data).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun updateVerificationStatus(tempId: String): Boolean {
        return try {
            db.collection("vendor_registrations")
                .document(tempId)
                .update("status", "verified")
                .await()
            true
        } catch (e: Exception) {
            false
        }
    }
}

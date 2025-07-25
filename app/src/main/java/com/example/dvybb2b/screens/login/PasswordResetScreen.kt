package com.example.dvybb2b.ui.theme.components.screens.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dvybb2b.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.sp


//@Composable
//fun PasswordResetScreen(navController: NavController) {
//    var phone by remember { mutableStateOf("") }
//    var showOtp by remember { mutableStateOf(false) }
//    var otpValues by remember { mutableStateOf(List(4) { "" }) }
//    var showPasswordFields by remember { mutableStateOf(false) }
//    var password by remember { mutableStateOf("") }
//    var confirmPassword by remember { mutableStateOf("") }
//    var success by remember { mutableStateOf(false) }
//    var errorMessage by remember { mutableStateOf<String?>(null) }
//
//    Box(Modifier.fillMaxSize()) {
//        // Background image
//        Image(
//            painter = painterResource(R.drawable.fpbg),
//            contentDescription = null,
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.Crop
//        )
//
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .align(Alignment.BottomCenter)
//                .background(Color.White, RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
//                .padding(horizontal = 24.dp, vertical = 32.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            // Title
//            Text(
//                "Forgot Password",
//                style = MaterialTheme.typography.titleLarge,
//                color = Color(0xFF0D47A1)
//            )
//            Spacer(Modifier.height(12.dp))
//
//            if (!success) {
//                // Phone input
//                if (!showOtp) {
//                    OutlinedTextField(
//                        value = phone,
//                        onValueChange = {
//                            if (it.length <= 10 && it.all { c -> c.isDigit() })
//                                phone = it
//                        },
//                        label = { Text("Enter Your 10 Digit Mobile Number") },
//                        leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null) },
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
//                        singleLine = true,
//                        modifier = Modifier.fillMaxWidth(),
//                        isError = errorMessage != null
//                    )
//                    if (errorMessage != null) {
//                        Text(errorMessage!!, color = Color.Red, fontSize = MaterialTheme.typography.bodySmall.fontSize)
//                    }
//                    Spacer(Modifier.height(16.dp))
//                    Button(
//                        onClick = {
//                            errorMessage = if (phone.length < 10) {
//                                "Please enter a valid 10 digit number"
//                            } else {
//                                showOtp = true
//                                errorMessage = null
//                                null
//                            }
//                        },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(50.dp),
//                        shape = RoundedCornerShape(10.dp)
//                    ) {
//                        Text("Get OTP", fontSize = 16.sp)
//                    }
//                }
//                // OTP step
//                else {
//                    Text("Enter the 4-digit OTP sent to your phone", color = Color.Gray)
//                    Spacer(Modifier.height(16.dp))
//                    Row(
//                        horizontalArrangement = Arrangement.spacedBy(12.dp),
//                        modifier = Modifier.align(Alignment.CenterHorizontally)
//                    ) {
//                        otpValues.forEachIndexed { idx, value ->
//                            OutlinedTextField(
//                                value = value,
//                                onValueChange = {
//                                    if (it.length <= 1 && (it.isEmpty() || it.all { c -> c.isDigit() })) {
//                                        otpValues = otpValues.toMutableList().also { list -> list[idx] = it }
//                                    }
//                                },
//                                singleLine = true,
//                                modifier = Modifier
//                                    .width(54.dp)
//                                    .height(60.dp),
//                                textStyle = LocalTextStyle.current.copy(
//                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
//                                    fontSize = 24.sp
//                                ),
//                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
//                            )
//                        }
//                    }
//                    Spacer(Modifier.height(10.dp))
//                    TextButton(onClick = {
//                        // Resend logic
//                        otpValues = List(4) { "" }
//                    }) {
//                        Text("Resend OTP", color = MaterialTheme.colorScheme.primary)
//                    }
//                    Spacer(Modifier.height(12.dp))
//                    Button(
//                        onClick = {
//                            if (otpValues.all { it.length == 1 }) {
//                                showPasswordFields = true
//                                errorMessage = null
//                            } else {
//                                errorMessage = "Enter all 4 OTP digits"
//                            }
//                        },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(50.dp),
//                        shape = RoundedCornerShape(10.dp)
//                    ) {
//                        Text("Verify OTP", fontSize = 16.sp)
//                    }
//                }
//                // New Password step
//                if (showPasswordFields) {
//                    Spacer(Modifier.height(18.dp))
//                    OutlinedTextField(
//                        value = password,
//                        onValueChange = { password = it },
//                        label = { Text("Enter New Password") },
//                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
//                        visualTransformation = PasswordVisualTransformation(),
//                        singleLine = true,
//                        modifier = Modifier.fillMaxWidth()
//                    )
//                    Spacer(Modifier.height(10.dp))
//                    OutlinedTextField(
//                        value = confirmPassword,
//                        onValueChange = { confirmPassword = it },
//                        label = { Text("Re-enter Password") },
//                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
//                        visualTransformation = PasswordVisualTransformation(),
//                        singleLine = true,
//                        modifier = Modifier.fillMaxWidth()
//                    )
//                    if (errorMessage != null && password.isNotEmpty()) {
//                        Text(errorMessage!!, color = Color.Red, fontSize = MaterialTheme.typography.bodySmall.fontSize)
//                    }
//                    Spacer(Modifier.height(16.dp))
//                    Button(
//                        onClick = {
//                            errorMessage = when {
//                                password.length < 6 -> "Password must be at least 6 characters"
//                                password != confirmPassword -> "Passwords do not match"
//                                else -> null
//                            }
//                            if (errorMessage == null) {
//                                success = true
//                            }
//                        },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(50.dp),
//                        shape = RoundedCornerShape(10.dp)
//                    ) {
//                        Text("Submit", fontSize = 16.sp)
//                    }
//                }
//            }
//            // Success message
//            else {
//                Spacer(Modifier.height(30.dp))
//                Icon(
//                    Icons.Default.CheckCircle,
//                    contentDescription = null,
//                    tint = Color(0xFF43A047),
//                    modifier = Modifier.size(72.dp)
//                )
//                Spacer(Modifier.height(10.dp))
//                Text(
//                    "Password reset successfully!",
//                    color = Color(0xFF388E3C),
//                    style = MaterialTheme.typography.titleMedium
//                )
//                Spacer(Modifier.height(18.dp))
//                Button(
//                    onClick = { navController.navigate("login") },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(50.dp),
//                    shape = RoundedCornerShape(10.dp)
//                ) {
//                    Text("Go to Login", fontSize = 16.sp)
//                }
//            }
//        }
//    }
//}


@Composable
fun PasswordResetScreen(navController: NavController) {
    var phone by remember { mutableStateOf("") }
    var showOtp by remember { mutableStateOf(false) }
    var otpValues by remember { mutableStateOf(List(4) { "" }) }
    var focusedOtpIndex by remember { mutableStateOf(0) }
    var showPasswordFields by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var success by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // FocusRequester for OTP fields
    val otpFocusRequesters = List(4) { remember { FocusRequester() } }

    Box(Modifier.fillMaxSize()) {
        // Background image (change drawable as needed)
        Image(
            painter = painterResource(R.drawable.fpbg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(Color.White, RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Logo (change drawable as needed)
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(58.dp)
            )
            Spacer(Modifier.height(25.dp))

            // Title
            Text(
                "Forgot Password",
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFF0D47A1)
            )
            Spacer(Modifier.height(12.dp))

            if (!success) {
                // PHONE ENTRY
                if (!showOtp && !showPasswordFields) {
                    OutlinedTextField(
                        value = phone,
                        onValueChange = {
                            if (it.length <= 10 && it.all { c -> c.isDigit() }) phone = it
                        },
                        label = { Text("Enter 10 digit mobile number") },
                        leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        isError = errorMessage != null
                    )
                    if (errorMessage != null) {
                        Text(errorMessage!!, color = Color.Red)
                    }
                    Spacer(Modifier.height(18.dp))
                    Button(
                        onClick = {
                            errorMessage = if (phone.length < 10) {
                                "Please enter a valid 10 digit number"
                            } else {
                                showOtp = true
                                errorMessage = null
                                focusedOtpIndex = 0
                                null
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("GET OTP", fontSize = 16.sp)
                    }
                    Spacer(Modifier.height(18.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = "Need help",
                            color = Color(0xFF2196F3),
                            style = MaterialTheme.typography.bodySmall
                        )
                        Icon(
                            imageVector = Icons.Default.HelpOutline,
                            contentDescription = "Need help",
                            tint = Color(0xFF2196F3),
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(Modifier.width(20.dp))
                    }
                }


                // OTP ENTRY
                if (showOtp && !showPasswordFields) {
                    Text("Enter the 4-digit OTP sent to your phone", color = Color.Gray)
                    Spacer(Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        otpValues.forEachIndexed { idx, value ->
                            OutlinedTextField(
                                value = value,
                                onValueChange = { input ->
                                    if (input.length > 1) return@OutlinedTextField // Only 1 char max

                                    // Only accept digits or empty
                                    if (input.isEmpty() || input.all { it.isDigit() }) {
                                        val newOtp = otpValues.toMutableList()
                                        newOtp[idx] = input
                                        otpValues = newOtp

                                        if (input.isNotEmpty() && idx < 3) {
                                            focusedOtpIndex = idx + 1
                                        }
                                        if (input.isEmpty() && idx > 0) {
                                            focusedOtpIndex = idx - 1
                                        }
                                    }
                                },
                                singleLine = true,
                                modifier = Modifier
                                    .width(54.dp)
                                    .height(60.dp)
                                    .focusRequester(otpFocusRequesters[idx])
                                    .onFocusChanged {
                                        if (it.isFocused) focusedOtpIndex = idx
                                    },
                                textStyle = LocalTextStyle.current.copy(
                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                    fontSize = 24.sp
                                ),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                        }
                    }
                    // KEEP OTP FOCUSED
                    LaunchedEffect(focusedOtpIndex) {
                        otpFocusRequesters[focusedOtpIndex].requestFocus()
                    }
                    Spacer(Modifier.height(8.dp))
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextButton(onClick = {
                            otpValues = List(4) { "" }
                            focusedOtpIndex = 0
                        }) {
                            Text("Resend OTP", color = MaterialTheme.colorScheme.primary)
                        }
                        // You could add a "Change Number" button here if needed
                    }
                    Spacer(Modifier.height(8.dp))
                    if (errorMessage != null) {
                        Text(errorMessage!!, color = Color.Red)
                        Spacer(Modifier.height(8.dp))
                    }
                    Button(
                        onClick = {
                            if (otpValues.all { it.length == 1 }) {
                                showPasswordFields = true
                                showOtp = false // Hide OTP fields!
                                errorMessage = null
                            } else {
                                errorMessage = "Enter all 4 OTP digits"
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Verify OTP", fontSize = 16.sp)
                    }
                }

                // PASSWORD ENTRY ONLY (after OTP is verified)
                if (showPasswordFields) {
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Enter New Password") },
                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                        visualTransformation = PasswordVisualTransformation(),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(10.dp))
                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        label = { Text("Re-enter Password") },
                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                        visualTransformation = PasswordVisualTransformation(),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (errorMessage != null && password.isNotEmpty()) {
                        Text(errorMessage!!, color = Color.Red)
                        Spacer(Modifier.height(4.dp))
                    }
                    Spacer(Modifier.height(16.dp))
                    Button(
                        onClick = {
                            errorMessage = when {
                                password.length < 6 -> "Password must be at least 6 characters"
                                password != confirmPassword -> "Passwords do not match"
                                else -> null
                            }
                            if (errorMessage == null) {
                                success = true
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Submit", fontSize = 16.sp)
                    }
                }
            }
            // SUCCESS STATE
            if (success) {
                Spacer(Modifier.height(30.dp))
                Icon(
                    Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = Color(0xFF43A047),
                    modifier = Modifier.size(72.dp)
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    "Password reset successfully!",
                    color = Color(0xFF388E3C),
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.height(18.dp))
                Button(
                    onClick = { navController.navigate("login") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Go to Login", fontSize = 16.sp)
                }
            }
        }
    }
}

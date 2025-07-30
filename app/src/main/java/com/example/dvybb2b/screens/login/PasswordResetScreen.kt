package com.example.dvyb.ui.theme.components.screens.login


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.material.icons.outlined.CheckCircle
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration


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
    var isFocused by remember { mutableStateOf(false) }
    // FocusRequester for OTP fields
    val otpFocusRequesters = List(4) { remember { FocusRequester() } }

    Box(Modifier.fillMaxSize()) {
        // Background image (change drawable as needed)
        Image(
            painter = painterResource(id = R.drawable.fpbg),
            contentDescription = "Background Image",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .align(Alignment.TopCenter),
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
                modifier = Modifier.size(50.dp)
            )
            Spacer(Modifier.height(25.dp))

            // Title
            Text(
                "Forgot Password",
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFF1D4C5C)
            )
            Spacer(Modifier.height(18.dp))

            if (!success) {
                // PHONE ENTRY
                if (!showOtp && !showPasswordFields) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(if (isFocused) Color.White else Color(0xFFEAF2F5))
                            .drawBehind {
                                val strokeWidth = 2.dp.toPx()
                                val y = size.height - strokeWidth / 2
                                drawLine(
                                    color = Color(0xFF2196F3), // blue color bottom line
                                    start = Offset(0f, y),
                                    end = Offset(size.width, y),
                                    strokeWidth = strokeWidth
                                )
                            }
                    )
                    {
                        OutlinedTextField(
                            value = phone,
                            onValueChange = { newText ->
                                val filtered = newText.filter { it.isDigit() }.take(10)
                                phone = filtered
                            },
                            label = { Text("Enter 10 digit mobile number", color = if (isFocused) Color(0xFF8A9193) else Color.Gray ) },
                            // Conditionally show leading icon only when not focused
                            leadingIcon = if (!isFocused) {
                                {
                                    Icon(Icons.Default.Phone, contentDescription = null)
                                }
                            } else null,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()

                                .onFocusChanged { isFocused = it.isFocused },

                            isError = errorMessage != null,
                            textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                            colors = OutlinedTextFieldDefaults.colors(

                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                                disabledBorderColor = Color.Transparent,
                                errorBorderColor = Color.Transparent,
                                errorCursorColor = Color.Red
                            )
                        )
                    }


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
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4F9EBB) // B2B-500 color
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Get OTP", fontSize = 16.sp,color = Color.White)
                    }
                    Spacer(Modifier.height(18.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Spacer(Modifier.width(6.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Need help",
                                fontSize = 18.sp,
                                color = Color.Black
                            )
                            Box(
                                modifier = Modifier
                                    .size(22.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFF729BA6)), // matching the blue-gray background
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = " \uFF1F",
                                    color = Color.White,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Spacer(Modifier.height(60.dp))
                    }
                }


                // OTP ENTRY
//                if (showOtp && !showPasswordFields) {
//                    Text("Enter the 4-digit OTP sent to your phone", color = Color.Gray)
//                    Spacer(Modifier.height(16.dp))
//                    Row(
//                        horizontalArrangement = Arrangement.spacedBy(12.dp),
//                        modifier = Modifier.align(Alignment.CenterHorizontally)
//                    ) {
//                        otpValues.forEachIndexed { idx, value ->
//                            OutlinedTextField(
//                                value = value,
//                                onValueChange = { input ->
//                                    if (input.length > 1) return@OutlinedTextField // Only 1 char max
//
//                                    // Only accept digits or empty
//                                    if (input.isEmpty() || input.all { it.isDigit() }) {
//                                        val newOtp = otpValues.toMutableList()
//                                        newOtp[idx] = input
//                                        otpValues = newOtp
//
//                                        if (input.isNotEmpty() && idx < 3) {
//                                            focusedOtpIndex = idx + 1
//                                        }
//                                        if (input.isEmpty() && idx > 0) {
//                                            focusedOtpIndex = idx - 1
//                                        }
//                                    }
//                                },
//                                singleLine = true,
//                                modifier = Modifier
//                                    .width(54.dp)
//                                    .height(60.dp)
//                                    .focusRequester(otpFocusRequesters[idx])
//                                    .onFocusChanged {
//                                        if (it.isFocused) focusedOtpIndex = idx
//                                    },
//                                textStyle = LocalTextStyle.current.copy(
//                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
//                                    fontSize = 24.sp
//                                ),
//                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
//                            )
//                        }
//                    }
//                    // KEEP OTP FOCUSED
//                    LaunchedEffect(focusedOtpIndex) {
//                        otpFocusRequesters[focusedOtpIndex].requestFocus()
//                    }
//                    Spacer(Modifier.height(8.dp))
//                    Row(
//                        Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.SpaceBetween
//                    )
//                    {
//                        TextButton(onClick = {
//                            otpValues = List(4) { "" }
//                            focusedOtpIndex = 0
//                        }) {
//                            Text("Resend OTP", color = MaterialTheme.colorScheme.primary)
//                        }
//
//                    }
//                    Spacer(Modifier.height(8.dp))
//                    if (errorMessage != null) {
//                        Text(errorMessage!!, color = Color.Red)
//                        Spacer(Modifier.height(8.dp))
//                    }
//                    Button(
//                        onClick = {
//                            if (otpValues.all { it.length == 1 }) {
//                                showPasswordFields = true
//                                showOtp = false // Hide OTP fields!
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

//                if (showOtp && !showPasswordFields) {
//                    Spacer(Modifier.height(20.dp))
//                    Text("Enter OTP", color = Color(0xFF1D4C5C))
//                    Spacer(Modifier.height(20.dp))
//                    Row(
//                        horizontalArrangement = Arrangement.spacedBy(12.dp),
//                        modifier = Modifier.align(Alignment.CenterHorizontally)
//                    ) {
//                        otpValues.forEachIndexed { idx, value ->
//                            OutlinedTextField(
//                                value = value,
//                                onValueChange = { input ->
//                                    if (input.length > 1) return@OutlinedTextField // Only 1 char max
//
//                                    // Only accept digits or empty
//                                    if (input.isEmpty() || input.all { it.isDigit() }) {
//                                        val newOtp = otpValues.toMutableList()
//                                        newOtp[idx] = input
//                                        otpValues = newOtp
//
//                                        if (input.isNotEmpty() && idx < 3) {
//                                            focusedOtpIndex = idx + 1
//                                        }
//                                        if (input.isEmpty() && idx > 0) {
//                                            focusedOtpIndex = idx - 1
//                                        }
//                                    }
//                                },
//                                singleLine = true,
//                                modifier = Modifier
//                                    .width(54.dp)
//                                    .height(60.dp)
//                                    .focusRequester(otpFocusRequesters[idx])
//                                    .onFocusChanged {
//                                        if (it.isFocused) focusedOtpIndex = idx
//                                    },
//                                textStyle = LocalTextStyle.current.copy(
//                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
//                                    fontSize = 24.sp,
//                                    color= Color.Black
//                                ),
//                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
//                            )
//                        }
//                    }
//                    // KEEP OTP FOCUSED
//                    LaunchedEffect(focusedOtpIndex) {
//                        otpFocusRequesters[focusedOtpIndex].requestFocus()
//                    }
//                    Spacer(Modifier.height(8.dp))
//                    if (errorMessage != null) {
//                        Text(errorMessage!!, color = Color.Red)
//                        Spacer(Modifier.height(8.dp))
//                    }
//                    Button(
//                        onClick = {
//                            if (otpValues.all { it.length == 1 }) {
//                                showPasswordFields = true
//                                showOtp = false // Hide OTP fields!
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
//
//                    // Centered underlined "Resend OTP" text here
//                    Spacer(Modifier.height(12.dp))
//                    Box(
//                        modifier = Modifier.fillMaxWidth(),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        TextButton(
//                            onClick = {
//                                otpValues = List(4) { "" }
//                                focusedOtpIndex = 0
//                            },
//                            contentPadding = PaddingValues(0.dp)
//                        ) {
//                            Text(
//                                "Resend OTP",
//                                color = MaterialTheme.colorScheme.primary,
//                                textDecoration = TextDecoration.Underline
//                            )
//                        }
//                    }
//                    Spacer(Modifier.height(24.dp))
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.spacedBy(8.dp)
//                    ) {
//                        Text(
//                            text = "Need help",
//                            fontSize = 18.sp,
//                            color = Color.Black
//                        )
//                        Box(
//                            modifier = Modifier
//                                .size(22.dp)
//                                .clip(CircleShape)
//                                .background(Color(0xFF729BA6)), // matching the blue-gray background
//                            contentAlignment = Alignment.Center
//                        ) {
//                            Text(
//                                text = " \uFF1F",
//                                color = Color.White,
//                                fontSize = 13.sp,
//                                fontWeight = FontWeight.Bold
//                            )
//                        }
//                    }
//                }

                if (showOtp && !showPasswordFields) {
                    Spacer(Modifier.height(20.dp))
                    Text(
                        "Enter OTP",
                        color = Color(0xFF1D4C5C),
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp), // ✅ Correct padding usage
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom,
                    ) {
                        otpValues.forEachIndexed { idx, value ->
                            // Box to hold each OTP input field
                            Box(
                                modifier = Modifier
                                    .width(56.dp)
                                    .height(60.dp)
                                    .border(
                                        BorderStroke(width = 1.dp, color = Color.Transparent),
                                        shape = RectangleShape
                                    )
                            ) {
                                BasicTextField(
                                    value = value,
                                    onValueChange = { input ->
                                        if (input.length <= 1 && (input.isEmpty() || input.all { it.isDigit() })) {
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
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    singleLine = true,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .focusRequester(otpFocusRequesters[idx])
                                        .onFocusChanged {
                                            if (it.isFocused) focusedOtpIndex = idx
                                        },
                                    cursorBrush = SolidColor(Color.Black),
                                    textStyle = LocalTextStyle.current.copy(
                                        fontSize = 32.sp,
                                        textAlign = TextAlign.Center,
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    visualTransformation = StarVisualTransformation() // show stars instead of digits
                                )
                                // Bottom black line
                                Spacer(
                                    modifier = Modifier
                                        .align(Alignment.BottomCenter)
                                        .fillMaxWidth()
                                        .height(1.dp)
                                        .background(Color.Black)
                                )
                            }
                        }
                    }

                    // KEEP OTP FOCUSED
                    LaunchedEffect(focusedOtpIndex) {
                        otpFocusRequesters[focusedOtpIndex].requestFocus()
                    }

                    Spacer(Modifier.height(5.dp))
                    if (errorMessage != null) {
                        Text(errorMessage!!, color = Color.Red)
                        Spacer(Modifier.height(5.dp))
                    }
                    Spacer(Modifier.height(15.dp))
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
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4F9EBB) // B2B-500 fallback color
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Submit", fontSize = 16.sp, color = Color.White)
                    }

                    Spacer(Modifier.height(12.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        TextButton(
                            onClick = {
                                otpValues = List(4) { "" }
                                focusedOtpIndex = 0
                            },
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(
                                "Resend OTP",
                                color = Color(0xFF4F9EBB),
//                                color = MaterialTheme.colorScheme.primary,
                                textDecoration = TextDecoration.Underline
                            )
                        }
                    }

                    Spacer(Modifier.height(12.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Need help",
                            fontSize = 18.sp,
                            color = Color.Black
                        )
                        Box(
                            modifier = Modifier
                                .size(22.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF729BA6)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = " \uFF1F",
                                color = Color.White,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Spacer(Modifier.height(20.dp))
                }



                // PASSWORD ENTRY ONLY (after OTP is verified)
//                if (showPasswordFields) {
//                    Spacer(Modifier.height(8.dp))
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
//                        Text(errorMessage!!, color = Color.Red)
//                        Spacer(Modifier.height(4.dp))
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
//                        colors = ButtonDefaults.buttonColors(
//                            containerColor = Color(0xFF4F9EBB) // B2B-500 fallback color
//                        ),
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(50.dp),
//                        shape = RoundedCornerShape(10.dp)
//                    ) {
//                        Text("Submit", fontSize = 16.sp, color = Color.White)
//                    }
//                    Spacer(Modifier.height(22.dp))
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.spacedBy(8.dp)
//                    ) {
//                        Text(
//                            text = "Need help",
//                            fontSize = 18.sp,
//                            color = Color.Black
//                        )
//                        Box(
//                            modifier = Modifier
//                                .size(22.dp)
//                                .clip(CircleShape)
//                                .background(Color(0xFF729BA6)), // matching the blue-gray background
//                            contentAlignment = Alignment.Center
//                        ) {
//                            Text(
//                                text = " \uFF1F",
//                                color = Color.White,
//                                fontSize = 13.sp,
//                                fontWeight = FontWeight.Bold
//                            )
//                        }
//                    }
//                }

                if (showPasswordFields) {
                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "Enter new password",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1F4A57),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .align(Alignment.Start) // ensures left alignment in Column
                    )
                    Spacer(Modifier.height(14.dp))

                    var passwordVisible by remember { mutableStateOf(true) } // Password visible by default
                    var confirmPasswordVisible by remember { mutableStateOf(false) }

                    // Password Field
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = {
                            Text("Password", color = if (password.isNotEmpty()) Color.Black else Color.Gray)
                        },
                        trailingIcon = {
                            val icon = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(imageVector = icon, contentDescription = "Toggle Password Visibility",tint = Color(0xFF4F9EBB))
                            }
                        },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .drawBehind {
                                val strokeWidth = 1.dp.toPx()
                                val y = size.height - strokeWidth / 2
                                drawLine(
                                    color = Color.Gray,
                                    start = Offset(0f, y),
                                    end = Offset(size.width, y),
                                    strokeWidth = strokeWidth
                                )
                            },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            disabledBorderColor = Color.Transparent,
                            errorBorderColor = Color.Transparent,
                            cursorColor = Color.Black
                        ),
                        textStyle = TextStyle(color = Color.Black,fontSize = 16.sp,textAlign = TextAlign.Start)
                    )

                    Spacer(Modifier.height(10.dp))

                    // Confirm Password Field
                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        label = {
                            Text("Re enter Password", color = if (confirmPassword.isNotEmpty()) Color.Black else Color.Gray)
                        },
                        trailingIcon = {
                            val icon = if (confirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                            IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                                Icon(imageVector = icon, contentDescription = "Toggle Password Visibility",tint = Color(0xFF4F9EBB) )
                            }
                        },
                        visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 2.dp)
                            .drawBehind {
                                val strokeWidth = 1.dp.toPx()
                                val y = size.height - strokeWidth / 2
                                drawLine(
                                    color = Color.Gray,
                                    start = Offset(0f, y),
                                    end = Offset(size.width, y),
                                    strokeWidth = strokeWidth
                                )
                            },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            disabledBorderColor = Color.Transparent,
                            errorBorderColor = Color.Transparent,
                            cursorColor = Color.Black
                        ),
                        textStyle = TextStyle(color = Color.Black, fontSize = 16.sp,textAlign = TextAlign.Start)
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
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4F9EBB)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Submit", fontSize = 16.sp, color = Color.White)
                    }

                    Spacer(Modifier.height(22.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Need help",
                            fontSize = 18.sp,
                            color = Color.Black
                        )
                        Box(
                            modifier = Modifier
                                .size(22.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF729BA6)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "\uFF1F",
                                color = Color.White,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

            }
            // SUCCESS STATE

            if (success) {
                AnimatedVisibility(
                    visible = success,
                    enter = slideInVertically(initialOffsetY = { fullHeight -> fullHeight }) + fadeIn(),
                    exit = fadeOut()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 40.dp)
                            .background(Color.White, shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                            .padding(top = 32.dp, bottom = 48.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Icon(
                            imageVector = Icons.Outlined.CheckCircle,
                            contentDescription = "Success",
                            tint = Color(0xFF43A047), // green color
                            modifier = Modifier.size(72.dp)
                        )

                        Spacer(modifier = Modifier.height(28.dp))

                        // ✅ Blue Text Message
                        Text(
                            text = "You have created a new password",
                            fontSize = 16.sp,
                            color = Color(0xFF1D4C5C), // Blue text
                            textAlign = TextAlign.Center,

                            )

                        Spacer(modifier = Modifier.height(22.dp))

                        TextButton(
                            onClick = { navController.navigate("login") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(
                                "Login",
                                fontSize = 16.sp,
                                color = Color(0xFF2196F3) // Optional: set a link-like blue color
                            )
                        }
                    }
                }
            }


        }
    }
}

// Define this inside your file (above or below your Composable), or as a nested class/object

class StarVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val transformed = AnnotatedString("*".repeat(text.text.length))
        return TransformedText(
            transformed,
            OffsetMapping.Identity
        )
    }
}

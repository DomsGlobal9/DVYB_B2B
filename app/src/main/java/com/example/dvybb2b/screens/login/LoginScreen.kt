package com.example.dvyb.ui.theme.components.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dvybb2b.R
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration


@Composable
fun LoginScreen(navController: NavHostController) {
    var mobileNumber by remember { mutableStateOf(TextFieldValue("")) }
    var isFocused by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by remember { mutableStateOf(false) }
    var isPasswordFocused by remember { mutableStateOf(false) }

    var loggedIn by remember { mutableStateOf(false) }
    var userName by remember { mutableStateOf("") } // For demo, hardcoded on login success
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Background image
//        Image(
//            painter = painterResource(id = R.drawable.loginbg),
//            contentDescription = "Background Image",
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.Crop
//        )
        Image(
            painter = painterResource(id = R.drawable.bg_top),
            contentDescription = "Background Image",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .align(Alignment.TopCenter),
            contentScale = ContentScale.Crop
        )

        if (!loggedIn) {
            // Login form card
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.65f)
                    .align(Alignment.BottomCenter)
                    .background(Color.White, RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp))
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Logo
                Icon(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(40.dp),
                    tint = Color(0xFF00ACC1)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text("Vendor Login", fontSize = 22.sp, color = Color.Black)

                Spacer(modifier = Modifier.height(20.dp))

                // Mobile input
                BasicTextField(
                    value = mobileNumber,
                    onValueChange = { newValue ->
                        val filtered = newValue.text.filter { it.isDigit() }.take(10)
                        mobileNumber = TextFieldValue(filtered, selection = TextRange(filtered.length))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp, vertical = 10.dp)
                        .background(
                            color = if (isFocused) Color.White else Color(0xFFEAF2F5),
                            shape = RoundedCornerShape(6.dp)
                        )
                        .onFocusChanged { isFocused = it.isFocused }
                        .drawBehind {
                            val strokeWidth = 2.dp.toPx()
                            val y = size.height - strokeWidth / 2
                            drawLine(
                                color = Color(0xFF87CEEB), // Sky blue bottom border
                                start = Offset(0f, y),
                                end = Offset(size.width, y),
                                strokeWidth = strokeWidth
                            )
                        },
                    textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    decorationBox = { innerTextField ->
                        Column(modifier = Modifier.padding(vertical = 10.dp)) {
                            if (isFocused || mobileNumber.text.isNotEmpty()) {
                                Text(
                                    text = "Enter 10 digit mobile number",
                                    fontSize = 12.sp,
                                    color = Color.Gray,
                                    modifier = Modifier.padding(start = 10.dp, bottom = 4.dp)
                                )
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                if (!isFocused) {  // Show icon only when NOT focused
                                    Icon(
                                        imageVector = Icons.Default.Phone,
                                        contentDescription = "Phone Icon",
                                        tint = Color.Gray,
                                        modifier = Modifier
                                            .padding(end = 8.dp)
                                            .size(20.dp)
                                    )
                                }

                                Box {
                                    if (!isFocused && mobileNumber.text.isEmpty()) {
                                        Text(
                                            text = "Enter 10 digit mobile number",
                                            fontSize = 16.sp,
                                            color = Color.LightGray
                                        )
                                    }
                                    innerTextField()
                                }
                            }
                        }
                    }
                )

                Spacer(modifier = Modifier.height(15.dp))

                // Password input
                BasicTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp, vertical = 1.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .onFocusChanged { isPasswordFocused = it.isFocused }
                        .drawBehind {
                            val strokeWidth = 2.dp.toPx()
                            val y = size.height - strokeWidth / 2
                            drawLine(
                                color = Color(0xFF87CEEB),
                                start = Offset(0f, y),
                                end = Offset(size.width, y),
                                strokeWidth = strokeWidth
                            )
                        },
                    textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                    singleLine = true,
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    decorationBox = { innerTextField ->
                        Column(modifier = Modifier.padding(vertical = 0.dp)) {
                            if (isPasswordFocused || password.text.isNotEmpty()) {
                                Text(
                                    text = "Enter Password",
                                    fontSize = 12.sp,
                                    color = Color.Gray,
                                    modifier = Modifier.padding(start = 10.dp, bottom = 0.dp)
                                )
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(horizontal = 8.dp, vertical = 1.dp)
                                    .fillMaxWidth()
                            ) {
                                if (!isPasswordFocused) {
                                    Icon(
                                        imageVector = Icons.Default.Lock,
                                        contentDescription = "Lock Icon",
                                        tint = Color.Gray,
                                        modifier = Modifier
                                            .padding(end = 4.dp)
                                            .size(20.dp)
                                    )
                                }

                                Box(modifier = Modifier.weight(1f)) {
                                    if (!isPasswordFocused && password.text.isEmpty()) {
                                        Text(
                                            text = "Password",
                                            fontSize = 16.sp,
                                            color = Color.LightGray
                                        )
                                    }
                                    innerTextField()
                                }

                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(
                                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                        contentDescription = "Toggle Password",
                                        tint = Color(0xFF266B84)
                                    )
                                }
                            }
                        }
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                errorMessage?.let {
                    Text(it, color = Color.Red, modifier = Modifier.padding(bottom = 12.dp))
                }

                // Login Button
                Button(
                    onClick = {
                        // Simple validation logic
                        if (mobileNumber.text.length == 10 && password.text.isNotEmpty()) {
                            loggedIn = true
                            userName = "Somesh"  // Hardcoded username
                            errorMessage = null
                            navController.navigate("home") {
                                popUpTo("login") { inclusive = true } // optional: clears login from backstack
                            }
                        } else {
                            errorMessage = "Please enter valid mobile number and password"
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
                ) {
                    Text("Login", fontSize = 18.sp, color = Color.White)
                }

                Spacer(modifier = Modifier.height(10.dp))

                TextButton(onClick = { navController.navigate("forgot_password") }) {
                    Text(
                        "Forgot Password?",
                        color = Color(0xFF2196F3),
                        style = TextStyle(
                            textDecoration = TextDecoration.Underline,
                            fontSize = 17.sp,
                        )
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Don't have an account?", color = Color.Gray)
                    TextButton(onClick = { navController.navigate("registerDetails") }) {
                        Text(
                            "Register for new account",
                            color = Color(0xFF2196F3),
                            style = TextStyle(
                                textDecoration = TextDecoration.Underline,
                                fontSize = 18.sp
                            )
                        )
                    }
                }
            }
        } else {
            // Logged in screen - welcome message
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .align(Alignment.BottomCenter)
                    .background(Color.White, RoundedCornerShape(topStart = 35.dp, topEnd = 35.dp))
                    .padding(top = 24.dp, bottom = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(64.dp),
                    tint = Color(0xFF00ACC1)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Welcome back, $userName",
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }
        }
    }
}

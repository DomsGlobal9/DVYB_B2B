package com.example.dvybb2b.ui.theme.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextStyle
import com.example.dvybb2b.R


@Composable
fun LoginScreen(navController: NavHostController) {
    var mobileNumber by remember { mutableStateOf("") }
    var text by remember { mutableStateOf(TextFieldValue("")) }
    var isFocused by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by remember { mutableStateOf(false) }
    var isPasswordFocused by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }


    Box(modifier = Modifier.fillMaxSize()) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.loginbg),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.None
        )

        // White rounded login card
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
                painter = painterResource(id = R.drawable.logo), // Replace with your logo drawable
                contentDescription = "Logo",
                modifier = Modifier.size(40.dp),
                tint = Color(0xFF00ACC1) // Adjust color to match
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text("Vendor Login", fontSize = 22.sp, color = Color.Black)

            Spacer(modifier = Modifier.height(20.dp))

            BasicTextField(
                value = text,
                onValueChange = { text = it },
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
                            color = Color(0xFF87CEEB),
                            start = Offset(0f, y),
                            end = Offset(size.width, y),
                            strokeWidth = strokeWidth
                        )
                    },
                textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                decorationBox = { innerTextField ->
                    Column(modifier = Modifier.padding(vertical = 10.dp)) {
                        if (isFocused || text.text.isNotEmpty()) {
                            Text(
                                text = "Enter 10 digit mobile number",
                                fontSize = 12.sp,
                                color = Color.Gray,
                                modifier = Modifier.padding(start = 30.dp, bottom = 4.dp)
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = "Phone Icon",
                                tint = Color.Gray,
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .size(20.dp)
                            )

                            Box {
                                if (!isFocused && text.text.isEmpty()) {
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
                    .padding(horizontal = 5.dp, vertical = 10.dp)
                    .background(
                        color = if (isPasswordFocused) Color.White else Color(0xFFEAF2F5),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .onFocusChanged { focusState ->
                        isPasswordFocused = focusState.isFocused
                    }
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
                                text = "Password",
                                fontSize = 12.sp,
                                color = Color.Gray,
                                modifier = Modifier.padding(start = 30.dp, bottom = 4.dp)
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                                .fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Lock Icon",
                                tint = Color.Gray,
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .size(20.dp)
                            )

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
                                    contentDescription = "Toggle Password"
                                )
                            }
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Login Button
            Button(
                onClick = { /* handle login */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
            ) {
                Text("Login", fontSize = 18.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(10.dp))

//            TextButton(onClick = {
//                navController.navigate("reset_password")
//            }) {
//                Text("Forgot Password?", color = Color(0xFF2196F3))
//            }
            TextButton(onClick = { navController.navigate("forgot_password") }) {
                Text("Forgot Password?",color = Color(0xFF2196F3))
            }



            Spacer(modifier = Modifier.height(24.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Don't have an account?", color = Color.Gray)
                TextButton(onClick = {
                    navController.navigate("registerDetails")
                }) {
                    Text("Register for new account", color = Color(0xFF2196F3))
                }
            }

        }
    }
}

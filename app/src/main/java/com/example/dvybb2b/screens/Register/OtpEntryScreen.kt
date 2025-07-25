package com.example.dvybb2b.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dvybb2b.R

@Composable
fun OtpEntryScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0E0E0)) // Light gray background
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Enter OTP",
                    fontSize = 22.sp,
                    color = Color(0xFF0B3C49)
                )

                Spacer(modifier = Modifier.height(24.dp))

                OtpInput()

                Spacer(modifier = Modifier.height(24.dp))

                SubmitButton {
                    navController.navigate("nextScreen") // Replace with your next route
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Resend OTP",
                    color = Color(0xFF3793B6),
                    fontSize = 14.sp,
                    modifier = Modifier.clickable {
                        // Handle resend OTP logic here
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                HelpSection()
            }
        }
    }
}

@Composable
fun OtpInput() {
    var otpValues by remember { mutableStateOf(List(4) { "" }) }

    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        otpValues.forEachIndexed { index, value ->
            OutlinedTextField(
                value = value,
                onValueChange = {
                    if (it.length <= 1 && it.all { char -> char.isDigit() }) {
                        otpValues = otpValues.toMutableList().also { list -> list[index] = it }
                    }
                },
                modifier = Modifier.width(48.dp),
                textStyle = LocalTextStyle.current.copy(fontSize = 20.sp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )
        }
    }
}

@Composable
fun SubmitButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5CA8C5))
    ) {
        Text(text = "Submit", fontSize = 16.sp, color = Color.White)
    }
}

@Composable
fun HelpSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Need help?",
            fontSize = 14.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
            imageVector = Icons.Default.Help,
            contentDescription = "Help",
            tint = Color(0xFF666666),
            modifier = Modifier.size(16.dp)
        )
    }
}

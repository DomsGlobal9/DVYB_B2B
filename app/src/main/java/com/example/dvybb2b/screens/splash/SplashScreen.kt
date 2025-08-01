package com.example.dvyb.ui.theme.components.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(true) {
        delay(2000)
        navController.navigate("onboarding/0") {
            popUpTo("splash") { inclusive = true }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF4F9EBB)) ,// Blue background
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "DVYB",
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White // White text
            )
            Spacer(modifier = Modifier.height(8.dp)) // Small spacing between texts
            Text(
                text = "“List It. Style It. Sell It.",
                fontSize = 16.sp,
                color = Color.White // White text
            )
        }
    }

}

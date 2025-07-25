package com.example.dvybb2b.ui.theme.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dvybb2b.R
//import com.example.onboardingapp.R
// Add this import at the top
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.ui.text.font.FontWeight

@Composable
fun OnboardingScreen(navController: NavHostController, step: Int) {
    val titles = listOf(
        "Grow your sales like never before",
        " Smart digital Visual dashboards\n" +
                " of sales Analysis",
        "Limit product returns for vendors."
    )
    val images = listOf(R.drawable.step1, R.drawable.step2, R.drawable.step3)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
//        verticalArrangement = Arrangement.SpaceBetween
        verticalArrangement = Arrangement.Top

    ) {
        Spacer(modifier = Modifier.height(30.dp)) // Small spacing between texts
//        Row(
//            modifier = Modifier
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.End
//        ) {
//            Button(
//                onClick = {
//                    navController.navigate("get_started") {
//                        popUpTo("onboarding/0") { inclusive = true }
//                    }
//                },
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFFEAF3F7), // Equivalent to rgba(20, 40, 47, 1)
//                    contentColor = Color.White
//                ),
//                shape = RoundedCornerShape(50),
//                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
//                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
//            ) {
//                Text("Skip", fontSize = 16.sp, color = Color.Black)
//                Spacer(modifier = Modifier.width(6.dp))
//                Icon(
//                    imageVector = Icons.Default.ArrowForward,
//                    contentDescription = "Skip Arrow",
//                    tint = Color.Black,
//                    modifier = Modifier.size(20.dp)
//                )
//            }
//        }
        if (step < 2) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = {
                        navController.navigate("onboarding/2") {
                            popUpTo("onboarding/0") { inclusive = true }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFEAF3F7),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(50),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text("Skip", fontSize = 16.sp, color = Color.Black)
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Skip Arrow",
                        tint = Color.Black,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }else {
            // Empty space to occupy same height
            Spacer(modifier = Modifier
                .height(50.dp)
                .width(100.dp)) // Adjust width/height to match Skip button
        }

        Text(
            text = "DVYB",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4F9EBB)

        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            (0..2).forEach { i ->
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .height(4.dp)
                        .width(80.dp)
                        .background(
                            if (i <= step) Color(0xFF4F9EBB)
                            else Color(0xFFE3F1F7),
                            RoundedCornerShape(50)
                        )
                )
            }
        }

        Image(
            painter = painterResource(id = images[step]),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            titles[step],
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(100.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center // Center horizontally
        ) {
            if (step < 2) {
                // "Next" button aligned to end
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = { navController.navigate("onboarding/${step + 1}") },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Black
                        ),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                        modifier = Modifier.padding(top = 20.dp)
                    ) {
                        Text("Next", fontSize = 24.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Next Arrow",
                            tint = Color.Black,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            } else {
                Spacer(modifier = Modifier.height(100.dp))
                // "Continue" button centered
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 35.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = { navController.navigate("login") },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4F9EBB), // Sky Blue
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(10.dp),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth(0.8f) // adjust width here (80% of parent width)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text("Start", fontSize = 20.sp)
                            Spacer(modifier = Modifier.width(8.dp)) // space between text and icon
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = "Arrow Forward",
                                tint = Color.White
                            )
                        }
                    }
                }
            }

        }



    }
}

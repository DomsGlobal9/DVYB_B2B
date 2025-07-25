package com.example.dvybb2b.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dvybb2b.R

@Composable
fun VendorPreferencesScreen() {
    val selectedGender = remember { mutableStateOf("Women") }
    val selectedCategory = remember { mutableStateOf("Clothing") }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.loginbg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        // Foreground Card
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.93f)
                .align(Alignment.BottomCenter)
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 20.dp)
                ) {
                    Text(
                        text = "What do you want to sell in DVYB",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "We can customize your online store to boost your business and improve customer experience.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 8.dp, bottom = 20.dp)
                    )

                    // Gender Section
                    Text(
                        text = "Who do you sell for?",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Row(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        listOf("Women", "Men", "Kids").forEach { gender ->
                            FilterChip(
                                selected = selectedGender.value == gender,
                                onClick = { selectedGender.value = gender },
                                label = { Text(gender) },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }

                    // Category Section
                    Text(
                        text = "Select Categories",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                    Row(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        listOf("Clothing", "Accessories").forEach { category ->
                            FilterChip(
                                selected = selectedCategory.value == category,
                                onClick = { selectedCategory.value = category },
                                label = { Text(category) },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }

                    // Submit Button
                    Button(
                        onClick = { /* Submit logic */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 28.dp)
                            .height(48.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4C9FDB),
                            contentColor = Color.White
                        )
                    ) {
                        Text("submit")
                    }

                    // Help Row
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp)
                    ) {
                        Text("Need help", color = Color.Gray, fontSize = 12.sp)
                        Icon(
                            imageVector = Icons.Filled.Help,
                            contentDescription = "Help",
                            tint = Color.Gray,
                            modifier = Modifier
                                .size(14.dp)
                                .padding(start = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

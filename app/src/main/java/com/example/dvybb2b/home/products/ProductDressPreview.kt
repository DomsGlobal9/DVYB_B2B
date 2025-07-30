package com.example.dvybb2b.home.products

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dvybb2b.R
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavController
import com.example.dvybb2b.viewmodel.DressPreviewViewModel



@Composable
fun ProductDressPreview(
    dressName: String,
    viewModel: DressPreviewViewModel = viewModel()) {
    val dressList = viewModel.dressList
    val selectedDress by viewModel.selectedDress


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        // Top Title Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(vertical = 16.dp, horizontal = 16.dp)
        ) {
            Column  {
                Text(
                    text = "Product View",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Look at the products to view",
                    fontSize = 14.sp,

                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(26.dp))
        // Content Container with light background
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)

//                .fillMaxSize()
                .background(Color(0xFFE6F0F8))
                .padding(16.dp)
        ) {

            // Header Row with Icon + Label
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.hoodies),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Total Products", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Row with Total Units + Dropdown Button
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .border(1.dp, Color.Gray)
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Text("0", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text("Total Units", fontSize = 14.sp)
                }

                var expanded by remember { mutableStateOf(false) }
                val dressTypes = listOf("Cotton Saree", "Silk Saree", "Designer Saree", "Wedding Saree")

                Box {
                    Button(
                        onClick = { expanded = true },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                    ) {
                        Text(text = "Select Dress", color = Color.Black)
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        dressTypes.forEach { type ->
                            DropdownMenuItem(
                                text = { Text(type) },
                                onClick = {
                                    expanded = false
                                    // You can optionally filter your dressList based on this type
                                    // or update selectedDress.name = type if that's what you intend
                                    // For now, we just show selection
                                    // viewModel.filterByType(type) ← if you have such a function
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Selected Dress Pill
            Button(
                onClick = {},
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(text = selectedDress.name, color = Color.Black)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Breadcrumb
            Text(
                "Category 1 > Dress type > ${selectedDress.name}",
                fontSize = 14.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Main product row view
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.Top
            ) {
                // Large Main Product Image
                Image(
                    painter = painterResource(id = selectedDress.imageRes),
                    contentDescription = selectedDress.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .weight(1f)
                        .height(350.dp)
                        .padding(8.dp)
                        .border(2.dp, Color.LightGray)
                )

                Spacer(modifier = Modifier.width(12.dp))

                // Thumbnail List
                LazyColumn(
                    modifier = Modifier
                        .height(350.dp)
                        .width(80.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    item {
                        Text(
                            "Preview",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                    items(dressList) { dress ->
                        Image(
                            painter = painterResource(id = dress.imageRes),
                            contentDescription = dress.name,
                            modifier = Modifier
                                .size(60.dp)
                                .padding(4.dp)
                                .border(1.dp, if (dress == selectedDress) Color.Black else Color.Gray)
                                .clickable {
                                    viewModel.selectDress(dress)

                                }
                        )
                    }
                }
            }
        }
    }
}
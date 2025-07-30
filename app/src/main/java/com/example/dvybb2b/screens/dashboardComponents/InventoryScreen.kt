package com.example.dvybb2b.screens.dashboardComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dvybb2b.R

data class StockItem(val name: String, val count: Int = 0, val imageRes: Int)

@Composable
fun InventoryScreen() {
    var searchQuery by remember { mutableStateOf("") }
    var stockAlertEnabled by remember { mutableStateOf(false) }

    val lowStockItems = listOf(
        StockItem("Kids Combo", 5, R.drawable.kids_combo),
        StockItem("Hoodies", 2, R.drawable.hoodies),
        StockItem("Kids Bottoms", 10, R.drawable.kids_bottoms),
    )

    val outOfStockItems = listOf(
        StockItem("Trendy Red Lehenga", 0, R.drawable.red_lehenga),
        StockItem("Cotton Saree", 0, R.drawable.cotton_saree),
        StockItem("Org Cotton T-shirt", 0, R.drawable.org_cotton_tshirt),
    )

    val categoryItems = listOf(
        StockItem("Sarees", 12, R.drawable.saree),
        StockItem("Kids Wear", 8, R.drawable.kidswear),
        StockItem("Mens Wear", 15, R.drawable.menswear),
        StockItem("Lehenga", 6, R.drawable.lehenga),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        // Top Bar
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 8.dp)
            )

            Text(
                text = "Inventory",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Search
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFD0E8FF), RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                cursorColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Total Items Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFD0E8FF), RoundedCornerShape(12.dp))
                .padding(vertical = 16.dp, horizontal = 16.dp)
        ) {
            Column {
                Text(text = "Total", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "0", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Stock Alerts Toggle
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Stock Alerts",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = stockAlertEnabled,
                onCheckedChange = { stockAlertEnabled = it }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn {
            // Low Stock Section
            if (lowStockItems.isNotEmpty()) {
                item {
                    Text("Low Stock", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                }
                items(lowStockItems) { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = item.imageRes),
                            contentDescription = item.name,
                            modifier = Modifier
                                .size(48.dp)
                                .background(Color.LightGray, CircleShape)
                        )
                        Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                            Text(item.name, fontWeight = FontWeight.Medium)
                            Text("12 items", fontSize = 12.sp, color = Color.Gray)
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Text(item.count.toString(), fontWeight = FontWeight.Bold)
                    }
                }
            }

            // Out of Stock Section
            if (outOfStockItems.isNotEmpty()) {
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Out of Stock", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                }
                items(outOfStockItems) { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = item.imageRes),
                            contentDescription = item.name,
                            modifier = Modifier
                                .size(48.dp)
                                .background(Color.LightGray, CircleShape)
                        )
                        Text(
                            text = item.name,
                            modifier = Modifier.padding(start = 12.dp),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            // Inventory by Category Section (reusable row)
            if (categoryItems.isNotEmpty()) {
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Inventory by Category", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                }
                items(categoryItems) { item ->
                    InventoryItemRow(item)
                }
            }
        }
    }
}

@Composable
fun InventoryItemRow(item: StockItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.name,
            modifier = Modifier
                .size(48.dp)
                .background(Color.LightGray, CircleShape)
        )
        Column(modifier = Modifier.padding(start = 12.dp)) {
            Text(item.name, fontWeight = FontWeight.Medium)
            Text("Items: ${item.count}", fontSize = 12.sp, color = Color.Gray)
        }
    }
}
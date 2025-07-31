package com.example.dvyb.ui.theme.home.orders

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dvybb2b.R
import com.example.dvyb.ui.theme.home.orders.model.Order

@Composable
fun OrdersScreen(navController: NavController, viewModel: OrdersViewModel = viewModel()) {
    var selectedCategory by remember { mutableStateOf("All") }
    var searchText by remember { mutableStateOf("") }
    val categories = listOf("All", "Shipped", "Delivered", "Pending", "Canceled")
    val selectedTabIndex = categories.indexOf(selectedCategory).takeIf { it >= 0 } ?: 0

    val orders by viewModel.orders.collectAsState()

    val filteredOrders = orders.filter { order ->
        val matchesSearch = searchText.isEmpty() || order.orderId.contains(searchText, ignoreCase = true)
        val matchesCategory = when (selectedCategory) {
            "All" -> true
            "Shipped" -> order.tracking.isShipped && !order.tracking.isDelivered
            "Delivered" -> order.tracking.isDelivered
            "Pending" -> !order.tracking.isShipped
            "Canceled" -> false
            else -> true
        }
        matchesSearch && matchesCategory
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Search Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF5F5F5))
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.Gray)
                Spacer(modifier = Modifier.width(8.dp))
                BasicTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    decorationBox = { innerTextField ->
                        if (searchText.isEmpty()) {
                            Text("Search", color = Color.Gray)
                        }
                        innerTextField()
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Your orders",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(12.dp))

        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            edgePadding = 16.dp,
            divider = {},
            indicator = {},
            backgroundColor = Color.Transparent
        ) {
            categories.forEachIndexed { _, category ->
                val isSelected = selectedCategory == category
                Tab(
                    selected = isSelected,
                    onClick = { selectedCategory = category }
                )
                {
                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .padding(vertical = 8.dp, horizontal = 4.dp)
                            .border(
                                width = 1.dp,
                                color = Color.Gray, // Add a color here
                                shape = RoundedCornerShape(6.dp)
                            )
                            .clip(RoundedCornerShape(6.dp))
                            .background(
                                when {
                                    isSelected && category == "Canceled" -> Color.Red
                                    (isSelected) ->Color(0xFF7DBBD1)
                                    else -> Color(0xFFFFFFFF)
                                }
                            )
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = category,
                            color = if (isSelected) Color.White else Color.Black,
                            fontSize = 14.sp,
                            maxLines = 1
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (filteredOrders.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Looks like you don't have \n any Order yet!",
                        fontSize = 18.sp, // Changed from dp to sp for text sizing
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(8.dp) // Added padding for better spacing
                    )

                    Image(
                        painter = painterResource(id = R.drawable.order_empty_fallback),
                        contentDescription = "Empty orders",
                        modifier = Modifier.size(340.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                filteredOrders.forEach { order ->
                    OrderCard(order = order) {
                        navController.navigate("orderDetails/${order.orderId}")
                    }
                }
            }
        }
    }
}

@Composable
fun OrderCard(order: Order, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF5F5F5))
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "Order icon",
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = "Order #${order.orderId}", fontWeight = FontWeight.SemiBold)
                    Text(
                        text = "${order.itemCount} item${if (order.itemCount > 1) "s" else ""}",
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                }
            }
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Arrow",
                tint = Color.Black
            )
        }
    }
}

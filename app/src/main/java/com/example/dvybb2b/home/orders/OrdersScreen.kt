package com.example.dvyb.ui.theme.home.orders

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
    val interactionSource = remember { MutableInteractionSource() }
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
                .border(
                    width = 1.dp,
                    color = Color(0XFFB9B9B9))
                .background(Color(0xFFF5F5F5))
                .padding(horizontal = 9.dp, vertical = 16.dp)

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
                            Text("Search",fontSize = 24.sp, color = Color.Gray)
                        }
                        innerTextField()
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Your orders",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(18.dp))

        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            edgePadding = 4.dp,
            backgroundColor = Color.Transparent,
            divider = {},
            indicator = {}
        ) {
            categories.forEachIndexed { index, category ->
                val isSelected = selectedCategory == category

                val interactionSource = remember { MutableInteractionSource() }

                Tab(
                    selected = isSelected,
                    onClick = { selectedCategory = category },
                    interactionSource = interactionSource,
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.Black,
                    content = {
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 4.dp, vertical = 8.dp)
                                .border(
                                    width = 1.dp,
                                    color = when {
                                        isSelected && category == "Canceled" -> Color.Red
                                        isSelected -> Color(0xFF7DBBD1)
                                        else -> Color(0xFFB9B9B9)
                                    },
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .clip(RoundedCornerShape(8.dp))
                                .background(
                                    color = when {
                                        isSelected && category == "Canceled" -> Color.Red
                                        isSelected -> Color(0xFF7DBBD1)
                                        else -> Color.White
                                    }
                                )
                                .padding(
                                    horizontal = if (isSelected && category == "All") 24.dp else 18.dp,
                                    vertical = 12.dp
                                )
                        ) {
                            Text(
                                text = category,
                                color = if (isSelected) Color.White else Color.Black,
                                fontSize = 14.sp,
                                maxLines = 1
                            )
                        }
                    }
                )


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
                        modifier = Modifier.size(150.dp),
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
                Image(
                    painter = painterResource(R.drawable.ordericon),
                    contentDescription = "order",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = "Order #${order.orderId}", fontWeight = FontWeight.SemiBold)
                    Text(
                        text = "${order.itemCount} item${if (order.itemCount > 1) "s" else ""}",
                        fontSize = 15.sp,
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

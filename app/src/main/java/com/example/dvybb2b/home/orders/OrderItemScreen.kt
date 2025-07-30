package com.example.dvyb.ui.theme.home.orders

import OrdersViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import androidx.compose.material.Divider

import com.example.dvyb.ui.theme.home.orders.model.Order

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderItemScreen(
    orderId: String,
    viewModel: OrdersViewModel = viewModel(),
    navController: NavController
) {
    val orders by viewModel.orders.collectAsState()

    // Find the order by id
    val order = orders.find { it.orderId == orderId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Order Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        if (order == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text("Order not found")
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                    .padding(innerPadding)
            ) {
                Text("Order #${order.orderId}", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Spacer(Modifier.height(12.dp))

                Text("Order Items", fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(8.dp))

                order.items.forEach {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
                            .padding(8.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = rememberAsyncImagePainter(it.image),
                                contentDescription = it.name,
                                modifier = Modifier.size(40.dp),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(it.name)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text("Customer details", fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(order.customerName)
                Text(order.shippingAddress)

                Spacer(modifier = Modifier.height(16.dp))
                Text("Shipping details", fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(order.shippingAddress)

                Spacer(modifier = Modifier.height(16.dp))
                Text("Tracking", fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(8.dp))

                val statuses = listOf(
                    "Order Placed" to order.tracking.isPlaced,
                    "Order Confirmed" to order.tracking.isConfirmed,
                    "Shipped" to order.tracking.isShipped,
                    "Delivered" to order.tracking.isDelivered
                )

                statuses.forEachIndexed { index, pair ->
                    val (label, isDone) = pair
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = if (isDone) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                            contentDescription = label,
                            tint = if (isDone) Color(0xFF0E9FF2) else Color.Gray
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(label)
                    }
                    if (index != statuses.lastIndex) {
                        Divider(
                            modifier = Modifier
                                .height(24.dp)
                                .width(2.dp)
                                .padding(start = 12.dp)
                                .background(Color.LightGray)
                        )
                    }
                }
            }
        }
    }
}

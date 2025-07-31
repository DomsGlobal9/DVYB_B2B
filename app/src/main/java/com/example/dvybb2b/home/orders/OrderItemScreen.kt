package com.example.dvyb.ui.theme.home.orders


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dvybb2b.R
import com.example.dvyb.ui.theme.home.orders.model.OrderItem
import com.example.dvyb.ui.theme.home.orders.model.TrackingStatus

@Composable
fun OrderItemScreen(
    modifier: Modifier = Modifier,
    orderId: String,
    viewModel: OrdersViewModel,
    navController: NavController
) {
    val orders by viewModel.orders.collectAsState()
    val order = viewModel.getOrderById(orderId)
    val scrollState = rememberScrollState()

    androidx.compose.material.Scaffold(

        topBar = {
            Column {
                Spacer(modifier = Modifier.height(30.dp)) // 👈 Adds top space
                TopAppBar(
                    title = {
                        Text(
                            text = "Order #${order?.orderId}",
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.Black
                            )
                        }
                    },
                    backgroundColor = Color.White,
                    elevation = 0.dp
                )
            }
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
                    .padding(innerPadding)
                    .verticalScroll(scrollState)
                    .background(Color.White)
                    .padding(16.dp)

            ) {
                // Order Header
                Spacer(modifier = Modifier.height(16.dp))

                // Order Items Section
                Text(
                    text = "Order Items",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Order Items List
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF4F4F4), shape = RoundedCornerShape(8.dp))
                        .padding(16.dp) // Padding inside the box around the list
                ) {
                    Column {
                        // 👇 Display number of items
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Icon from drawable folder
                            Image(
                                painter = painterResource(id = R.drawable.ic_item_count),
                                contentDescription = "Items Icon",
                                modifier = Modifier
                                    .size(30.dp)
                                    .padding(end = 6.dp)
                            )

                            Text(
                                text = "${order.items.size} items",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp,
                                color = Color.DarkGray
                            )
                        }


                        Spacer(modifier = Modifier.height(8.dp))

                        // 👇 Your list of items
                        order.items.forEach { item ->
                            OrderItemRow(item = item)
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }
                }




                Spacer(modifier = Modifier.height(24.dp))


                // Customer Details Section
                Text(
                    text = "Customer details",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(12.dp))

// Background card for customer details
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF5F5F5), shape = MaterialTheme.shapes.medium)
                        .padding(16.dp)
                ) {
                    Text(
                        text = order.customerName,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "9982388989", // Replace with actual phone number if available
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = order.shippingAddress,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

// Shipping Details Section
                Text(
                    text = "Shipping details",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(12.dp))

// Background card for shipping address
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF5F5F5), shape = MaterialTheme.shapes.medium)
                        .padding(16.dp)
                ) {
                    Text(
                        text = order.shippingAddress,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                }


                // Order Progress Section
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Order Progress",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                OrderProgressTracker(tracking = order.tracking)
            }
        }
    }
}



@Composable
fun OrderItemRow(item: OrderItem) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFAFAFAFA), shape = MaterialTheme.shapes.medium) // Light gray background
            .padding(horizontal = 12.dp, vertical = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White) // Set background first
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Product Image
            Image(
                painter = painterResource(id = R.drawable.ic_product_placeholder),
                contentDescription = "Product",
                modifier = Modifier
                    .size(50.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(Color.White),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Product Name
            Text(
                text = item.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }

    }

}

@Composable
fun OrderProgressTracker(tracking: TrackingStatus) {
    val steps = listOf(
        Triple("Order Placed", tracking.isPlaced, "28 May"),
        Triple("Order Confirmed", tracking.isConfirmed, "28 May"),
        Triple("Shipped", tracking.isShipped, "28 May"),
        Triple("Delivered", tracking.isDelivered, "28 May")
    )

    val lastCompletedIndex = steps.indexOfLast { it.second }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, top = 5.dp)
    ) {
        steps.forEachIndexed { index, (label, isCompleted, date) ->
            ProgressStep(
                label = label,
                isCompleted = isCompleted,
                date = date,
                showLine = index < steps.lastIndex
            )
//            Spacer(modifier = Modifier.height(0.dp)) // fixed spacing between steps
        }
    }
}

@Composable
fun ProgressStep(
    label: String,
    isCompleted: Boolean,
    date: String,
    showLine: Boolean
) {
    val completedColor = Color(0xFF007EA7)
    val inactiveColor = Color(0xFFB0BEC5)
    val circleColor = if (isCompleted) completedColor else Color(0xFFE0F7FA)
    val iconColor = if (isCompleted) Color.White else Color.Transparent
    val labelColor = if (isCompleted) Color.Black else inactiveColor
    val dateColor = if (isCompleted) Color(0xFF333333) else inactiveColor

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        // Left Icon + Line
        Column(
            modifier = Modifier
                .padding(top = 2.dp)
                .width(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(circleColor),
                contentAlignment = Alignment.Center
            ) {
                if (isCompleted) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = iconColor,
                        modifier = Modifier.size(12.dp)
                    )
                }
            }

            if (showLine) {
                Spacer(modifier = Modifier.height(3.dp)) // tight connection
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(40.dp)
                        .background(completedColor)
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Label and Date Row with space-between
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = labelColor
            )
            Text(
                text = date,
                fontSize = 12.sp,
                color = dateColor
            )
        }
    }
}




package com.example.dvyb2b2.ui.dashboard

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dvybb2b.R
import com.example.dvybb2b.viewmodel.dashboard.DashboardViewModel

@Composable
fun DashboardScreen(navController: NavController) {
    val viewModel: DashboardViewModel = viewModel()
    val dashboardData by viewModel.dashboardData
    val selectedTab by viewModel.selectedTab
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .background(Color(0xFFF2F2F2))
    ) {


        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Welcome, Vendor!",
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF0C4559)
        )

        Text(
            text = "Explore your shop",
            fontSize = 18.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.saree_sample),
            contentDescription = "Shop Banner",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Your Shop",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Stat Cards Grid
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard(
                    title = "Products",
                    value = dashboardData.productCount.toString(),
                    iconResId = R.drawable.ic_products,
                    Modifier.weight(1f),
                    onClick = { navController.navigate("products") }
                )
                StatCard("Inventory", "2,345", R.drawable.ic_inventory, Modifier.weight(1f),
                    onClick ={navController.navigate("inventory")} )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard("Total Revenue", "₹ 12,000", R.drawable.ic_revenue, Modifier.weight(1f),
                    onClick ={navController.navigate("total_revenue")} )
                StatCard("Units Sold", "1,300", R.drawable.ic_units, Modifier.weight(1f),
                    onClick ={navController.navigate("total_Units")} )
            }
        }

        // Sales Chart Section
        SalesPerformanceSection()
    }
}

@Composable
fun StatCard(title: String, value: String, iconResId: Int, modifier: Modifier = Modifier,
             onClick: () -> Unit
    ) {
    Card(
        modifier = modifier.height(100.dp)
                    .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),

        ) {
            Column {
                Text(text = title, color = Color.Gray, fontSize = 14.sp)
                Text(
                    text = value,
                    color = Color(0xFF4E0021),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = title,
                modifier = Modifier
                    .size(60.dp)
                    .padding(start = 12.dp)
            )
        }
    }
}

@Composable
fun SalesPerformanceSection() {
    var selectedTab by remember { mutableStateOf("Monthly") }

    val months = listOf("May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec", "Jan")
    val heights = listOf(60, 20, 60, 40, 60, 60, 20, 100, 60)

    Spacer(modifier = Modifier.height(32.dp))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text("Performance", style = MaterialTheme.typography.titleMedium)
        Text("Sales Statistics", color = Color.Gray, fontSize = 14.sp)
        Text("Updated: Jan 26, 2024, 10 AM", fontSize = 12.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFFE8E8E8))
        ) {
            listOf("Daily", "Weekly", "Monthly").forEach { label ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { selectedTab = label }
                        .background(if (selectedTab == label) Color(0xFF51AFA0) else Color.Transparent)
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        label,
                        color = if (selectedTab == label) Color.White else Color.Black
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("High: DEC – ₹0", style = MaterialTheme.typography.labelSmall)
            Text("Low: JUN – ₹0", style = MaterialTheme.typography.labelSmall)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp), // Give room for bars to grow bottom-up
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom // ✅ bars grow bottom to top
        ) {
            months.forEachIndexed { index, month ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Box(
                        modifier = Modifier
                            .width(16.dp)
                            .height(heights[index].dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(
                                if (month == "Dec") Color(0xFF163C56)
                                else Color(0xFFB8E6E1)
                            )
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(month, style = MaterialTheme.typography.labelSmall)
                }
            }
        }
    }
}



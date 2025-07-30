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

@Composable
fun TotalRevenueScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        // Top Bar
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Total Revenue", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Revenue Summary
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            SummaryCard(title = "Total Revenue", value = "₹0", highlighted = true)
            SummaryCard(title = "Total Refunds", value = "0")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Dropdowns
        DropdownField(label = "Select city")
        Spacer(modifier = Modifier.height(8.dp))
        DropdownField(label = "Select Product")

        Spacer(modifier = Modifier.height(16.dp))

        // Revenue Analysis Section
        Text("Revenue Analysis", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
        Text("₹0", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(8.dp))

        // Toggle Tabs
        val selectedTab = remember { mutableStateOf("Monthly") }
        Row {
            listOf("Daily", "Weekly", "Monthly").forEach {
                FilterChip(text = it, selected = selectedTab.value == it) {
                    selectedTab.value = it
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text("High: DEC – ₹0", fontSize = 12.sp)
        Text("Low: JUN – ₹0", fontSize = 12.sp)

        Spacer(modifier = Modifier.height(16.dp))
        RevenueBarChart()

        Spacer(modifier = Modifier.height(16.dp))
        RevenueByProductList()
    }
}

@Composable
fun SummaryCard(title: String, value: String, highlighted: Boolean = false) {
    Card(
        colors = if (highlighted)
            CardDefaults.cardColors(containerColor = Color(0xFFDBEEF2))
        else
            CardDefaults.cardColors(),
        modifier = Modifier
            .padding(end = if (highlighted) 8.dp else 0.dp)
            .height(80.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(title, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            Text(value, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownField(label: String) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        TextField(
            value = selectedOption,
            onValueChange = { selectedOption = it },
            readOnly = true,
            label = { Text(label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier.menuAnchor().fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listOf("Option 1", "Option 2").forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        selectedOption = item
                        expanded = false
                    }
                )
            }
        }
    }
}

//@Composable
//fun FilterChip(text: String, selected: Boolean, onClick: () -> Unit) {
//    Button(
//        onClick = onClick,
//        colors = ButtonDefaults.buttonColors(
//            containerColor = if (selected) Color(0xFFDBEEF2) else Color.White,
//            contentColor = if (selected) Color.Black else Color.Gray
//        ),
//        shape = RoundedCornerShape(8.dp),
//        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
//        elevation = ButtonDefaults.buttonElevation(2.dp)
//    ) {
//        Text(text)
//    }
//}

//@Composable
//fun RevenueBarChart() {
//    val months = listOf("May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec", "Jan")
//    val values = listOf(0f, 1f, 2f, 1.5f, 2.5f, 1f, 1.7f, 3f, 1.2f)
//    val maxVal = values.maxOrNull() ?: 1f
//
//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.SpaceAround
//    ) {
//        months.zip(values).forEach { (month, value) ->
//            Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                Box(
//                    modifier = Modifier
//                        .width(20.dp)
//                        .height((value / maxVal * 100).dp)
//                        .background(
//                            color = if (value == maxVal) Color.Black else Color(0xFFDBEEF2),
//                            shape = RoundedCornerShape(4.dp)
//                        )
//                )
//                Spacer(modifier = Modifier.height(4.dp))
//                Text(month, fontSize = 12.sp)
//            }
//        }
//    }
//}

@Composable
fun RevenueByProductList() {
    val products = listOf(
        Triple("Trendy Red Lehenga", "₹5,000", R.drawable.red_lehenga),
        Triple("Cotton saree", "₹4,000", R.drawable.cotton_saree),
        Triple("Kids Bottom", "₹3,345.67", R.drawable.kids_bottom)
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Text("Revenue by Product", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(products) { (title, price, imageRes) ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .background(Color.LightGray, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(title, fontWeight = FontWeight.Bold)
                        Text(price, color = Color.Gray)
                    }
                }
            }
        }
    }
}

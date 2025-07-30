package com.example.dvybb2b.screens.homecomponents


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dvybb2b.R
import com.example.dvybb2b.home.SidebarItem
import com.example.dvybb2b.viewmodel.dashboard.DashboardViewModel
import androidx.lifecycle.viewmodel.compose.viewModel




@Composable
fun Sidebar(navController: NavController, onClose: () -> Unit,viewModel: DashboardViewModel = viewModel()) {
    var selectedItem by remember { mutableStateOf<SidebarItem?>(null) }
    val currentDate = viewModel.currentDate.value
    val items = listOf(
        SidebarItem.Dashboard,
        SidebarItem.Products,
        SidebarItem.Orders,
        SidebarItem.Profile
    )

    Column(
        modifier = Modifier
            .width(300.dp)
            .fillMaxHeight()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(30.dp)
                )
                Text("DVYB – Vendor", style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold )
                IconButton(onClick = onClose) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Close Sidebar")
                }
            }

            Text(currentDate, style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(10.dp,20.dp))

            Spacer(Modifier.height(16.dp))

            items.forEach { item ->
                val isSelected = selectedItem == item

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable{
                            selectedItem = item
                            navController.navigate(item.route)
                            onClose()
                        }
                        .padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        modifier = Modifier.size(20.dp),
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

        // Logout at bottom
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate("login")
                }
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = SidebarItem.Logout.icon),
                contentDescription = "Logout",
                modifier = Modifier.size(20.dp),
                tint = Color.Red
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = SidebarItem.Logout.title,
                color = Color.Red,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

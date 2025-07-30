package com.example.dvybb2b.home.products

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.dvybb2b.viewmodel.Products.ProductsViewModel
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.ui.graphics.Brush




@Composable
fun ProductsScreen(navController: NavController,viewModel: ProductsViewModel = ProductsViewModel()) {
    val overview = viewModel.overview
    val recent = viewModel.recentlyAdded
    val top = viewModel.topProducts
    val categoryWise = viewModel.categoryWise

    val gradientBrush = Brush.linearGradient(
        colors = listOf(Color(0xFFCC71CE), Color(0xFF673968))
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
                Spacer(Modifier.width(8.dp))
                Text(
                    "Products",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(Modifier.height(12.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE9F1F4))
            ) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                    ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Total Products", fontWeight = FontWeight.SemiBold)
                        Text(
                            "${overview.totalCount}",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(){
                        Text("Last updated:", style = MaterialTheme.typography.labelSmall)
                        Text(" ${overview.lastUpdated}", style = MaterialTheme.typography.labelLarge)
                    }

                }
            }

            Spacer(Modifier.height(16.dp))
            Text("Recently Added", style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(8.dp))

            recent.forEach {
                val gradientBrush = when (it.name) {
                    "Women" -> Brush.linearGradient(
                        colors = listOf(Color(0xFFCC71CE), Color(0xFF673968))
                    )
                    "Men" -> Brush.linearGradient(
                        colors = listOf(Color(0xFF718ACE), Color(0xFF394668))
                    )
                    else -> Brush.linearGradient(
                        colors = listOf(Color(0xFF71CE99), Color(0xFF39684D))
                    )
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable {
                            navController.navigate("recently_added/${it.name}")
                        }
                ) {
                    Box(
                        modifier = Modifier
                            .background(brush = gradientBrush)
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(it.name, color = Color.White, modifier = Modifier.weight(1f),
                                style = MaterialTheme.typography.titleLarge)
                            Text("${it.count}", color = Color.White, fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleLarge)
                        }
                    }
                }
            }

            Spacer(Modifier.height(10.dp))
            Button(
                onClick = { /* Handle Add Product */ },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF7DBBD1)),
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Add Product")
            }

            Spacer(Modifier.height(28.dp))
            Text("Top Performing Products", style = MaterialTheme.typography.titleMedium)
        }

        items(top) { item ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberAsyncImagePainter(item.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color.Gray)
                )
                Spacer(Modifier.width(12.dp))
                Text(item.name, modifier = Modifier.weight(1f))
                Icon(Icons.Default.ArrowForward, contentDescription = null)
            }
        }

        item {
            Spacer(Modifier.height(24.dp))
            Text("Category Wise Products", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
        }

        items(categoryWise) { cat ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberAsyncImagePainter(cat.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color.Gray)
                )
                Text(cat.name, modifier = Modifier.weight(1f))
                Text("${cat.count} items")
            }
        }

        item { Spacer(modifier = Modifier.height(64.dp)) }
    }
}

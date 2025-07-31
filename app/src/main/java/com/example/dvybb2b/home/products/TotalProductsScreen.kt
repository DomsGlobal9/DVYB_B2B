package com.example.dvybb2b.viewmodel

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController
import com.example.dvybb2b.home.products.components.ProductScreenHeaderSection
import com.example.dvybb2b.viewmodel.Products.RecentlyAddedProductsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TotalProductsScreen(
    navController: NavHostController,
    viewModel: RecentlyAddedProductsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val selectedGender = viewModel.selectedGender
    val selectedCategory = viewModel.selectedCategory

    // Category Lists
    val womenList = listOf(
        "Top Wear" to 16,
        "Bottom Wear" to 12,
        "Ethnic Wear" to 18,
        "Dresses & Jumpsuits" to 22,
        "Loungewear & Sleepwear" to 34,
        "Winterwear" to 56,
        "Active Wear" to 56,
        "Inner Wear" to 56,
        "Maternity Wear" to 56
    )

    val menList = listOf(
        "Top Wear" to 16,
        "Bottom Wear" to 12,
        "Ethnic Wear" to 18,
        "Loungewear & Innerwear" to 34,
        "Activewear" to 43,
        "Winterwear" to 56
    )

    val kidsList = listOf(
        "Boys" to 16,
        "Girls" to 12,
        "Infants" to 18
    )

    val currentList = when (selectedGender) {
        "Men" -> menList
        "Kids" -> kidsList
        else -> womenList
    }

    Column(modifier = Modifier.fillMaxSize()) {
        ProductScreenHeaderSection(
            title = "Products",
            navController = navController,
            viewModel = viewModel
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
            items(currentList) { (name, count) ->
                val isSelected = selectedCategory == name

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = if (isSelected) 1.dp else 0.dp,
                            color = if (isSelected) Color(0xFF79BAEC) else Color.Transparent,
                            shape = RectangleShape
                        )
                        .clickable {
                            viewModel.updateCategory(name)
                            navController.navigate("categoryDetail/$selectedGender/$name")
                        }
                        .padding(vertical = 8.dp, horizontal = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("$name ($count)", fontSize = 16.sp)
                    Icon(Icons.Default.ArrowForward, contentDescription = null)
                }

            }
        }
    }
}

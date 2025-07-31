package com.example.dvybb2b.home.products

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dvybb2b.R
import com.example.dvybb2b.home.products.components.EmptyState
import com.example.dvybb2b.viewmodel.Products.AddProductViewModel


@Composable
fun EmptyScreen(navController: NavController,viewModel: AddProductViewModel = viewModel()) {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            EmptyState(
                title = "Your Products",
                subtitle = "Looks like you don't have any products yet!",
                imageRes = R.drawable.empty_box,
                buttonText = "Add Product",
                onButtonClick = {navController.navigate("add_product")}
            )
        }
    }
}
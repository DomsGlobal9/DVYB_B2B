package com.example.dvybb2b.home.products

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dvybb2b.model.Product.Products
import com.example.dvybb2b.utils.loadImageFromAssets
import com.example.dvybb2b.viewmodel.Products.CategoryProductsViewModel


@Composable
fun CategoryProductScreen(category: String, viewModel: CategoryProductsViewModel = viewModel()) {
    val products by viewModel.products.collectAsState()

    LaunchedEffect(category) {
        viewModel.loadProductsForCategory(category)
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = category.replace("_", " > "), style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(12.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(products) { products ->
                ProductCard(products = products)
            }
        }
    }
}

@Composable
fun ProductCard(products: Products) {
    val context = LocalContext.current
    val imageBitmap = loadImageFromAssets(context, products.imageAssetPath)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.8f),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            imageBitmap?.let {
                Image(
                    bitmap = it,
                    contentDescription = products.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = products.title, style = MaterialTheme.typography.bodyMedium)
            Text(text = products.price, style = MaterialTheme.typography.labelLarge)
            Text(text = products.reviews, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
        }
    }
}


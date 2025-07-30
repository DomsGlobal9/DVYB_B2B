package com.example.dvybb2b.home.products

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.dvybb2b.R
import com.example.dvybb2b.model.Product.RecentlyAddedProduct
import com.example.dvybb2b.viewmodel.Products.RecentlyAddedProductsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecentlyAddedProductsScreen(
    gender: String,
    navController: NavController,
    viewModel: RecentlyAddedProductsViewModel = remember { RecentlyAddedProductsViewModel() }
) {
    LaunchedEffect(Unit) {
        viewModel.updateGender(gender)
    }



            Spacer(Modifier.height(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(bottom = 100.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(viewModel.filteredProducts) { product ->
                    RecentlyAddedProductCard(product)
                }
            }
        }

//}


@Composable
fun RecentlyAddedProductCard(product: RecentlyAddedProduct) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(0.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(product.imageUrl),
            contentDescription = product.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .background(Color.Gray, RoundedCornerShape(8.dp))
        )

        Spacer(Modifier.height(8.dp))
        Text(product.title, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(2.dp))
        Row {
            Image(
                painter = painterResource(id = R.drawable.productcardicon01),
                contentDescription = "Qty Available",
                modifier = Modifier
                    .size(20.dp).padding(2.dp)
            )
            Text("Qty Available: ${product.quantity}", fontSize = 12.sp)
        }

        Row {

            Image(
                painter = painterResource(id = R.drawable.productcardicon02),
                contentDescription = "Qty Available",
                modifier = Modifier.size(20.dp).padding(2.dp)
            )

            Text("Units Sold: ${product.sold}", fontSize = 12.sp)
        }

    }
}
package com.example.dvybb2b.home.products


import androidx.compose.runtime.getValue
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dvybb2b.home.products.components.InventoryByColorCard
import com.example.dvybb2b.home.products.components.ProductOverviewCard
import com.example.dvybb2b.home.products.components.ReadyToPublishCard
import com.example.dvybb2b.home.products.components.VariantsInventoryCard
import com.example.dvybb2b.viewmodel.DressPreviewViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreviewScreen(
    navController: NavController,
    productId: String?,
    viewModel: DressPreviewViewModel = viewModel()
) {
    val scrollState = rememberScrollState()

    // 🟡 Load the product when productId is available
    LaunchedEffect(productId) {
        productId?.let {
            viewModel.loadDressById(it) // Custom function you define in the ViewModel
        }
    }

    val selectedDress by viewModel.selectedDress

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        ProductOverviewCard()

        Spacer(modifier = Modifier.height(16.dp))

        VariantsInventoryCard()

        Spacer(modifier = Modifier.height(16.dp))

        InventoryByColorCard()

        Spacer(modifier = Modifier.height(16.dp))

        selectedDress?.let { dress ->
            ReadyToPublishCard(navController = navController, selectedDress = dress)
        }

    }
}



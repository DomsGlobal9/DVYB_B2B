package com.example.dvybb2b.home.products


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dvybb2b.home.products.components.SizeAndPriceScreen
import com.example.dvybb2b.viewmodel.Products.AddProductViewModel


// ---------- MAIN COMPOSABLE ----------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(navController: NavController,viewModel: AddProductViewModel = viewModel()) {
    val tabTitles = listOf("General", "Pricing", "Upload")
    val selectedTabIndex by viewModel.currentTab.collectAsState()
    val product by viewModel.product.collectAsState()
    val scrollState = rememberScrollState()
    val productTypes = listOf("Ready to Wear", "Unstitched")
    val dressTypes = listOf("Kurti", "Gown", "Frock")
    val materialTypes = listOf("Cotton", "Lawn", "Silk")
    val designTypes = listOf("Floral", "Geometric", "Plain")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 8.dp)
            .verticalScroll(scrollState)
    ) {
        TopAppBar(
            title = { Text("Add New Product", fontWeight = FontWeight.Bold) },
            navigationIcon = {
                IconButton(onClick = { /* Back logic */ }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
        )

        TabRow(selectedTabIndex = selectedTabIndex) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { viewModel.setTab(index) },
                    text = {
                        Text(
                            text = title,
                            fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal,
                            color = if (selectedTabIndex == index) Color(0xFF61A9C6) else Color.Black
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        when (selectedTabIndex) {
            0 -> {
                Text("Product Information", fontWeight = FontWeight.Medium, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(16.dp))

                DropdownField(
                    label = "Choose Type",
                    options = listOf("Men", "Women", "Kids"),
                    selected = product.category,
                    onValueChange = { viewModel.updateCategory(it) }
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text("Product Type", fontWeight = FontWeight.Medium)

                Row(modifier = Modifier.padding(vertical = 8.dp)) {
                    productTypes.forEach { type ->
                        val isSelected = product.productType == type
                        Button(
                            onClick = { viewModel.updateProductType(type) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isSelected) Color(0xFF61A9C6) else Color(0xFFF0F0F0)
                            ),
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .height(36.dp)
                        ) {
                            Text(
                                text = type,
                                color = if (isSelected) Color.White else Color.Black
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Text("Select Dress", fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(10.dp))

                DropdownField(
                    label = "Select Dress",
                    options = dressTypes,
                    selected = product.dressType,
                    onValueChange = { viewModel.updateDressType(it) }
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text("Select Material", fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(10.dp))

                DropdownField(
                    label = "Select Material",
                    options = materialTypes,
                    selected = product.material,
                    onValueChange = { viewModel.updateMaterial(it) }
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text("Select Design", fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(10.dp))

                DropdownField(
                    label = "Select Design",
                    options = designTypes,
                    selected = product.design,
                    onValueChange = { viewModel.updateDesign(it) }
                )

                Spacer(modifier = Modifier.height(34.dp))
                Button(
                    onClick = { viewModel.nextTab() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF61A9C6)),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(Icons.Default.ArrowForward, contentDescription = null, tint = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Next", color = Color.White)
                }
            }

            1 -> {
                SizeAndPriceScreen()

                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = { viewModel.nextTab() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF61A9C6)),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(Icons.Default.ArrowForward, contentDescription = null, tint = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Next", color = Color.White)
                }
            }

            2 -> {
                UploadSection(
                    onBackClick = { viewModel.previousTab() },
                    onNextClick = { navController.navigate("product_preview/1") } // change
                )
            }
        }
    }
}

// ---------- DROPDOWN FIELD ----------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownField(
    label: String,
    options: List<String>,
    selected: String,
    onValueChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selected,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
        )

        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { selection ->
                DropdownMenuItem(
                    text = { Text(selection) },
                    onClick = {
                        onValueChange(selection)
                        expanded = false
                    }
                )
            }
        }
    }
}

// ---------- UPLOAD SECTION ----------
@Composable
fun UploadSection(onBackClick: () -> Unit, onNextClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text("Upload Photos", style = MaterialTheme.typography.titleMedium)
        Text("Add a Photo of your product", color = Color.Gray)

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                .clickable { /* Open image picker */ },
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(Icons.Default.CameraAlt, contentDescription = null, tint = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Add photos", color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text("Capture Instruction’s", style = MaterialTheme.typography.titleSmall)

        val instructions = listOf(
            "Click 'Upload Photo' beside the product name.",
            "Include image details in the description.",
            "Set the price before uploading the image.",
            "If multiple images are allowed, specify the number.",
            "Select the right category for your image.",
            "Choose the brand linked to the product image."
        )

        instructions.forEach {
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text("• ", fontWeight = FontWeight.Bold)
                Text(it, color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = onBackClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
            ) {
                Text("Back")
            }

            Button(
                onClick = onNextClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3AB7FF))
            ) {
                Text("Next")
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.Default.ArrowForward, contentDescription = null)
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun AddProductPreview() {
//    AddProductScreen( navController = NavController() )
//}
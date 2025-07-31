package com.example.dvybb2b.home.products.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.theme.AddOneProduct.ViewModel.SizeAndPriceViewModel


@Composable
fun SizeAndPriceScreen(viewModel: SizeAndPriceViewModel = viewModel()) {
    val sizes = viewModel.allSizes
    val colors = viewModel.allColors

    // Calculate total units entered (sum all units in the map)
    val totalUnits = remember(viewModel.units) {
        viewModel.units.values.sumOf { sizeMap ->
            sizeMap.values.mapNotNull { it.toIntOrNull() }.sum()
        }
    }

//    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
//            .verticalScroll(scrollState)
    ) {
        Spacer(Modifier.height(8.dp))
        Text(
            "Select Sizes",
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        )
        Spacer(Modifier.height(14.dp))
        SizeSelector(
            sizes = sizes,
            selected = viewModel.selectedSizes,
            onToggle = viewModel::toggleSize
        )
        Spacer(Modifier.height(16.dp))
        Text(
            "*You can select multiple sizes",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 10.sp
            )
        )

        Spacer(Modifier.height(16.dp))
        Text(
            "Price",
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        )

        TextField(
            value = viewModel.price,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() }) {
                    viewModel.onPriceChange(newValue)
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
            )
        )

        Spacer(Modifier.height(16.dp))
        Text(
            "Select Colors",
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        )
        Spacer(Modifier.height(16.dp))
        Text(
            "Color",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 14.sp
            )
        )
        ColorDropdownSelector(
            availableColors = colors,
            selectedColors = viewModel.selectedColors,
            onAddColor = viewModel::addColor
        )

        Spacer(Modifier.height(16.dp))

        if (viewModel.selectedSizes.isNotEmpty() && viewModel.selectedColors.isNotEmpty()) {
            Text(
                "Color",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 16.sp
                )
            )
            UnitsInput(
                sizes = sizes.filter { viewModel.selectedSizes.contains(it) },
                selectedColors = viewModel.selectedColors,
                units = viewModel.units,
                onValueChange = viewModel::setUnit,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { /* TODO: Handle back navigation */ },
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, Color.Black, shape = MaterialTheme.shapes.small),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.width(8.dp))
                Text("Back", color = MaterialTheme.colorScheme.primary)
            }
            // Next button width grows with totalUnits, capped max width for usability:
            Button(
                onClick = { /* TODO: Handle next action */ },
                modifier = Modifier
                    .weight(1f),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF87CEEB)) // Sky Blue background
            ) {
                Text("Next", color = Color.White)
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Next",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun SizeSelector(
    sizes: List<String>,
    selected: Set<String>,
    onToggle: (String) -> Unit
) {
    Row {
        sizes.forEach { size ->
            val isSelected = selected.contains(size)
            OutlinedButton(
                onClick = { onToggle(size) },
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (isSelected) Color(0xFF5BAAF9) else Color.LightGray
                ),
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text(size, color = Color.White)
            }
        }
    }
}


@Composable
fun ColorDropdownSelector(
    availableColors: List<String>,
    selectedColors: List<String>,
    onAddColor: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedColorText by remember { mutableStateOf("") }

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(Color.LightGray.copy(alpha = 0.3f), shape = MaterialTheme.shapes.small)
                .clickable { expanded = !expanded }
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = if (selectedColorText.isEmpty()) "Select color" else selectedColorText,
                    color = if (selectedColorText.isEmpty()) Color.Gray else Color.Black
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown arrow",
                    tint = Color.Gray
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            availableColors.forEach { colorName ->
                DropdownMenuItem(
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(16.dp)
                                    .clip(CircleShape)
                                    .background(chooseColor(colorName))
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(colorName, color = Color.Black)
                        }
                    },
                    onClick = {
                        selectedColorText = colorName
                        expanded = false
                        onAddColor(colorName)
                    }
                )
            }
        }

        // Show selected colors as Assist Chips
        Row(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            selectedColors.forEach { colorSelected ->
                AssistChip(
                    onClick = { /* Optional: implement removal if needed */ },
                    label = {
                        Text(colorSelected, color = Color.White)
                    },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = chooseColor(colorSelected)
                    )
                )
            }
        }
    }
}

fun chooseColor(name: String): Color = when (name) {
    "Red" -> Color(0xFFD32F2F)
    "Pastel" -> Color(0xFFFFC1CC)
    "Blue" -> Color(0xFF4079AB)
    "Dark" -> Color(0xFF880808)
    else -> Color.LightGray
}


@Composable
fun UnitsInput(
    sizes: List<String>,
    selectedColors: List<String>,
    units: Map<String, Map<String, String>>,
    onValueChange: (String, String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        // Header row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Size", modifier = Modifier.weight(1f))
            Text("Color", modifier = Modifier.weight(1f))
            Text("Units", modifier = Modifier.weight(1f))
        }

        // Rows for each size-color combination
        for (color in selectedColors) {
            for (size in sizes) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    // Size column
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(Color(0xFFF1F1F1), shape = MaterialTheme.shapes.small)
                            .padding(vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = size)
                    }

                    // Color column with full background and white text
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(chooseColor(color), shape = MaterialTheme.shapes.small)
                            .padding(vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = color,
                            color = Color.White
                        )
                    }

                    // Units input column
                    OutlinedTextField(
                        value = units[color]?.get(size) ?: "",
                        onValueChange = { onValue -> onValueChange(size, color, onValue) },
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        singleLine = true,
                        label = { Text("e.g 22") },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    )
                }
            }
        }
    }
}

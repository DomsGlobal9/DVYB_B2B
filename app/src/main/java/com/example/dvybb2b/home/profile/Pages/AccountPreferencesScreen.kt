package com.example.dvyb.ui.theme.home.profile.pages

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AccountPreferencesScreen(
    viewModel: AccountPreferencesViewModel = viewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Header with back button and edit toggle
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable { /* Handle back */ }
                )
                Text("Account Preferences", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }

            if (!state.isInitialSelection) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        viewModel.toggleEditable()
                        if (!state.isEditable) {
                            Toast.makeText(context, "Changes saved!", Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(if (state.isEditable) "Done" else "Edit", fontSize = 16.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Category dropdown (single selection)
        SingleSelectionDropdown(
            title = "Category",
            options = viewModel.categories,
            selectedOption = state.selectedCategory,
            showOptions = state.showCategoryOptions,
            onDropdownClick = { viewModel.toggleDropdown("category") },
            onOptionSelected = { viewModel.setCategory(it) },
            enabled = state.isInitialSelection || state.isEditable
        )


        // Update the MultiSelectionDropdown calls to pass the isEditable state
        MultiSelectionDropdown(
            title = "All Type",
            options = viewModel.types,
            selectedOptions = state.selectedTypes,
            showOptions = state.showTypeOptions,
            onDropdownClick = {
                if (state.isInitialSelection || state.isEditable)
                    viewModel.toggleDropdown("type")
                else Toast.makeText(context, "Click Edit to modify preferences", Toast.LENGTH_SHORT).show()
            },
            onOptionSelected = { viewModel.setTypes(it) },
            enabled = state.isInitialSelection || state.isEditable,
            isEditable = state.isEditable
        )

        MultiSelectionDropdown(
            title = "Dress Type",
            options = viewModel.dressTypes,
            selectedOptions = state.selectedDresses,
            showOptions = state.showDressOptions,
            onDropdownClick = {
                if (state.isInitialSelection || state.isEditable)
                    viewModel.toggleDropdown("dress")
                else Toast.makeText(context, "Click Edit to modify preferences", Toast.LENGTH_SHORT).show()
            },
            onOptionSelected = { viewModel.setDresses(it) },
            enabled = state.isInitialSelection || state.isEditable,
            isEditable = state.isEditable
        )

        MultiSelectionDropdown(
            title = "Material Type",
            options = viewModel.materials,
            selectedOptions = state.selectedMaterials,
            showOptions = state.showMaterialOptions,
            onDropdownClick = {
                if (state.isInitialSelection || state.isEditable)
                    viewModel.toggleDropdown("material")
                else Toast.makeText(context, "Click Edit to modify preferences", Toast.LENGTH_SHORT).show()
            },
            onOptionSelected = { viewModel.setMaterials(it) },
            enabled = state.isInitialSelection || state.isEditable,
            isEditable = state.isEditable
        )
        if (state.isEditable) {
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    viewModel.setEditable(false)
                    Toast.makeText(context, "Changes saved!", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(117, 180, 203)
                )
            ) {
                Text("Save Changes", fontSize = 16.sp,color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(80.dp))
    }
}

@Composable
fun SingleSelectionDropdown(
    title: String,
    options: List<String>,
    selectedOption: String?,
    showOptions: Boolean,
    onDropdownClick: () -> Unit,
    onOptionSelected: (String) -> Unit,
    enabled: Boolean = true
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = title, fontWeight = FontWeight.SemiBold)

        OutlinedButton(
            onClick = { if (enabled) onDropdownClick() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
            enabled = enabled
        ) {
            Text(
                text = selectedOption ?: "Select ${title.lowercase()}",
                color = if (selectedOption == null) Color.Gray else Color.Black
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
        }

        if (showOptions && enabled) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFFF3F3F3))
                    .padding(vertical = 4.dp)
            ) {
                options.forEach { option ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(if (option == selectedOption) Color.Gray else Color.LightGray)
                            .clickable { onOptionSelected(option) }
                            .padding(12.dp)
                    ) {
                        Text(text = option)
                    }
                }
            }
        }

        selectedOption?.let {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFFF3F3F3))
                    .padding(12.dp)
            ) {
                Text(text = it, fontWeight = FontWeight.Medium)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}


@Composable
fun MultiSelectionDropdown(
    title: String,
    options: List<String>,
    selectedOptions: List<String>,
    showOptions: Boolean,
    onDropdownClick: () -> Unit,
    onOptionSelected: (List<String>) -> Unit,
    enabled: Boolean = true,
    isEditable: Boolean = false
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = title, fontWeight = FontWeight.SemiBold)

        OutlinedButton(
            onClick = { if (enabled) onDropdownClick() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
            enabled = enabled
        ) {
            Text(
                text = if (selectedOptions.isEmpty()) "Select ${title.lowercase()}"
                else "${selectedOptions.size} selected",
                color = if (selectedOptions.isEmpty()) Color.Gray else Color.Black
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
        }

        if (showOptions && enabled) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFFF3F3F3))
                    .padding(vertical = 4.dp)
            ) {
                options.forEach { option ->
                    val isSelected = selectedOptions.contains(option)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(if (isSelected) Color.Gray else Color.LightGray)
                            .clickable {
                                val updated = if (isSelected) {
                                    selectedOptions - option
                                } else {
                                    selectedOptions + option
                                }
                                onOptionSelected(updated)
                            }
                            .padding(12.dp)
                    ) {
                        Text(text = option)
                    }
                }
            }
        }

        if (selectedOptions.isNotEmpty()) {
            selectedOptions.forEach { item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(0xFFF3F3F3))
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = item,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .weight(1f)
                                .padding(12.dp)
                        )

                        if (isEditable) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Remove",
                                modifier = Modifier
                                    .padding(end = 12.dp)
                                    .clickable {
                                        onOptionSelected(selectedOptions - item)
                                    },
                                tint = Color.Black
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}


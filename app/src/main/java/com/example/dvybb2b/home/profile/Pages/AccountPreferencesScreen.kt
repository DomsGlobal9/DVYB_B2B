package com.example.dvybb2b.home.profile.Pages

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
import androidx.navigation.NavController
import com.example.dvybb2b.home.profile.Components.MultiSelectionDropdown
import com.example.dvybb2b.home.profile.Components.SingleSelectionDropdown
import com.example.dvybb2b.viewmodel.Profile.pages.AccountPreferencesViewModel


@Composable
fun AccountPreferencesScreen(navController: NavController,viewModel: AccountPreferencesViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    LaunchedEffect(viewModel.isInitialSelection) {
        if (viewModel.isInitialSelection) {
            viewModel.saveInitialPreferences()
            if (!viewModel.isInitialSelection) {
                Toast.makeText(context, "Preferences saved!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,

        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable { }
                )
                Text("Account Preferences", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }

            if (!viewModel.isInitialSelection) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        viewModel.toggleEditMode()
                        if (!viewModel.isEditable) {
                            Toast.makeText(context, "Changes saved!", Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(if (viewModel.isEditable) "Done" else "Edit", fontSize = 16.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        SingleSelectionDropdown(
            title = "Category",
            options = viewModel.categories,
            selectedOption = viewModel.selectedCategory,
            showOptions = viewModel.showCategoryOptions,
            onDropdownClick = { viewModel.showCategoryOptions = !viewModel.showCategoryOptions },
            onOptionSelected = {
                viewModel.selectedCategory = it
                viewModel.showCategoryOptions = false
                Toast.makeText(context, "$it selected", Toast.LENGTH_SHORT).show()
            },
            enabled = viewModel.isInitialSelection || viewModel.isEditable
        )

        MultiSelectionDropdown(
            title = "All Type",
            options = viewModel.types,
            selectedOptions = viewModel.selectedTypes,
            showOptions = viewModel.showTypeOptions,
            onDropdownClick = {
                if (viewModel.isInitialSelection || viewModel.isEditable)
                    viewModel.showTypeOptions = !viewModel.showTypeOptions
                else Toast.makeText(context, "Click Edit to modify preferences", Toast.LENGTH_SHORT).show()
            },
            onOptionSelected = { viewModel.selectedTypes = it },
            enabled = viewModel.isInitialSelection || viewModel.isEditable
        )

        MultiSelectionDropdown(
            title = "Dress Type",
            options = viewModel.dressTypes,
            selectedOptions = viewModel.selectedDresses,
            showOptions = viewModel.showDressOptions,
            onDropdownClick = {
                if (viewModel.isInitialSelection || viewModel.isEditable)
                    viewModel.showDressOptions = !viewModel.showDressOptions
                else Toast.makeText(context, "Click Edit to modify preferences", Toast.LENGTH_SHORT).show()
            },
            onOptionSelected = { viewModel.selectedDresses = it },
            enabled = viewModel.isInitialSelection || viewModel.isEditable
        )

        MultiSelectionDropdown(
            title = "Material Type",
            options = viewModel.materials,
            selectedOptions = viewModel.selectedMaterials,
            showOptions = viewModel.showMaterialOptions,
            onDropdownClick = {
                if (viewModel.isInitialSelection || viewModel.isEditable)
                    viewModel.showMaterialOptions = !viewModel.showMaterialOptions
                else Toast.makeText(context, "Click Edit to modify preferences", Toast.LENGTH_SHORT).show()
            },
            onOptionSelected = { viewModel.selectedMaterials = it },
            enabled = viewModel.isInitialSelection || viewModel.isEditable
        )

        if (viewModel.isEditable) {
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    viewModel.saveChanges()
                    Toast.makeText(context, "Changes saved!", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Text("Save Changes", fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.height(80.dp))
    }
}

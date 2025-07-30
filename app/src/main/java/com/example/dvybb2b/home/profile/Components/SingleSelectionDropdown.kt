package com.example.dvybb2b.home.profile.Components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.draw.clip

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

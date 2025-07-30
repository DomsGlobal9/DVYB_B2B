package com.example.dvybb2b.home.products.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun VariantsInventoryCard() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Variants & Inventory", style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(12.dp))

            Text("Sizes", style = MaterialTheme.typography.bodySmall)
            Row {
                Chip("XS")
                Spacer(modifier = Modifier.width(8.dp))
                Chip("S")
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text("Colors", style = MaterialTheme.typography.bodySmall)
            Chip("Green")

            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                InfoBox("0", "Total Variants")
                InfoBox("0", "Images")
                InfoBox("0", "Est. Value")
            }
        }
    }
}

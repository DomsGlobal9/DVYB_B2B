package com.example.dvybb2b.home.products.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable



@Composable
fun ProductOverviewCard() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Product Overview", style = MaterialTheme.typography.titleSmall)

            Spacer(modifier = Modifier.height(8.dp))
            Text("Untitled Product", style = MaterialTheme.typography.titleMedium)
            Text("No Category", style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                InfoBox("$0", "Base Price")
                InfoBox("0", "Total Units")
            }
        }
    }
}

package com.example.dvybb2b.home.products.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow


@Composable
fun InfoBox(value: String, label: String) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(value, style = MaterialTheme.typography.titleMedium)
        Text(label, style = MaterialTheme.typography.bodySmall)
    }
}

//@Composable
//fun InfoBoxCard(value: String, label: String) {
//    Card(
//        modifier = Modifier
//            .shadow(6.dp, shape = RoundedCornerShape(12.dp)),
//        shape = RoundedCornerShape(12.dp),
//        colors = CardDefaults.cardColors(containerColor = Color.White)
//    ) {
//        Column(
//            modifier = Modifier
//                .padding(vertical = 20.dp)
//                .fillMaxWidth(),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(text = value, style = MaterialTheme.typography.titleMedium)
//            Text(text = label, style = MaterialTheme.typography.bodySmall)
//        }
//    }
//}


@Composable
fun Chip(text: String) {
    Box(
        modifier = Modifier
            .background(Color.LightGray, RoundedCornerShape(20.dp))
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(text = text, style = MaterialTheme.typography.bodySmall)
    }
}

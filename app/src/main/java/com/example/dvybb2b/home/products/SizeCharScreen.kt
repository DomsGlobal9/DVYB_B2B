package com.example.dvybb2b.home.products

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class SizeMeasurement(
    val size: String,
    val bust: Double,
    val waist: Double,
    val hip: Double
)

@Composable
fun SizeChartScreen() {
    var measurementUnit by remember { mutableStateOf("cm") } // "in" or "cm"

    val sizeChartData = listOf(
        SizeMeasurement("XXS", 70.2, 81.4, 74.0),
        SizeMeasurement("XS", 81.3, 83.5, 88.9),
        SizeMeasurement("S", 86.4, 88.6, 94.0),
        SizeMeasurement("M", 92.4, 73.7, 98.1),
        SizeMeasurement("L", 96.5, 78.7, 104.1),
        SizeMeasurement("XL", 101.6, 83.8, 109.2),
        SizeMeasurement("XXL", 106.7, 88.9, 114.3),
        SizeMeasurement("XXXL", 113.0, 95.2, 120.7),
        SizeMeasurement("XXXXL", 119.4, 101.6, 127.0)
    )

    Column(modifier = Modifier.padding(16.dp)) {
        // Title and Unit selection in single row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Size Chart",
                style = MaterialTheme.typography.headlineMedium.copy(fontSize = 20.sp),
                fontWeight = FontWeight.Bold
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
            ) {
                UnitButton(
                    text = "In",
                    isSelected = measurementUnit == "in",
                    onClick = { measurementUnit = "in" }
                )
                UnitButton(
                    text = "Cm",
                    isSelected = measurementUnit == "cm",
                    onClick = { measurementUnit = "cm" }
                )
            }
        }

        Spacer(modifier = Modifier.padding(8.dp))

        // Table header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .background(Color.LightGray.copy(alpha = 0.3f))
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Size", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f).padding(start = 8.dp))
            Text("Bust", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            Text("Waist", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            Text("Hip", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f).padding(end = 8.dp))
        }

        // Table content
        LazyColumn {
            items(sizeChartData) { size ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .background(
                            if (sizeChartData.indexOf(size) % 2 == 0)
                                Color.LightGray.copy(alpha = 0.1f)
                            else
                                Color.LightGray.copy(alpha = 0.2f)
                        )
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        size.size,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f).padding(start = 8.dp))
                    Text(
                        formatMeasurement(size.bust, measurementUnit),
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        formatMeasurement(size.waist, measurementUnit),
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        formatMeasurement(size.hip, measurementUnit),
                        modifier = Modifier.weight(1f).padding(end = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun UnitButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .background(
                if (isSelected) Color.Blue.copy(alpha = 0.2f) else Color.Transparent,
                RoundedCornerShape(4.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.Blue else Color.Gray,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

private fun formatMeasurement(value: Double, unit: String): String {
    return when (unit) {
        "in" -> String.format("%.1f", value / 2.54) // Convert to inches
        else -> String.format("%.1f", value) // Show original cm values
    }
}

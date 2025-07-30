package com.example.dvybb2b.viewmodel.register.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun ErrorText(errorMessage: String?, modifier: Modifier = Modifier) {
    if (!errorMessage.isNullOrBlank()) {
        Text(
            text = errorMessage,
            color = Color.Red,
            fontSize = 12.sp,
            style = MaterialTheme.typography.bodySmall,
            modifier = modifier
        )
    }
}

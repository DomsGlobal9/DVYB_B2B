package com.example.dvybb2b.home.products.components

import com.example.dvybb2b.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.foundation.shape.RoundedCornerShape


@Composable
fun EmptyState(
    title: String,
    subtitle: String,
    imageRes: Int,
    buttonText: String,
    onButtonClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Text(
            text = subtitle,
            fontSize = 14.sp,
            color = Color.Gray
        )

        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Empty State Image",
            modifier = Modifier
                .height(120.dp)
                .width(120.dp)
        )

        Button(
            onClick = onButtonClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF61A9C6)),
            shape = RoundedCornerShape(6.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.plus_icon),
                    contentDescription = "Add",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )

                Text(text = buttonText, color = Color.White)
            }
        }

    }
}
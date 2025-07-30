package com.example.dvybb2b.home.products.components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dvybb2b.model.Product.ProductDress


@Composable
fun ReadyToPublishCard( navController: NavController,
                        selectedDress: ProductDress
) {
    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text("✅   Ready to Publish", modifier = Modifier.padding(bottom = 5.dp), color = Color(0XFF065B0B), style = MaterialTheme.typography.titleSmall)
            Text("Your product will be live and available for customers to purchase", style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
            ) {
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp), // Optional: same height for uniform look
                    shape = RoundedCornerShape(10.dp),
                    onClick = { navController.navigate("product_dress_preview/${selectedDress.name}")

                    }
                ) {
                    Text(text = "Preview", fontWeight = FontWeight.Medium)
                }

                OutlinedButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(10.dp),
                    onClick = { }
                ) {
                    Text(text = "Edit", fontWeight = FontWeight.Medium)
                }
            }

        }
    }
}

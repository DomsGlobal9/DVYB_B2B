package com.example.dvybb2b.home.profile

import com.example.dvybb2b.viewmodel.Profile.ProfileViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.border
import com.example.dvybb2b.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dvybb2b.navigation.SupportScreens

@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel = viewModel()) {
    val userProfile by viewModel.userProfile

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp) // Optional horizontal padding
        ) {
            // Back Arrow - Aligned to Start
            Image(
                painter = painterResource(id = R.drawable.leftarrow),
                contentDescription = "Back Arrow",
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterStart)
                    .clickable {
                        // Handle back press
                    }
            )

            // Centered Text
            Text(
                text = userProfile.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(contentAlignment = Alignment.BottomEnd) {
            Image(
                painter = painterResource(id = userProfile.imageRes),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            // Edit Icon
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .offset(x = (-4).dp, y = (-4).dp)
                    .clip(CircleShape)
                    .background(Color(0xFFAA607F)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_edit),
                    contentDescription = "Edit Icon",
                    modifier = Modifier
                        .size(14.dp)
                )

            }
        }

        Spacer(modifier = Modifier.height(8.dp))


        Text(text = userProfile.phone, color = Color.Gray)

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .border(
                    width = 1.dp,
                    color = Color.Black.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable { navController.navigate("account_preference")  }
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Account Preferences")
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Arrow"
            )


        }
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .border(
                    width = 1.dp,
                    color = Color.Black.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable { navController.navigate("vendor_register") }
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Vendor Information")
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Arrow"
            )


        }
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .border(
                    width = 1.dp,
                    color = Color.Black.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable { navController.navigate("shop_details") }
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Shop Details")
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Arrow"
            )


        }
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .border(
                    width = 1.dp,
                    color = Color.Black.copy(alpha = 0.2f), // 👈 light black border
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable { navController.navigate("payout_settings") }
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Payout Settings")
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Arrow"
            )


        }
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .border(
                    width = 1.dp,
                    color = Color.Black.copy(alpha = 0.2f), // 👈 light black border
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable { /* Handle navigation */ }
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Ratings & Reviews")
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Arrow"
            )


        }
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .border(
                    width = 1.dp,
                    color = Color.Black.copy(alpha = 0.2f), // 👈 light black border
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 16.dp)
                .background(Color.White, RoundedCornerShape(12.dp))
                .clickable { navController.navigate(SupportScreens.Support.route) }
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Support")
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Arrow"
            )


        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFF42A5F5), // 👈 light black border
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable { /* Logout logic */ }
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Logout",
                color = Color(0xFF42A5F5), // Light blue
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
        }




    }
}
package com.example.dvybb2b.home.profile.Pages

import com.example.dvybb2b.viewmodel.Profile.pages.VendorInfoViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dvybb2b.R


@Composable
fun VendorRegisterScreen(navController: NavController,viewModel: VendorInfoViewModel = viewModel()) {
    val vendorInfo by viewModel.vendorInfo

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Spacer(modifier = Modifier.height(10.dp))


        Box(
            modifier = Modifier
                .fillMaxWidth()
            // Optional horizontal padding
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
                text = "Vendor Information" ,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .align(Alignment.Center),
                fontWeight = FontWeight.Bold
            )
        }


        Spacer(modifier = Modifier.height(18.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
//                .clickable { onClick() },
            horizontalArrangement = Arrangement.End, // aligns to the right
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.tabler_edit), // 🔁 Replace with your icon
                contentDescription = "Edit Icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "Edit",
                color = Color(0xFF0BA4E0),
                fontSize = 14.sp,
                modifier = Modifier.padding(end = 4.dp)
            )
        }


        Spacer(modifier = Modifier.height(18.dp))

        VendorInputField(
            value = vendorInfo.name,
            onValueChange = { viewModel.updateVendorName(it) },
            iconRes = R.drawable.ic_user,
            placeholder = "Enter Your Full Name"
        )
        VendorInputField(
            value = vendorInfo.phone,
            onValueChange = { viewModel.updateVendorPhone(it) },
            iconRes = R.drawable.ic_phone,
            placeholder = "Enter Your Mobile Number"
        )
        VendorInputField(
            value = vendorInfo.email,
            onValueChange = { viewModel.updateVendorEmail(it) },
            iconRes = R.drawable.ic_email,
            placeholder = "Enter Your Email"
        )
        VendorInputField(
            value = vendorInfo.password,
            onValueChange = { viewModel.updateVendorPassword(it) },
            iconRes = R.drawable.ic_password,
            placeholder = "Enter Your Password"
        )
        VendorInputField(
            value = vendorInfo.address,
            onValueChange = { viewModel.updateVendorAddress(it) },
            iconRes = R.drawable.ic_location,
            placeholder = "Enter Your Address"
        )
    }
}

@Composable
fun VendorInputField(
    value: String,
    onValueChange: (String) -> Unit,
    iconRes: Int,
    placeholder: String,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 4.dp)
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(20.dp)
            )

            TextField(
                value = value,
                onValueChange = onValueChange,
                placeholder = {
                    Text(
                        text = placeholder,
                        color = Color.Gray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                singleLine = true,

                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color(0xFF0BA4E0),  // light blue underline
                    unfocusedIndicatorColor = Color.LightGray, // gray underline
                    disabledIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    cursorColor = Color(0xFF0BA4E0),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )
        }
    }
}
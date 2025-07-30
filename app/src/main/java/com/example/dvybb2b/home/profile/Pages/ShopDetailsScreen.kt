package com.example.dvybb2b.home.profile.Pages

import com.example.dvybb2b.viewmodel.Profile.pages.ShopInfoViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dvybb2b.R


@Composable
fun ShopDetailsScreen(navController: NavController,viewModel: ShopInfoViewModel = viewModel()) {
    // UI code goes here


    val shopInfo by viewModel.shopdetailsInfo

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Spacer(modifier = Modifier.height(10.dp))

        // Top Bar
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.leftarrow),
                contentDescription = "Back Arrow",
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterStart)
                    .clickable {
                        // Back navigation logic
                    }
            )

            Text(
                text = "Shop Details",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Center),
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Edit Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.tabler_edit),
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

        ShopInputField(
            value = shopInfo.Shopname,
            onValueChange = { viewModel.updateShopname(it) },
            iconRes = R.drawable.ic_user,
            placeholder = "Enter Shop Name"
        )

        ShopInputField(
            value = shopInfo.GSTINnum,
            onValueChange = { viewModel.updateShopGSTINnum(it) },
            iconRes = R.drawable.ic_dashboard, // Replace with GSTIN icon if you have
            placeholder = "Enter GSTIN Number"
        )

        ShopInputField(
            value = shopInfo.PANnum,
            onValueChange = { viewModel.updateShopPANnum(it) },
            iconRes = R.drawable.ic_dashboard, // Replace with PAN icon if you have
            placeholder = "Enter PAN Number"
        )

        ShopInputField(
            value = shopInfo.Address,
            onValueChange = { viewModel.updateShopAddress(it) },
            iconRes = R.drawable.ic_location,
            placeholder = "Enter Shop Address"
        )

        ShopInputField(
            value = shopInfo.PINcode,
            onValueChange = { viewModel.updateShopPINcode(it) },
            iconRes = R.drawable.ic_dashboard, // Replace with pin icon if you have
            placeholder = "Enter PIN Code"
        )

        ShopInputField(
            value = shopInfo.BusinessType,
            onValueChange = { viewModel.updateShopBusinessType(it) },
            iconRes = R.drawable.ic_dashboard, // Replace with business icon if needed
            placeholder = "Enter Business Type"
        )
    }
}


@Composable
fun ShopInputField(
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
                    focusedIndicatorColor = Color(0xFF0BA4E0),
                    unfocusedIndicatorColor = Color.LightGray,
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
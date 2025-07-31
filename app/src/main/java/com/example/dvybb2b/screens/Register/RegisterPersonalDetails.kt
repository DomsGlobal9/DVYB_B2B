
package com.example.dvybb2b.screens.Register

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Help
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.dvybb2b.R
import com.example.dvybb2b.viewmodel.register.RegisterViewModel


@Composable
fun RegisterPersonalDetails(
    navController: NavHostController,
    viewModel: RegisterViewModel = viewModel()
) {

    val context = LocalContext.current

    val name by viewModel.name.collectAsState()
    val phone by viewModel.phone.collectAsState()
    val state by viewModel.state.collectAsState()

    val city by viewModel.city.collectAsState()
    val address by viewModel.address.collectAsState()

    var expanded by remember { mutableStateOf(false) }
//    val stateList = listOf("Andhra Pradesh", "Karnataka", "Tamil Nadu", "Telangana", "Kerala")
    val allStates = listOf(
        "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa",
        "Gujarat", "Haryana", "Himachal Pradesh", "Jharkhand", "Karnataka", "Kerala",
        "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland",
        "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana", "Tripura",
        "Uttar Pradesh", "Uttarakhand", "West Bengal", "Andaman and Nicobar Islands",
        "Chandigarh", "Dadra and Nagar Haveli and Daman and Diu", "Delhi",
        "Jammu and Kashmir", "Ladakh", "Lakshadweep", "Puducherry"
    )

    val filteredStates = remember(state) {
        if (state.isBlank()) allStates
        else allStates.filter { it.startsWith(state, ignoreCase=true)}}
    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.bg_top),
            contentDescription = "Top Background",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .align(Alignment.TopCenter)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(80.dp)
            )

            Text(
                text = "Register",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF004D61),
                modifier = Modifier.padding(top = 36.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
            StepIndicator()
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "1  Personal details",
                color = Color(0xFF008CBD),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(12.dp))

            UnderlinedTextField(value = name, onValueChange = viewModel::onNameChange, label = "Enter Your full name")
            Spacer(modifier = Modifier.height(12.dp))

            UnderlinedTextField(value = phone, onValueChange = viewModel::onPhoneChange, label = "Enter 10 digit mobile number")
            Spacer(modifier = Modifier.height(12.dp))

            // State Dropdown

            Box(modifier = Modifier.fillMaxWidth()) {
                UnderlinedTextField(
                    value = state,
                    onValueChange = {
                        viewModel.onStateChange(it)
                        expanded = true
                    },
                    label = "State",
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Dropdown",
                            modifier = Modifier.clickable { expanded = true }
                        )
                    },
                    onClick = { expanded = true }
                )

                DropdownMenu(
                    expanded = expanded && filteredStates.isNotEmpty(),
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth(0.95f)
                ) {
                    filteredStates.forEach {
                        DropdownMenuItem(
                            onClick = {
                                viewModel.onStateChange(it)
                                expanded = false
                            },
                            text = {
                                Text(
                                    text = it,
                                    color = Color.Black,
                                    fontSize = 14.sp
                                )
                            }
                        )
                    }
                }
            }


            Spacer(modifier = Modifier.height(12.dp))
            UnderlinedTextField(value = city, onValueChange = viewModel::onCityChange, label = "City")
            Spacer(modifier = Modifier.height(12.dp))
            UnderlinedTextField(value = address, onValueChange = viewModel::onAddressChange, label = "Address")

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    viewModel.saveVendorToFirebase { success ->
                        if (success) {
                            Toast.makeText(context, "Vendor saved successfully", Toast.LENGTH_SHORT).show()
                            navController.navigate("shopDetails")
                        } else {
                            Toast.makeText(context, "Failed to save vendor", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF008CBD))
            ) {
                Text("Next", color = Color.White)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Need help?")
                Icon(
                    imageVector = Icons.Filled.Help,
                    contentDescription = "Help",
                    tint = Color(0xFF008CBD),
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun StepIndicator() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(1f)
    ) {
        listOf(0xFF008CBD, 0xFFB0BEC5, 0xFFB0BEC5,0xFFB0BEC5).forEach { colorHex ->
            Box(
                modifier = Modifier
                    .height(4.dp)
                    .weight(2f)
                    .background(Color(colorHex))
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}

@Composable
fun UnderlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    readOnly: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, fontSize = 14.sp, color = Color.Gray) },
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = readOnly && onClick != null) { onClick?.invoke() },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color(0xFF008CBD),
            unfocusedIndicatorColor = Color.Gray,
            disabledIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        ),
        singleLine = true,
        readOnly = readOnly,
        trailingIcon = trailingIcon
    )
}

package com.example.dvybb2b.ui.theme.screens.register

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dvybb2b.R
import com.example.dvybb2b.screens.Register.Components.UnderlinedTextField
import com.example.dvybb2b.viewmodel.register.RegisterShopDetailsViewModel

@Composable
fun RegisterShopDetailsScreen(
    navController: NavController,
    viewModel: RegisterShopDetailsViewModel = viewModel()
) {
    val context = LocalContext.current
    val shopState by viewModel.shopDetails.collectAsState()
    val stateList = listOf("Andhra Pradesh", "Karnataka", "Tamil Nadu", "Telangana", "Kerala")
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(80.dp)
                .padding(top = 32.dp)
        )

        Text("Register", fontSize = 22.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(vertical = 12.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            repeat(4) { index ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(3.dp)
                        .padding(horizontal = 2.dp)
                        .background(if (index <= 1) Color(0xFF008CBD) else Color.LightGray)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Surface(shape = CircleShape, color = Color(0xFF008CBD), modifier = Modifier.size(24.dp)) {
                Box(contentAlignment = Alignment.Center) {
                    Text("2", color = Color.White, fontSize = 12.sp)
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text("Shop Details", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text("Enter shop name", color = Color.Gray, fontSize = 12.sp)
        UnderlinedTextField(
            value = shopState.shopName,
            onValueChange = { newValue -> viewModel.updateField { it.copy(shopName = newValue) } },
            label = ""
        )

        Spacer(modifier = Modifier.height(12.dp))
        UnderlinedTextField(
            value = shopState.address,
            onValueChange = { newValue -> viewModel.updateField { it.copy(address = newValue) } },
            label = "Enter shop address"
        )

        Spacer(modifier = Modifier.height(12.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            UnderlinedTextField(
                value = shopState.state,
                onValueChange = {},
                label = "Select State",
                readOnly = true,
                trailingIcon = { Icon(Icons.Filled.ArrowDropDown, contentDescription = null) },
                onClick = { expanded = true }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                stateList.forEach {
                    DropdownMenuItem(
                        text = { Text(it) },
                        onClick = {
                            viewModel.updateField { old -> old.copy(state = it) }
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        UnderlinedTextField(
            value = shopState.city,
            onValueChange = { newValue -> viewModel.updateField { it.copy(city = newValue) } },
            label = "City"
        )

        Spacer(modifier = Modifier.height(12.dp))
        UnderlinedTextField(
            value = shopState.pincode,
            onValueChange = { newValue -> viewModel.updateField { it.copy(pincode = newValue) } },
            label = "Pincode",
            keyboardType = KeyboardType.Number
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text("GST Details", fontWeight = FontWeight.Bold, fontSize = 14.sp)

        Spacer(modifier = Modifier.height(12.dp))
        UnderlinedTextField(
            value = shopState.gstin,
            onValueChange = { newValue -> viewModel.updateField { it.copy(gstin = newValue) } },
            label = "GSTIN",
            readOnly = shopState.noGst
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = shopState.noGst,
                onCheckedChange = { isChecked ->
                    viewModel.updateField { it.copy(noGst = isChecked) }
                }
            )
            Text("I Don’t have GST")
        }

        UnderlinedTextField(
            value = shopState.pan,
            onValueChange = { newValue -> viewModel.updateField { it.copy(pan = newValue) } },
            label = "PAN"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                onClick = {
                    Toast.makeText(context, "Back Pressed", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
            ) {
                Text("Back")
            }

            Spacer(modifier = Modifier.width(12.dp))

            Button(
                onClick = {
                    viewModel.saveShopDetails(
                        onSuccess = { navController.navigate("bankDetails") },
                        onError = { msg -> Toast.makeText(context, msg, Toast.LENGTH_SHORT).show() }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF008CBD))
            ) {
                Text("Next", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Need help?", color = Color.Black, fontSize = 12.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                Icons.Default.ArrowDropDown,
                contentDescription = "Help",
                tint = Color(0xFF6B9FAF),
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

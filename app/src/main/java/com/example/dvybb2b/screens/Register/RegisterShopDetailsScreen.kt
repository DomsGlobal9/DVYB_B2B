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
import androidx.navigation.NavController
import com.example.dvybb2b.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnderlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    readOnly: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { if (label.isNotEmpty()) Text(label) },
        readOnly = readOnly,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = readOnly && onClick != null) {
                onClick?.invoke()
            },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        trailingIcon = trailingIcon,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color(0xFF008CBD),
            unfocusedIndicatorColor = Color.Gray,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        ),
        singleLine = true
    )
}

@Composable
fun RegisterShopDetailsScreen(navController: NavController) {
    val context = LocalContext.current

    var shopName by remember { mutableStateOf("Mehta Cloth store") }
    var address by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var pincode by remember { mutableStateOf("") }
    var gstin by remember { mutableStateOf("") }
    var pan by remember { mutableStateOf("") }
    var noGst by remember { mutableStateOf(false) }

    val stateList = listOf("Andhra Pradesh", "Karnataka", "Tamil Nadu", "Telangana", "Kerala")
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(80.dp)
                .padding(top = 32.dp)
        )

        Text(
            text = "Register",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 12.dp)
        )

        // Step Indicator
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

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Surface(
                shape = CircleShape,
                color = Color(0xFF008CBD),
                modifier = Modifier.size(24.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text("2", color = Color.White, fontSize = 12.sp)
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text("Shop Details", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text("Enter shop name", color = Color.Gray, fontSize = 12.sp)
        UnderlinedTextField(value = shopName, onValueChange = { shopName = it }, label = "")

        Spacer(modifier = Modifier.height(12.dp))
        UnderlinedTextField(value = address, onValueChange = { address = it }, label = "Enter shop address")

        Spacer(modifier = Modifier.height(12.dp))

        // Dropdown for State
        Box(modifier = Modifier.fillMaxWidth()) {
            UnderlinedTextField(
                value = state,
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
                            state = it
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        UnderlinedTextField(value = city, onValueChange = { city = it }, label = "City")

        Spacer(modifier = Modifier.height(12.dp))
        UnderlinedTextField(
            value = pincode,
            onValueChange = { pincode = it },
            label = "Pincode",
            keyboardType = KeyboardType.Number
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text("GST Details", fontWeight = FontWeight.Bold, fontSize = 14.sp)

        Spacer(modifier = Modifier.height(12.dp))
        UnderlinedTextField(
            value = gstin,
            onValueChange = { gstin = it },
            label = "GSTIN",
            readOnly = noGst
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = noGst, onCheckedChange = { noGst = it })
            Text("I Don’t have GST")
        }

        UnderlinedTextField(value = pan, onValueChange = { pan = it }, label = "PAN")

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
                    navController.navigate("bankDetails")
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

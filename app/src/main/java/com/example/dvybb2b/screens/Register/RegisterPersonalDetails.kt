package com.example.dvybb2b.ui.theme.screens.register

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dvybb2b.R
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextField
import androidx.navigation.NavHostController

@Composable
fun RegisterPersonalDetails(navController: NavHostController) {
    val context = LocalContext.current


    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }
    val stateList = listOf("Andhra Pradesh", "Karnataka", "Tamil Nadu", "Telangana", "Kerala")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
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
            modifier = Modifier.padding(top = 36.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Step Indicator
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Box(
                modifier = Modifier
                    .height(3.dp)
                    .weight(1f)
                    .background(Color(0xFF008CBD))
            )
            Spacer(modifier = Modifier.width(4.dp))
            Box(
                modifier = Modifier
                    .height(3.dp)
                    .weight(1f)
                    .background(Color.LightGray)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Box(
                modifier = Modifier
                    .height(3.dp)
                    .weight(1f)
                    .background(Color.LightGray)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "1  Personal details",
            color = Color(0xFF008CBD),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Inputs
        UnderlinedTextField(value = name, onValueChange = { name = it }, label = "Enter Your Name")
        Spacer(modifier = Modifier.height(12.dp))
        UnderlinedTextField(value = phone, onValueChange = { phone = it }, label = "Enter 10 digit mobile number")
        Spacer(modifier = Modifier.height(12.dp))

        // State Dropdown
        Box(modifier = Modifier.fillMaxWidth()) {
            UnderlinedTextField(
                value = state,
                onValueChange = { },
                label = "Select State",
                readOnly = true,
                trailingIcon = {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
                },
                onClick = { expanded = true }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                stateList.forEach {
                    DropdownMenuItem(onClick = {
                        state = it
                        expanded = false
                    }, text = { Text(it) })
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        UnderlinedTextField(value = city, onValueChange = { city = it }, label = "City")
        Spacer(modifier = Modifier.height(12.dp))
        UnderlinedTextField(value = address, onValueChange = { address = it }, label = "Address")

        Spacer(modifier = Modifier.height(20.dp))

        // Next Button
        Button(
            onClick = {
                Toast.makeText(context, "Proceeding...", Toast.LENGTH_SHORT).show()
                navController.navigate("shopDetails")
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
        label = { Text(label) },
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

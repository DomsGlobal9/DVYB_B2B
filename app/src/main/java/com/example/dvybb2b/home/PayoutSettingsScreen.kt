@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.dvybb2b.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.dvybb2b.R


//@file:OptIn(ExperimentalMaterial3Api::class):
@Composable
fun PayoutSettingsScreen(navController: NavController) {
    var selectedBank by remember { mutableStateOf("") }
    val bankOptions = listOf("Bank Name", "Axis Bank", "HDFC", "SBI", "ICICI")

    var bankAddress by remember { mutableStateOf("") }
    var accountHolder by remember { mutableStateOf("") }
    var accountNumber by remember { mutableStateOf("") }
    var ifscCode by remember { mutableStateOf("") }
    var upiId by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Payout settings", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                },
                navigationIcon = {
                    IconButton(onClick = { /* Back action */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Edit action */ }) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            var expanded by remember { mutableStateOf(false) }

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {

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

                TextField(
                    value = selectedBank,
                    onValueChange = { selectedBank = it },
                    label = { Text("Bank Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    readOnly = true,
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Blue.copy(alpha = 0.8f),
                        unfocusedIndicatorColor = Color.Gray.copy(alpha = 0.5f),
                        focusedContainerColor = Color(0xFFE3F2FD),
                        unfocusedContainerColor = Color.Transparent
                    )
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    bankOptions.forEach { bank ->
                        DropdownMenuItem(
                            text = { Text(bank) },
                            onClick = {
                                selectedBank = bank
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Define underline-style TextField as a @Composable
            @Composable
            fun UnderlineTextField(
                value: String,
                onValueChange: (String) -> Unit,
                label: String,
                keyboardType: KeyboardType = KeyboardType.Text
            ) {
                TextField(
                    value = value,
                    onValueChange = onValueChange,
                    label = { Text(label) },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Blue.copy(alpha = 0.8f),
                        unfocusedIndicatorColor = Color.Gray.copy(alpha = 0.5f),
                        focusedContainerColor = Color(0xFFE3F2FD),
                        unfocusedContainerColor = Color.Transparent
                    )
                )
            }

            UnderlineTextField(bankAddress, { bankAddress = it }, "Bank Address")
            Spacer(modifier = Modifier.height(12.dp))

            UnderlineTextField(accountHolder, { accountHolder = it }, "Account Holder Name")
            Spacer(modifier = Modifier.height(12.dp))

            UnderlineTextField(accountNumber, { accountNumber = it }, "Bank Account Number", KeyboardType.Number)
            Spacer(modifier = Modifier.height(12.dp))

            UnderlineTextField(ifscCode, { ifscCode = it }, "IFSC Code")
            Spacer(modifier = Modifier.height(12.dp))

            UnderlineTextField(upiId, { upiId = it }, "UPI ID")
        }
    }
}
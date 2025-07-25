package com.example.dvybb2b.screens.Register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Help
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dvybb2b.R
import kotlinx.coroutines.launch

// Data classes for better structure
data class BankDetailsState(
    val selectedBank: String = "",
    val branchName: String = "",
    val accountHolderName: String = "",
    val accountNumber: String = "",
    val confirmAccountNumber: String = "",
    val ifscCode: String = ""
)

data class ValidationErrors(
    val bankError: String? = null,
    val branchError: String? = null,
    val accountHolderError: String? = null,
    val accountNumberError: String? = null,
    val confirmAccountNumberError: String? = null,
    val ifscError: String? = null
) {
    val hasErrors: Boolean
        get() = listOfNotNull(bankError, branchError, accountHolderError,
            accountNumberError, confirmAccountNumberError, ifscError).isNotEmpty()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterBankDetailsScreen(
   navController: NavController,
    onHelp: () -> Unit = {}
) {
    var bankDetailsState by remember { mutableStateOf(BankDetailsState()) }
    var validationErrors by remember { mutableStateOf(ValidationErrors()) }
    var isBankDropdownExpanded by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.loginbg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            BankDetailsCard(
                bankDetailsState = bankDetailsState,
                validationErrors = validationErrors,
                isBankDropdownExpanded = isBankDropdownExpanded,
                isLoading = isLoading,
                onStateChange = { bankDetailsState = it },
                onValidationChange = { validationErrors = it },
                onBankDropdownExpandedChange = { isBankDropdownExpanded = it },
                onNext = { state ->
                    val errors = validateBankDetails(state)
                    validationErrors = errors

                    if (!errors.hasErrors) {
                        isLoading = true
                        // Simulate API call
                        coroutineScope.launch {
                            try {
                                kotlinx.coroutines.delay(2000) // Simulate network delay
                                navController.navigate("passwordAndOtp")
                            } catch (e: Exception) {
                                snackbarHostState.showSnackbar(
                                    message = "Failed to save bank details. Please try again.",
                                    actionLabel = "Retry"
                                )
                            } finally {
                                isLoading = false
                            }
                        }
                    }
                },
                onHelp = onHelp
            )
        }

        // Snackbar
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun BankDetailsCard(
    bankDetailsState: BankDetailsState,
    validationErrors: ValidationErrors,
    isBankDropdownExpanded: Boolean,
    isLoading: Boolean,
    onStateChange: (BankDetailsState) -> Unit,
    onValidationChange: (ValidationErrors) -> Unit,
    onBankDropdownExpandedChange: (Boolean) -> Unit,
    onNext: (BankDetailsState) -> Unit,
    onHelp: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(650.dp)
            .padding(1.dp),
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoSection()
            Spacer(modifier = Modifier.height(8.dp))
            ProgressIndicator(currentStep = 3)
            Spacer(modifier = Modifier.height(24.dp))
            StepIndicator(stepNumber = "3", stepTitle = "Bank details")
            Spacer(modifier = Modifier.height(20.dp))

            BankDetailsFields(
                bankDetailsState = bankDetailsState,
                validationErrors = validationErrors,
                isBankDropdownExpanded = isBankDropdownExpanded,
                onStateChange = onStateChange,
                onValidationChange = onValidationChange,
                onBankDropdownExpandedChange = onBankDropdownExpandedChange
            )

            Spacer(modifier = Modifier.height(24.dp))
            NextButton(
                isEnabled = !validationErrors.hasErrors && bankDetailsState.selectedBank.isNotEmpty(),
                isLoading = isLoading,
                onClick = { onNext(bankDetailsState) }
            )
            Spacer(modifier = Modifier.height(16.dp))
            HelpSection(onClick = onHelp)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BankDetailsFields(
    bankDetailsState: BankDetailsState,
    validationErrors: ValidationErrors,
    isBankDropdownExpanded: Boolean,
    onStateChange: (BankDetailsState) -> Unit,
    onValidationChange: (ValidationErrors) -> Unit,
    onBankDropdownExpandedChange: (Boolean) -> Unit
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Bank Selection Dropdown
        ExposedDropdownMenuBox(
            expanded = isBankDropdownExpanded,
            onExpandedChange = onBankDropdownExpandedChange
        ) {
            OutlinedTextField(
                value = bankDetailsState.selectedBank,
                onValueChange = {},
                readOnly = true,
                label = { Text("Bank Name") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isBankDropdownExpanded)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF5DADE2),
                    focusedLabelColor = Color(0xFF5DADE2)
                ),
                isError = validationErrors.bankError != null,
                supportingText = validationErrors.bankError?.let {
                    { ErrorText(text = it) }
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )

            ExposedDropdownMenu(
                expanded = isBankDropdownExpanded,
                onDismissRequest = { onBankDropdownExpandedChange(false) }
            ) {
                getBankList().forEach { bank ->
                    DropdownMenuItem(
                        text = { Text(bank) },
                        onClick = {
                            onStateChange(bankDetailsState.copy(selectedBank = bank))
                            onBankDropdownExpandedChange(false)

                            onValidationChange(validationErrors.copy(bankError = null))
                        }
                    )
                }
            }
        }

        // Bank Branch Name with custom style
        CustomInputField(
            value = bankDetailsState.branchName,
            onValueChange = {
                onStateChange(bankDetailsState.copy(branchName = it))
                if (validationErrors.branchError != null) {
                    onValidationChange(validationErrors.copy(branchError = null))
                }
            },
            label = "Branch Name",
            placeholder = "",
            isError = validationErrors.branchError != null,
            errorText = validationErrors.branchError,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        // Account Holder Name with custom style
        CustomInputField(
            value = bankDetailsState.accountHolderName,
            onValueChange = {
                onStateChange(bankDetailsState.copy(accountHolderName = it))
                if (validationErrors.accountHolderError != null) {
                    onValidationChange(validationErrors.copy(accountHolderError = null))
                }
            },
            label = "Enter Account Holder Name",
            placeholder = "",
            isError = validationErrors.accountHolderError != null,
            errorText = validationErrors.accountHolderError,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        // Account Number with custom style
        CustomInputField(
            value = bankDetailsState.accountNumber,
            onValueChange = { input ->
                // Only allow digits and limit length
                val filtered = input.filter { it.isDigit() }.take(20)
                onStateChange(bankDetailsState.copy(accountNumber = filtered))
                if (validationErrors.accountNumberError != null) {
                    onValidationChange(validationErrors.copy(accountNumberError = null))
                }
            },
            label = "Account Number",
            placeholder = "",
            isError = validationErrors.accountNumberError != null,
            errorText = validationErrors.accountNumberError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        // Confirm Account Number with custom style and match indicator
        CustomInputField(
            value = bankDetailsState.confirmAccountNumber,
            onValueChange = { input ->
                val filtered = input.filter { it.isDigit() }.take(18)
                onStateChange(bankDetailsState.copy(confirmAccountNumber = filtered))
                if (validationErrors.confirmAccountNumberError != null) {
                    onValidationChange(validationErrors.copy(confirmAccountNumberError = null))
                }
            },
            label = "Confirm Account Number",
            placeholder = "",
            isError = validationErrors.confirmAccountNumberError != null,
            errorText = validationErrors.confirmAccountNumberError,
            supportingText = if (validationErrors.confirmAccountNumberError == null && bankDetailsState.confirmAccountNumber.isNotEmpty()) {
                if (bankDetailsState.accountNumber == bankDetailsState.confirmAccountNumber) {
                    "✓ Account numbers match"
                } else {
                    "Account numbers don't match"
                }
            } else null,
            supportingTextColor = if (bankDetailsState.accountNumber == bankDetailsState.confirmAccountNumber &&
                bankDetailsState.confirmAccountNumber.isNotEmpty()) Color(0xFF4CAF50) else Color(0xFFFF5722),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        // IFSC Code with custom style
        CustomInputField(
            value = bankDetailsState.ifscCode,
            onValueChange = { input ->
                // IFSC format: 4 letters + 7 digits
                val filtered = input.uppercase().take(11)
                onStateChange(bankDetailsState.copy(ifscCode = filtered))
                if (validationErrors.ifscError != null) {
                    onValidationChange(validationErrors.copy(ifscError = null))
                }
            },
            label = "IFSC Code",
            placeholder = "e.g., SBIN0001234",
            isError = validationErrors.ifscError != null,
            errorText = validationErrors.ifscError,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Characters,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            )
        )
    }
}

@Composable
private fun CustomInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    isError: Boolean = false,
    errorText: String? = null,
    supportingText: String? = null,
    supportingTextColor: Color = Color.Gray,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    var isFocused by remember { mutableStateOf(false) }

    val borderColor = when {
        isError -> Color(0xFFFF5722)
        isFocused -> Color(0xFF5DADE2)
        else -> Color(0XFF5E6B70)
    }

    val backgroundColor = if (isFocused) Color(0xFFF0F8FF) else Color.Transparent

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor, shape = RoundedCornerShape(4.dp))
            .drawBehind {
                val strokeWidth = if (isFocused) 2.dp.toPx() else 1.dp.toPx()
                val y = size.height - strokeWidth / 2
                drawLine(
                    color = borderColor,
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth = strokeWidth
                )
            }
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            placeholder = { Text(placeholder, color = Color.Gray) },
            isError = isError,
            supportingText = if (isError && errorText != null) {
                { ErrorText(text = errorText) }
            } else if (supportingText != null) {
                { Text(supportingText, color = supportingTextColor, style = MaterialTheme.typography.bodySmall) }
            } else null,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            shape = RectangleShape,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color(0xFF8A9193),
                unfocusedTextColor = Color(0xFF8A9193),
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                errorBorderColor = Color.Transparent,
                focusedLabelColor = Color(0xFF5DADE2),
                unfocusedLabelColor = Color(0xFF5E6B70),
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            )
        )

    }
}

@Composable
private fun ErrorText(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Error,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.error,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
private fun LogoSection() {
    Box(
        modifier = Modifier.size(48.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = "Register",
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color(0xFF1D4C5C)
    )
}

@Composable
private fun ProgressIndicator(currentStep: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(4) { index ->
            Box(
                modifier = Modifier
                    .width(74.dp)
                    .height(6.dp)
                    .background(
                        if (index < currentStep) Color(0xFF4F8396) else Color(0xFFE0E0E0),
                        RoundedCornerShape(2.dp)
                    )
            )
            if (index < 3) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Composable
private fun StepIndicator(stepNumber: String, stepTitle: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(Color(0xFF4F8396), RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stepNumber,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = stepTitle,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF374151)
        )
    }
}

@Composable
private fun NextButton(
    isEnabled: Boolean,
    isLoading: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = isEnabled && !isLoading,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF7DBBD1),
            disabledContainerColor = Color(0xFF7DBBD1)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                color = Color.White,
                strokeWidth = 2.dp
            )
        } else {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Next",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.ArrowForwardIos,
                    contentDescription = "Next",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
private fun HelpSection(onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Need help",
                fontSize = 14.sp,
                color = Color(0xFF666666)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = Icons.Default.Help,
                contentDescription = "Help",
                tint = Color(0xFF7DBBD1),
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

// Utility Functions
private fun getBankList(): List<String> = listOf(
    "State Bank of India",
    "HDFC Bank",
    "ICICI Bank",
    "Punjab National Bank",
    "Bank of Baroda",
    "Canara Bank",
    "Union Bank of India",
    "Axis Bank",
    "Bank of India",
    "Indian Bank",
    "Central Bank of India",
    "Indian Overseas Bank",
    "UCO Bank",
    "Punjab & Sind Bank",
    "Bank of Maharashtra"
)

private fun validateBankDetails(state: BankDetailsState): ValidationErrors {
    return ValidationErrors(
        bankError = if (state.selectedBank.isEmpty()) "Please select a bank" else null,
        branchError = when {
            state.branchName.isEmpty() -> "Branch name is required"
            state.branchName.length < 2 -> "Branch name must be at least 2 characters"
            else -> null
        },
        accountHolderError = when {
            state.accountHolderName.isEmpty() -> "Account holder name is required"
            state.accountHolderName.length < 2 -> "Name must be at least 2 characters"
            !state.accountHolderName.matches(Regex("^[a-zA-Z\\s.]+$")) -> "Name can only contain letters, spaces, and dots"
            else -> null
        },
        accountNumberError = when {
            state.accountNumber.isEmpty() -> "Account number is required"
            state.accountNumber.length < 9 -> "Account number must be at least 9 digits"
            state.accountNumber.length > 18 -> "Account number cannot exceed 18 digits"
            else -> null
        },
        confirmAccountNumberError = when {
            state.confirmAccountNumber.isEmpty() -> "Please confirm your account number"
            state.accountNumber != state.confirmAccountNumber -> "Account numbers don't match"
            else -> null
        },
        ifscError = when {
            state.ifscCode.isEmpty() -> "IFSC code is required"
            // Fixed regex - 5th character can be any digit, not just 0
            !state.ifscCode.matches(Regex("^[A-Z]{4}[0-9][A-Z0-9]{6}$")) -> "Invalid IFSC format (e.g., SBIN0001234)"
            else -> null
        }
    )
}
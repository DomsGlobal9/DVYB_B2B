package com.example.dvybb2b.screens.Register

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.dvybb2b.R

@Composable
fun RegisterPasswordScreen(navController: NavController) {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }
    var otpNumber by remember { mutableStateOf("9102345678") }
    var isTermsAccepted by remember { mutableStateOf(true) }
    var isOtpDropdownExpanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
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
            RegistrationCard(
                navController = navController,
                password = password,
                onPasswordChange = { password = it },
                confirmPassword = confirmPassword,
                onConfirmPasswordChange = { confirmPassword = it },
                isPasswordVisible = isPasswordVisible,
                onPasswordVisibilityChange = { isPasswordVisible = it },
                isConfirmPasswordVisible = isConfirmPasswordVisible,
                onConfirmPasswordVisibilityChange = { isConfirmPasswordVisible = it },
                otpNumber = otpNumber,
                onOtpNumberChange = { otpNumber = it },
                isOtpDropdownExpanded = isOtpDropdownExpanded,
                onOtpDropdownExpandedChange = { isOtpDropdownExpanded = it },
                isTermsAccepted = isTermsAccepted,
                onTermsAcceptedChange = { isTermsAccepted = it }
            )
        }
    }
}


@Composable
fun CustomInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String = "",
    isPassword: Boolean = false,
    isVisible: Boolean = false,
    onVisibilityToggle: (() -> Unit)? = null,
    readOnly: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    val strokeWidth = 1.dp.toPx()
                    val y = size.height - strokeWidth / 2
                    drawLine(
                        color = Color(0xFF5DADE2),
                        start = Offset(0f, y),
                        end = Offset(size.width, y),
                        strokeWidth = strokeWidth
                    )
                }
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                visualTransformation = if (isPassword && !isVisible) PasswordVisualTransformation() else VisualTransformation.None,
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 14.sp,
                    color = Color.Black
                ),
                readOnly = readOnly,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 8.dp)
            )
            if (isPassword && onVisibilityToggle != null) {
                IconButton(onClick = onVisibilityToggle) {
                    Icon(
                        imageVector = if (isVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = null,
                        tint = Color(0xFF5DADE2)
                    )
                }
            }
            trailingIcon?.invoke()
        }
    }
}


@Composable
fun RegistrationCard(
    navController: NavController,
    password: String,
    onPasswordChange: (String) -> Unit,
    confirmPassword: String,
    onConfirmPasswordChange: (String) -> Unit,
    isPasswordVisible: Boolean,
    onPasswordVisibilityChange: (Boolean) -> Unit,
    isConfirmPasswordVisible: Boolean,
    onConfirmPasswordVisibilityChange: (Boolean) -> Unit,
    otpNumber: String,
    onOtpNumberChange: (String) -> Unit,
    isOtpDropdownExpanded: Boolean,
    onOtpDropdownExpandedChange: (Boolean) -> Unit,
    isTermsAccepted: Boolean,
    onTermsAcceptedChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(650.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoSection()
            Spacer(modifier = Modifier.height(8.dp))
            ProgressIndicator(currentStep = 4)
            Spacer(modifier = Modifier.height(30.dp))
            StepIndicator(stepNumber = "4", stepTitle = "Password")
            Spacer(modifier = Modifier.height(20.dp))
            CustomInputField(
                value = password,
                onValueChange = onPasswordChange,
                label = "Enter password",
                isPassword = true,
                isVisible = isPasswordVisible,
                onVisibilityToggle = { onPasswordVisibilityChange(!isPasswordVisible) }
            )
            Spacer(modifier = Modifier.height(10.dp))
            CustomInputField(
                value = confirmPassword,
                onValueChange = onConfirmPasswordChange,
                label = "Re-Enter password",
                isPassword = true,
                isVisible = isConfirmPasswordVisible,
                onVisibilityToggle = { onConfirmPasswordVisibilityChange(!isConfirmPasswordVisible) }
            )
            Spacer(modifier = Modifier.height(10.dp))
            OtpSection(
                otpNumber = otpNumber,
                onOtpNumberChange = onOtpNumberChange,
                isOtpDropdownExpanded = isOtpDropdownExpanded,
                onOtpDropdownExpandedChange = onOtpDropdownExpandedChange
            )
            Spacer(modifier = Modifier.height(10.dp))
            TermsCheckbox(isTermsAccepted, onTermsAcceptedChange)
            Spacer(modifier = Modifier.height(10.dp))
            GetOtpButton(navController)
            Spacer(modifier = Modifier.height(14.dp))
            HelpSection()
        }
    }
}


@Composable
private fun OtpSection(
    otpNumber: String,
    onOtpNumberChange: (String) -> Unit,
    isOtpDropdownExpanded: Boolean,
    onOtpDropdownExpandedChange: (Boolean) -> Unit
) {
    Text(
        text = "OTP Verification",
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        color = Color(0xFF2C3E50),
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(10.dp))

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        CustomInputField(
            value = otpNumber,
            onValueChange = onOtpNumberChange,
            label = "Select Mobile Number",
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = {
                    onOtpDropdownExpandedChange(!isOtpDropdownExpanded)
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown",
                        modifier = Modifier.rotate(if (isOtpDropdownExpanded) 180f else 0f)
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onOtpDropdownExpandedChange(true) }
        )

        DropdownMenu(
            expanded = isOtpDropdownExpanded,
            onDismissRequest = { onOtpDropdownExpandedChange(false) },
            modifier = Modifier.fillMaxWidth()
        ) {
            listOf("9102345678", "9876543210").forEach { number ->
                DropdownMenuItem(
                    onClick = {
                        onOtpNumberChange(number)
                        onOtpDropdownExpandedChange(false)
                    },
                    text = {
                        Text(
                            text = number,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun TermsCheckbox(
    isTermsAccepted: Boolean,
    onTermsAcceptedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier.fillMaxWidth()
    ) {
        Checkbox(
            checked = isTermsAccepted,
            onCheckedChange = onTermsAcceptedChange,
            colors = CheckboxDefaults.colors(checkedColor = Color(0xFF5DADE2))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "By signing up, you've agree to our",
            fontSize = 12.sp,
            color = Color(0xFF666666),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "Terms and Conditions",
            fontSize = 12.sp,
            color = Color(0xFF468BA4),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "and",
            fontSize = 12.sp,
            color = Color(0xFF666666),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "Privacy Policy.",
            fontSize = 12.sp,
            color = Color(0xFF468BA4),
            modifier = Modifier.weight(1f)
        )
    }
}


@Composable
fun GetOtpButton(navController: NavController) {
    var isPressed by remember { mutableStateOf(false) }

    val animatedOffset by animateFloatAsState(
        targetValue = if (isPressed) -8f else 0f,
        animationSpec = tween(durationMillis = 150, easing = EaseOutBack),
        label = "offset",
        finishedListener = { if (isPressed) isPressed = false }
    )

    val animatedScale by animateFloatAsState(
        targetValue = if (isPressed) 0.98f else 1f,
        animationSpec = tween(durationMillis = 150, easing = EaseOutBack),
        label = "scale"
    )

    Button(
        onClick = {
            isPressed = true
            navController.navigate("otpEntry")
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5CA8C5))
    ) {
        Text(text = "Get OTP", color = Color.White)
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
            contentDescription = null,
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
            if (index < 3) Spacer(modifier = Modifier.width(8.dp))
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
private fun HelpSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
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
            tint = Color(0xFF666666),
            modifier = Modifier.size(16.dp)
        )
    }
}

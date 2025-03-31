package com.anubhavauth.washup

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.rounded.ChevronLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.anubhavauth.washup.entities.User
import com.anubhavauth.washup.ui.theme.AccentColor
import com.anubhavauth.washup.ui.theme.BackgroundColor
import com.anubhavauth.washup.ui.theme.PrimaryColor
import com.anubhavauth.washup.ui.theme.SecondaryColor
import com.anubhavauth.washup.ui.theme.SurfaceColor
import com.anubhavauth.washup.ui.theme.TextSecondaryColor

@SuppressLint("DefaultLocale")
@Composable
fun Registration(
    modifier: Modifier = Modifier,
    navController: NavController,
    washUpViewModel: WashUpViewModel
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Top App Bar with Gradient
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(PrimaryColor, SecondaryColor)
                        )
                    )
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { navController.navigateUp() },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ChevronLeft,
                            contentDescription = "Go back",
                            tint = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = "Create Account",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Registration Form
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Form Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceColor)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "PERSONAL INFORMATION",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryColor,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        // Name Field
                        InputField(
                            value = name,
                            onValueChange = { name = it },
                            label = "Full Name",
                            icon = Icons.Default.Person
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Email Field
                        InputField(
                            value = email,
                            onValueChange = { email = it },
                            label = "Email Address",
                            icon = Icons.Default.Email,
                            keyboardType = KeyboardType.Email
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Phone Field
                        InputField(
                            value = phone,
                            onValueChange = { phone = it },
                            label = "Phone Number",
                            icon = Icons.Default.Phone,
                            keyboardType = KeyboardType.Phone
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Address Field
                        InputField(
                            value = address,
                            onValueChange = { address = it },
                            label = "Delivery Address",
                            icon = Icons.Default.Home
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        // Password Section
                        Text(
                            text = "SECURITY",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryColor,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        // Password Field
                        InputField(
                            value = password,
                            onValueChange = { password = it },
                            label = "Password",
                            icon = Icons.Default.Lock,
                            isPassword = true,
                            passwordVisible = passwordVisible,
                            onPasswordVisibilityChange = { passwordVisible = it }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Confirm Password Field
                        InputField(
                            value = confirmPassword,
                            onValueChange = { confirmPassword = it },
                            label = "Confirm Password",
                            icon = Icons.Default.Lock,
                            isPassword = true,
                            passwordVisible = confirmPasswordVisible,
                            onPasswordVisibilityChange = { confirmPasswordVisible = it }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Already have an account?",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondaryColor
                    )

                    TextButton(onClick = { navController.navigate("login") }) {
                        Text(
                            text = "Login",
                            color = PrimaryColor,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))
            }
        }

        // Register Button
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceColor)
                    .padding(16.dp)
            ) {
                Button(
                    onClick = {
                        // Handle registration
                        val user = User(
                            id = null,
                            name = name,
                            email = email,
                            phone = phone,
                            password = password,
                            address = address,
                            role = "customer",
                            createdAt = null,
                            orders = null
                        )
                        // washUpViewModel.registerUser(user)
                        navController.navigate("login")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = AccentColor),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    enabled = name.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty() &&
                            password.isNotEmpty() && confirmPassword.isNotEmpty() &&
                            password == confirmPassword && address.isNotEmpty()
                ) {
                    Text(
                        text = "Register",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    passwordVisible: Boolean = false,
    onPasswordVisibilityChange: (Boolean) -> Unit = {}
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = PrimaryColor,
            unfocusedBorderColor = TextSecondaryColor.copy(alpha = 0.5f),
            focusedLabelColor = PrimaryColor,
            cursorColor = PrimaryColor
        ),
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = PrimaryColor
            )
        },
        trailingIcon = {
            if (isPassword) {
                val icon = if (passwordVisible) {
                    Icons.Default.Email // Typically you'd use a "visibility_off" icon
                } else {
                    Icons.Default.Email // Typically you'd use a "visibility" icon
                }

                IconButton(onClick = { onPasswordVisibilityChange(!passwordVisible) }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password",
                        tint = TextSecondaryColor
                    )
                }
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = if (isPassword && !passwordVisible)
            PasswordVisualTransformation() else VisualTransformation.None
    )
}
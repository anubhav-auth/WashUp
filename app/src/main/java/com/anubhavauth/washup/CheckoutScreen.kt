package com.anubhavauth.washup

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.rounded.ChevronLeft
import androidx.compose.material.icons.rounded.Payment
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.anubhavauth.washup.ui.theme.AccentColor
import com.anubhavauth.washup.ui.theme.BackgroundColor
import com.anubhavauth.washup.ui.theme.PrimaryColor
import com.anubhavauth.washup.ui.theme.SecondaryColor
import com.anubhavauth.washup.ui.theme.SurfaceColor
import com.anubhavauth.washup.ui.theme.TextSecondaryColor
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("DefaultLocale")
@Composable
fun Checkout(
    modifier: Modifier = Modifier,
    washUpViewModel: WashUpViewModel,
    navController: NavController
) {
    val itemizedList by washUpViewModel.itemizedBill
    val totalPrice by washUpViewModel.totalPrice
    var showPaymentSuccess by remember { mutableStateOf(false) }

    val taxRate = 0.18f // 18% GST
    val tax = totalPrice * taxRate
    val grandTotal = totalPrice + tax

    val currentDate = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).format(Date())

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
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
                        text = "Your Receipt",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Receipt Content
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(16.dp))

                    // Store Info
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = SurfaceColor)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "WashUp Laundry Services",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryColor
                            )

                            Text(
                                text = "Invoice #WU-${System.currentTimeMillis() % 10000}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = TextSecondaryColor
                            )

                            Text(
                                text = currentDate,
                                style = MaterialTheme.typography.bodySmall,
                                color = TextSecondaryColor
                            )

                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                thickness = 1.dp,
                                color = Color.LightGray
                            )

                            Text(
                                text = "ITEM BREAKDOWN",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryColor
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }

                if (itemizedList.isNotEmpty()) {
                    items(itemizedList.keys.toList()) { item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(containerColor = SurfaceColor)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = item,
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontWeight = FontWeight.Medium,
                                        color = Color.Black
                                    )

                                    val quantity = itemizedList[item]?.first ?: 0
                                    val itemPrice = itemizedList[item]?.second ?: 0

                                    Text(
                                        text = "₹$itemPrice × $quantity",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.Black
                                    )
                                }

                                Text(
                                    text = "₹${itemizedList[item]?.second?.times(itemizedList[item]?.first ?: 0)}",
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                } else {
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = SurfaceColor)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(32.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "No items selected",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = TextSecondaryColor
                                )
                            }
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))

                    // Price Summary
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = SurfaceColor)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "PRICE SUMMARY",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryColor,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Subtotal",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = TextSecondaryColor
                                )
                                Text(
                                    text = "₹$totalPrice",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = TextSecondaryColor
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "GST (18%)",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = TextSecondaryColor
                                )
                                Text(
                                    text = "₹${String.format("%.2f", tax)}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = TextSecondaryColor
                                )
                            }

                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                thickness = 1.dp,
                                color = Color.LightGray
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Grand Total",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryColor
                                )
                                Text(
                                    text = "₹${String.format("%.2f", grandTotal)}",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryColor
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }

        // Payment Success Animation
        AnimatedVisibility(
            visible = showPaymentSuccess,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.7f)),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .width(280.dp)
                        .padding(16.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Payment Successful!",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Your laundry order has been confirmed.",
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center,
                            color = TextSecondaryColor
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                            onClick = { showPaymentSuccess = false },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = AccentColor)
                        ) {
                            Text("Done", color = Color.Black)
                        }
                    }
                }
            }
        }

        // Bottom Actions
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
                    onClick = { showPaymentSuccess = true },
                    colors = ButtonDefaults.buttonColors(containerColor = AccentColor),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Payment,
                        contentDescription = "Pay now",
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Pay ₹${String.format("%.2f", grandTotal)}",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedButton(
                    onClick = { /* Email Receipt Logic */ },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    border = BorderStroke(1.dp, PrimaryColor)
                ) {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email receipt",
                        tint = PrimaryColor
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Email Receipt",
                        color = PrimaryColor
                    )
                }
            }
        }
    }
}
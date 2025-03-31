package com.anubhavauth.washup

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.rounded.ChevronLeft
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.anubhavauth.washup.entities.Orders
import com.anubhavauth.washup.ui.theme.AccentColor
import com.anubhavauth.washup.ui.theme.BackgroundColor
import com.anubhavauth.washup.ui.theme.PrimaryColor
import com.anubhavauth.washup.ui.theme.SecondaryColor
import com.anubhavauth.washup.ui.theme.SurfaceColor
import com.anubhavauth.washup.ui.theme.TextSecondaryColor
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SuppressLint("DefaultLocale", "NewApi")
@Composable
fun OrderHistory(
    modifier: Modifier = Modifier,
    navController: NavController,
    washUpViewModel: WashUpViewModel
) {
    // Sample data for demonstration
    val orders = remember {
        listOf(
            Orders(
                orderId = "WU12345",
                user = null,
                status = "Completed",
                orderDate = LocalDateTime.now().minusDays(2),
                items = "Shirts (3), Pants (2), Bedsheets (1)",
                totalAmount = 450.00,
                paymentStatus = "Paid"
            ),
            Orders(
                orderId = "WU12346",
                user = null,
                status = "In Progress",
                orderDate = LocalDateTime.now().minusWeeks(1),
                items = "Suits (1), Shirts (5)",
                totalAmount = 750.00,
                paymentStatus = "Paid"
            ),
            Orders(
                orderId = "WU12347",
                user = null,
                status = "Pending Pickup",
                orderDate = LocalDateTime.now().minusWeeks(2),
                items = "Shirts (2), Jeans (3), Towels (4)",
                totalAmount = 680.00,
                paymentStatus = "Pending"
            ),
            Orders(
                orderId = "WU12348",
                user = null,
                status = "Cancelled",
                orderDate = LocalDateTime.now().minusMonths(1),
                items = "Blankets (2)",
                totalAmount = 350.00,
                paymentStatus = "Refunded"
            )
        )
    }

    // Filter options
    val filterOptions = listOf("All", "Completed", "In Progress", "Pending", "Cancelled")
    var selectedFilter by remember { mutableStateOf("All") }

    val filteredOrders = if (selectedFilter == "All") {
        orders
    } else {
        orders.filter { it.status == selectedFilter }
    }

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
                        text = "Order History",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Filter Chips
            ScrollableFilterChips(
                options = filterOptions,
                selectedOption = selectedFilter,
                onOptionSelected = { selectedFilter = it }
            )

            // Orders List
            if (filteredOrders.isEmpty()) {
                EmptyOrdersState()
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 16.dp)
                ) {
                    item {
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "${filteredOrders.size} ${if (filteredOrders.size == 1) "Order" else "Orders"}",
                            style = MaterialTheme.typography.titleMedium,
                            color = TextSecondaryColor,
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp)
                        )
                    }

                    items(filteredOrders) { order ->
                        OrderCard(order = order, onClick = {
                            // Navigate to order details
                            // navController.navigate("orderDetails/${order.orderId}")
                        })
                    }

                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyOrdersState() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Receipt,
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                tint = TextSecondaryColor.copy(alpha = 0.5f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "No orders found",
                style = MaterialTheme.typography.titleLarge,
                color = TextSecondaryColor
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Your order history will appear here",
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondaryColor.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { /* Navigate to services */ },
                colors = ButtonDefaults.buttonColors(containerColor = AccentColor),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Book a Service",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun ScrollableFilterChips(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    ScrollableRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        options.forEach { option ->
            FilterChip(
                selected = selectedOption == option,
                onClick = { onOptionSelected(option) },
                label = { Text(option) },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = SurfaceColor,
                    labelColor = TextSecondaryColor,
                    selectedContainerColor = PrimaryColor,
                    selectedLabelColor = Color.White
                ),
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }
}

@Composable
fun ScrollableRow(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Row(
        modifier = modifier
            .horizontalScroll(androidx.compose.foundation.rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}

@Composable
fun OrderCard(
    order: Orders,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceColor),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Order #${order.orderId}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryColor
                )

                OrderStatusChip(status = order.status ?: "Unknown")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Date
            OrderInfoRow(
                icon = Icons.Default.AccessTime,
                label = "Order Date",
                value = formatDate(order.orderDate)
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Items
            Text(
                text = order.items ?: "No items",
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondaryColor,
                modifier = Modifier.padding(start = 30.dp, top = 4.dp, bottom = 4.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Payment
            OrderInfoRow(
                icon = Icons.Default.Payments,
                label = "Total Amount",
                value = "₹${String.format("%.2f", order.totalAmount ?: 0.0)}"
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Payment Status
            PaymentStatusChip(status = order.paymentStatus ?: "Unknown")

            Spacer(modifier = Modifier.height(8.dp))

            // View Details Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = onClick,
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = PrimaryColor
                    )
                ) {
                    Text(
                        text = "View Details",
                        fontWeight = FontWeight.Bold
                    )

                    Icon(
                        imageVector = Icons.Rounded.ChevronRight,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun OrderInfoRow(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = TextSecondaryColor,
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = "$label: ",
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondaryColor
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}

@Composable
fun OrderStatusChip(status: String) {
    val (backgroundColor, textColor) = when (status) {
        "Completed" -> Pair(Color(0xFF4CAF50).copy(alpha = 0.2f), Color(0xFF2E7D32))
        "In Progress" -> Pair(Color(0xFF2196F3).copy(alpha = 0.2f), Color(0xFF1565C0))
        "Pending Pickup" -> Pair(Color(0xFFFFC107).copy(alpha = 0.2f), Color(0xFFFF8F00))
        "Cancelled" -> Pair(Color(0xFFF44336).copy(alpha = 0.2f), Color(0xFFD32F2F))
        else -> Pair(Color.LightGray, TextSecondaryColor)
    }

    Surface(
        color = backgroundColor,
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = status,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium,
            color = textColor,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun PaymentStatusChip(status: String) {
    val (backgroundColor, textColor, icon) = when (status) {
        "Paid" -> Triple(
            Color(0xFF4CAF50).copy(alpha = 0.1f),
            Color(0xFF2E7D32),
            Icons.Default.CheckCircle
        )
        "Pending" -> Triple(
            Color(0xFFFFC107).copy(alpha = 0.1f),
            Color(0xFFFF8F00),
            Icons.Default.AccessTime
        )
        "Refunded" -> Triple(
            Color(0xFF2196F3).copy(alpha = 0.1f),
            Color(0xFF1565C0),
            Icons.Default.Payments
        )
        else -> Triple(
            Color.LightGray,
            TextSecondaryColor,
            Icons.Default.Payments
        )
    }

    Row(
        modifier = Modifier
            .padding(start = 30.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = textColor,
            modifier = Modifier.size(14.dp)
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = status,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium,
            color = textColor
        )
    }
}

// Helper function to format date
@SuppressLint("NewApi")
fun formatDate(dateTime: LocalDateTime?): String {
    return dateTime?.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) ?: "Unknown"
}
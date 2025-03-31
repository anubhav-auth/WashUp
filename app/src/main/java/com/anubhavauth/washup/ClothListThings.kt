package com.anubhavauth.washup

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.anubhavauth.washup.ui.theme.AccentColor
import com.anubhavauth.washup.ui.theme.BackgroundColor
import com.anubhavauth.washup.ui.theme.PrimaryColor
import com.anubhavauth.washup.ui.theme.SecondaryColor
import com.anubhavauth.washup.ui.theme.SurfaceColor
import com.anubhavauth.washup.ui.theme.TextPrimaryColor
import com.anubhavauth.washup.ui.theme.TextSecondaryColor

@SuppressLint("MutableCollectionMutableState")
@Composable
fun ClothListMenu(
    modifier: Modifier = Modifier,
    items: List<ClothListContent>,
    washUpViewModel: WashUpViewModel,
    navController: NavHostController
) {
    var totalPrice by washUpViewModel.totalPrice
    val itemizedBill by washUpViewModel.itemizedBill

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                PrimaryColor,
                                SecondaryColor
                            )
                        )
                    )
                    .padding(16.dp),
            ) {
                Text(
                    text = "WashUp Services",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "",
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.CenterEnd)
                        .clickable {
                            navController.navigate("order-history")
                        },
                    tint = Color.White
                )
            }

            // Items List
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 12.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Select Items",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimaryColor,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                items(items) { item ->
                    ClothListItem(
                        item = item,
                        washUpViewModel = washUpViewModel,
                        onQuantityChange = { quantityChange ->
                            totalPrice += quantityChange * item.price
                            itemizedBill[item.name] = Pair(
                                (itemizedBill[item.name]?.first?.plus(quantityChange)
                                    ?: quantityChange), item.price
                            )
                        }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }

        // Total and Checkout Button - Fixed at bottom
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .shadow(
                    elevation = 12.dp,
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceColor)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Total",
                        style = MaterialTheme.typography.titleMedium,
                        color = TextSecondaryColor
                    )
                    Text(
                        text = "₹$totalPrice",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimaryColor
                    )
                }

                Button(
                    onClick = { navController.navigate("checkout-screen") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AccentColor
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .height(48.dp)
                        .width(150.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "Checkout",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Rounded.ArrowForward,
                            contentDescription = "Go to checkout",
                            tint = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ClothListItem(
    item: ClothListContent,
    washUpViewModel: WashUpViewModel,
    onQuantityChange: (Int) -> Unit
) {
    val itemized by washUpViewModel.itemizedBill
    var noOfItem by remember {
        mutableIntStateOf(itemized[item.name]?.first ?: 0)
    }

    val backgroundColor = animateColorAsState(
        targetValue = if (noOfItem > 0) Color(0xFFEDF7ED) else SurfaceColor,
        animationSpec = tween(300),
        label = "backgroundColorAnimation"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 4.dp)
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(12.dp),
                ambientColor = Color.Gray.copy(alpha = 0.2f)
            ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor.value)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Item image with rounded corners
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .shadow(elevation = 3.dp, shape = RoundedCornerShape(12.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(item.imageId),
                    contentDescription = item.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(60.dp)
                        .padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Item details with better typography
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimaryColor
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "₹${item.price}/piece",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondaryColor
                )
            }

            // Quantity controls with better styling
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .border(
                        width = 1.dp,
                        color = if (noOfItem > 0) PrimaryColor else Color.LightGray,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                // Decrease button
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clickable {
                            if (noOfItem > 0) {
                                noOfItem--
                                onQuantityChange(-1)
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Remove,
                        contentDescription = "Decrease quantity",
                        tint = if (noOfItem > 0) PrimaryColor else Color.Gray,
                        modifier = Modifier.size(18.dp)
                    )
                }

                // Quantity display
                Box(
                    modifier = Modifier
                        .width(36.dp)
                        .height(36.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = noOfItem.toString(),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = if (noOfItem > 0) PrimaryColor else TextSecondaryColor
                    )
                }

                // Increase button
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(
                            color = if (noOfItem > 0) PrimaryColor else AccentColor,
                            shape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp)
                        )
                        .clickable {
                            noOfItem++
                            onQuantityChange(1)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Increase quantity",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

data class ClothListContent(
    val name: String, val imageId: Int, val quantity: Int = 0, val price: Int
)
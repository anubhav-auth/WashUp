package com.anubhavauth.washup

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

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

    LazyColumn(modifier = modifier) {
        items(items) { item ->
            ClothListItem(
                item = item,
                onQuantityChange = { quantityChange ->
                    totalPrice += quantityChange * item.price
                    itemizedBill[item.name] =
                        itemizedBill[item.name]?.plus(quantityChange) ?: quantityChange

                }
            )
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Text(
                    "Total Price: RS.$totalPrice",
                    style = androidx.compose.ui.text.TextStyle(fontSize = 24.sp)
                )
                Text(itemizedBill.toString())
                Button(
                    onClick = {
                        navController.navigate("checkout-screen")
                    }
                ) {
                    Text("bill ->")
                }
            }
        }
    }


}

@Composable
fun ClothListItem(item: ClothListContent, onQuantityChange: (Int) -> Unit) {
    var noOfItem by remember { mutableIntStateOf(0) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 21.dp, vertical = 12.dp)
    ) {
        Image(
            modifier = Modifier
                .padding(6.dp)
                .size(50.dp)
                .align(Alignment.CenterVertically),
            painter = painterResource(item.imageId),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(18.dp))
        Column(
            modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center
        ) {
            Text(item.name)
            Text("${item.quantity} x RS.${item.price}/piece")
        }
        Row(
            modifier = Modifier
                .height(50.dp)
                .padding(end = 21.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .clickable {
                        if (noOfItem > 0) {
                            noOfItem -= 1
                            onQuantityChange(-1)
                        }
                    },
            ) {
                Icon(
                    imageVector = Icons.Filled.Remove, contentDescription = "", tint = Color.Black
                )
            }
            Box(
                modifier = Modifier.background(Color.White)
            ) {
                Text(text = noOfItem.toString(), color = Color.Black)
            }

            Box(modifier = Modifier
                .background(Color.White)
                .clickable {
                    noOfItem += 1
                    onQuantityChange(1)
                }) {
                Icon(
                    imageVector = Icons.Filled.Add, contentDescription = "", tint = Color.Black
                )
            }
        }
    }
}

data class ClothListContent(
    val name: String, val imageId: Int, val quantity: Int = 0, val price: Int
)
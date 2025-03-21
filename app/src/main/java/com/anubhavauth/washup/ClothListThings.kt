package com.anubhavauth.washup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ClothListMenu(modifier: Modifier = Modifier, items: List<ClothListContent>) {
    LazyColumn(modifier = modifier) {
        items(items) { item ->
            ClothListItem(item = item)
        }
    }
}

@Composable
fun ClothListItem(item: ClothListContent) {
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
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(item.name)
            Text("${item.quantity} x RS.${item.price}/piece")
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Blue),
            verticalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier
                .background(Color.White)
                .size(20.dp, 10.dp))
        }
    }
}


data class ClothListContent(
    val name: String,
    val imageId: Int,
    val quantity: Int = 0,
    val price: Int
)
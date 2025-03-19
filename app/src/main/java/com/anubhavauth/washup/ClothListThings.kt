package com.anubhavauth.washup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ClothListMenu(modifier: Modifier = Modifier) {

}

@Composable
fun ClothListItem(item: ClothListContent) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 21.dp)
    ) {
        Icon(painter = painterResource(item.iconId), contentDescription = "", tint = Color.Red )
        Column {
            Text(item.name)
            Text("${item.quantity} x RS.${item.price}/piece")
        }

    }
}


data class ClothListContent(
    val name: String,
    val iconId: Int,
    val iconSize: Dp,
    val quantity: Int = 0,
    val price: Int
)
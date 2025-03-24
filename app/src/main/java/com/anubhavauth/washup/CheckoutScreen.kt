package com.anubhavauth.washup

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun Checkout(
    modifier: Modifier = Modifier,
    washUpViewModel: WashUpViewModel,
    navController: NavController
) {
    val itemizedList by washUpViewModel.itemizedBill
    LazyColumn(modifier = modifier) {
        items(itemizedList.keys.toList()){ item ->
            Text("$item pc: ${itemizedList[item]}")
        }
    }
}
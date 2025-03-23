package com.anubhavauth.washup.ui.theme

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.anubhavauth.washup.WashUpViewModel

@Composable
fun Checkout(
    modifier: Modifier = Modifier,
    washUpViewModel: WashUpViewModel,
    navController: NavController
) {
    val itemizedList by washUpViewModel.itemizedBill
    LazyColumn {
        items(itemizedList.keys.toList()){ item ->
            Text("$item pc: ${itemizedList[item]}")
        }
    }
}
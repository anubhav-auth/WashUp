package com.anubhavauth.washup

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class WashUpViewModel : ViewModel() {

    var totalPrice = mutableIntStateOf(0)

    @SuppressLint("MutableCollectionMutableState")
    val itemizedBill = mutableStateOf(hashMapOf<String, Int>())
}
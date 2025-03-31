package com.anubhavauth.washup.entities

import java.time.LocalDateTime


data class Orders(
    val orderId: String? = null,

    val user: User? = null,

    val status: String? = null,
    val orderDate: LocalDateTime? = null,
    val items: String? = null,
    val totalAmount: Double? = null,
    val paymentStatus: String? = null,
)

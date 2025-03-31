package com.anubhavauth.washup.api

import com.anubhavauth.washup.entities.Orders
import com.anubhavauth.washup.entities.User
import kotlinx.coroutines.flow.Flow

interface WashUpRepo {

    suspend fun registerUser(user: User): Flow<Response<String>>
    suspend fun loginUser(email: String, password: String): Flow<Response<String>>
    suspend fun verifyOtp(email: String, otp: String): Flow<Response<String>>
    suspend fun resendOtp(email: String): Flow<Response<String>>

    suspend fun createOrder(userId: String, order: Orders): Flow<Response<String>>
    suspend fun getUserOrders(userId: String): Flow<Response<List<Orders>>>
    suspend fun getOrderById(orderId: String): Flow<Response<Orders>>
    suspend fun updateOrderStatus(orderId: String, status: String): Flow<Response<String>>
}
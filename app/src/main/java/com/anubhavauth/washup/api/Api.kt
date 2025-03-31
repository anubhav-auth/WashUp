package com.anubhavauth.washup.api

import com.anubhavauth.washup.entities.Orders
import com.anubhavauth.washup.entities.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    companion object {
        const val BASE_URL_WASHUP =
            "https://your-api-base-url.com/"
    }

    @POST("api/user/register")
    suspend fun registerUser(@Body user: User): String

    @POST("api/user/login")
    suspend fun loginUser(
        @Query("email") email: String,
        @Query("password") password: String
    ): String

    @POST("api/user/verify-otp")
    suspend fun verifyOtp(
        @Query("email") email: String,
        @Query("otp") otp: String
    ): String

    @POST("api/user/resend-otp")
    suspend fun resendOtp(@Query("email") email: String): String

    @POST("api/order/create")
    suspend fun createOrder(@Query("userId") userId: String, @Body order: Orders): String

    @GET("api/order/user/{userId}")
    suspend fun getUserOrders(@Path("userId") userId: String): List<Orders>

    @GET("api/order/{orderId}")
    suspend fun getOrderById(@Path("orderId") orderId: String): Orders

    @PUT("api/order/update-status")
    suspend fun updateOrderStatus(
        @Query("orderId") orderId: String,
        @Query("status") status: String
    ): String
}

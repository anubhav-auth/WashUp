package com.anubhavauth.washup.api

import com.anubhavauth.washup.entities.Orders
import com.anubhavauth.washup.entities.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WashUpRepoImpl(private val api: Api) : WashUpRepo {

    override suspend fun registerUser(user: User): Flow<Response<String>> {
        return flow {
            val responseFromApi = try {
                api.registerUser(user)
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Response.Error(message = "Error creating user"))
                return@flow
            }
            emit(Response.Success(responseFromApi))
        }
    }

    override suspend fun loginUser(email: String, password: String): Flow<Response<String>> {
        return flow {
            val responseFromApi = try {
                api.loginUser(email, password)
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Response.Error(message = "Error logging in user"))
                return@flow
            }
            emit(Response.Success(responseFromApi))
        }
    }

    override suspend fun verifyOtp(email: String, otp: String): Flow<Response<String>> {
        return flow {
            val responseFromApi = try {
                api.verifyOtp(email, otp)
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Response.Error(message = "Error verifying OTP"))
                return@flow
            }
            emit(Response.Success(responseFromApi))
        }
    }

    override suspend fun resendOtp(email: String): Flow<Response<String>> {
        return flow {
            val responseFromApi = try {
                api.resendOtp(email)
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Response.Error(message = "Error resending OTP"))
                return@flow
            }
            emit(Response.Success(responseFromApi))
        }
    }

    override suspend fun createOrder(userId: String, order: Orders): Flow<Response<String>> {
        return flow {
            val responseFromApi = try {
                api.createOrder(userId, order)
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Response.Error(message = "Error creating order"))
                return@flow
            }
            emit(Response.Success(responseFromApi))
        }
    }

    override suspend fun getUserOrders(userId: String): Flow<Response<List<Orders>>> {
        return flow {
            val responseFromApi = try {
                api.getUserOrders(userId)
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Response.Error(message = "Error fetching user orders"))
                return@flow
            }
            emit(Response.Success(responseFromApi))
        }
    }

    override suspend fun getOrderById(orderId: String): Flow<Response<Orders>> {
        return flow {
            val responseFromApi = try {
                api.getOrderById(orderId)
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Response.Error(message = "Error fetching order by ID"))
                return@flow
            }
            emit(Response.Success(responseFromApi))
        }
    }

    override suspend fun updateOrderStatus(
        orderId: String,
        status: String
    ): Flow<Response<String>> {
        return flow {
            val responseFromApi = try {
                api.updateOrderStatus(orderId, status)
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Response.Error(message = "Error updating order status"))
                return@flow
            }
            emit(Response.Success(responseFromApi))
        }
    }
}

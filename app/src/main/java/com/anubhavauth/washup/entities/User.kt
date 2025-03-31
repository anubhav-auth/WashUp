package com.anubhavauth.washup.entities

import java.time.LocalDateTime

data class User(
    private val id: String? = null,
    private val name: String? = null,
    private val email: String? = null,
    private val phone: String? = null,
    private val password: String? = null,
    private val address: String? = null,
    private val role: String? = null,
    private val createdAt: LocalDateTime? = null,
    private val orders: List<Orders>? = null
)

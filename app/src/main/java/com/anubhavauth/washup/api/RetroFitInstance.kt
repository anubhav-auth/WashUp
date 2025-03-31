package com.anubhavauth.washup.api

import com.anubhavauth.washup.api.Api.Companion.BASE_URL_WASHUP
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitInstance {

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(interceptor)
        .build()

    val api: Api = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL_WASHUP)
        .client(client)
        .build()
        .create(Api::class.java)
}
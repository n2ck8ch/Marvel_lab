package com.example.marvellab

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import java.util.Date

object MarvelApiClient {
    private const val BASE_URL = "https://gateway.marvel.com/"
    private const val PUBLIC_KEY = "3f7241993afaabc1a30db803b828303e"
    private const val PRIVATE_KEY = "640aac64b49c556722722ba28b238024ad90423c"

    private fun generateHash(ts: String): String {
        val input = "$ts$PRIVATE_KEY$PUBLIC_KEY"
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

    private val apiService: MarvelApiService = run {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        retrofit.create(MarvelApiService::class.java)
    }

    fun getAuthParams(): Map<String, String> {
        val ts = Date().time.toString()
        val hash = generateHash(ts)
        return mapOf(
            "ts" to ts,
            "apikey" to PUBLIC_KEY,
            "hash" to hash
        )
    }

    // Возвращаем уже созданный apiService
    fun getApiService(): MarvelApiService {
        return apiService
    }
}

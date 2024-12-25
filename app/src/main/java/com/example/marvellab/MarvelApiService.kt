package com.example.marvellab

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApiService {
    @GET("v1/public/characters")
    suspend fun getCharacters(
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): MarvelResponse

    @GET("v1/public/characters/{characterId}")
    suspend fun getCharacterDetail(
        @Path("characterId") characterId: String,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    ): MarvelResponse
}

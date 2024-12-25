package com.example.marvellab.data

import com.example.marvellab.Character
import com.example.marvellab.MarvelApiClient
import com.example.marvellab.MarvelApiService

class MarvelRepository(private val apiService: MarvelApiService) {

    // Метод для получения списка персонажей
    suspend fun getCharacters(limit: Int = 20, offset: Int = 0): List<Character> {
        // Получаем параметры для авторизации
        val params = MarvelApiClient.getAuthParams()

        // Вызов API для получения персонажей
        val response = apiService.getCharacters(
            ts = params["ts"]!!,
            apiKey = params["apikey"]!!,
            hash = params["hash"]!!,
            limit = limit,
            offset = offset
        )

        // Возвращаем список персонажей
        return response.data.results
    }

    // Метод для получения подробностей персонажа по его ID
    suspend fun getCharacterDetail(characterId: Int): Character? {
        // Получаем параметры для авторизации
        val params = MarvelApiClient.getAuthParams()

        // Вызов API для получения подробностей персонажа
        val response = apiService.getCharacterDetail(
            characterId = characterId.toString(),
            ts = params["ts"]!!,
            apiKey = params["apikey"]!!,
            hash = params["hash"]!!
        )

        // Возвращаем первого персонажа из результатов (если он есть)
        return response.data.results.firstOrNull()
    }
}

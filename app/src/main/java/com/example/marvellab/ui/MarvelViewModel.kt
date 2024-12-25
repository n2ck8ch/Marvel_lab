package com.example.marvellab.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marvellab.data.MarvelRepository  // Импортируем MarvelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.marvellab.data.Character

class MarvelViewModel(private val repository: MarvelRepository) : ViewModel() {

    // Инициализируем свойство с пустым списком с помощью MutableStateFlow
    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters

    // Пример функции для обновления списка персонажей
    fun setCharacters(newCharacters: List<Character>) {
        _characters.value = newCharacters
    }

    // Загрузка списка персонажей (пока не реализовано)
    fun fetchCharacters() {
        // Тут будет логика для загрузки персонажей из репозитория
        TODO("Not yet implemented")
    }

    // Фабрика для создания MarvelViewModel с зависимостями
    class Factory(private val marvelRepository: MarvelRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MarvelViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MarvelViewModel(marvelRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

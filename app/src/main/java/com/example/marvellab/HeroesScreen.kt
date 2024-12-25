package com.example.marvellab.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.marvellab.R
import com.example.marvellab.data.Character

@Composable
fun HeroesScreen(
    navController: NavController,
    viewModel: MarvelViewModel // Здесь используем правильный MarvelViewModel
) {
    // Загружаем список персонажей из ViewModel
    val characters = viewModel.characters.collectAsState().value

    // Загружаем данные, если они еще не загружены
    LaunchedEffect(Unit) {
        viewModel.fetchCharacters()
    }

    // Заголовок экрана
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Персонажи Marvel",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Список персонажей
        LazyColumn {
            items(characters) { character: Character -> // Указываем тип явно
                HeroItem(character = character, navController = navController)
            }
        }
    }
}

@Composable
fun HeroItem(character: Character, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                // Переход к экрану с деталями героя, передаем имя персонажа
                navController.navigate("heroDetail/${character.name}")
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Изображение персонажа (используем пример изображения)
        Image(
            painter = painterResource(id = R.drawable.spiderman_image), // Это пример, замените на реальное изображение
            contentDescription = character.name,
            modifier = Modifier.size(60.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Информация о персонаже
        Column {
            Text(text = character.name, style = MaterialTheme.typography.bodyMedium)
            Text(text = character.description, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHeroesScreen() {
    // Передаем navController для preview
    HeroesScreen(
        navController = rememberNavController(),
        viewModel = MarvelViewModel(repository = TODO()) // Используем правильный ViewModel
    )
}
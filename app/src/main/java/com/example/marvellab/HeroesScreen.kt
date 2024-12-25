package com.example.marvellab

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.*

data class Hero(val name: String, val imageRes: Int, val description: String)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HeroesScreen(navController: NavController) {
    // Список героев для примера
    val heroes = listOf(
        Hero("Человек-паук", R.drawable.spiderman_image, "Супергерой с паучьими способностями."),
        Hero("Железный человек", R.drawable.ironman_image, "Гениальный миллиардер и изобретатель."),
        Hero("Тор", R.drawable.thor_image, "Бог Грома."),
        Hero("Халк", R.drawable.hulk_image, "Сила, которой не остановить."),
        Hero("Капитан Америка", R.drawable.captain_america_image, "Суперсолдат и лидер.")
    )

    // Контейнер для всего содержимого
    Column(modifier = Modifier.fillMaxSize()) {
        // Логотип Marvel в верхней части экрана
        Image(
            painter = painterResource(id = R.drawable.marvel_logo), // Логотип Marvel
            contentDescription = "Marvel Logo",
            modifier = Modifier
                .padding(top = 16.dp)
                .size(150.dp)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop
        )

        // Текст "Choose your hero"
        Text(
            text = "Choose your hero",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black,
            modifier = Modifier
                .padding(top = 8.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp)) // Отступ между текстом и пагером

        // Стартуем пагер с одним героем на экране
        HorizontalPager(
            count = heroes.size, // Количество героев
            modifier = Modifier.fillMaxSize(),
            state = rememberPagerState() // Состояние пагера
        ) { page ->
            val hero = heroes[page] // Получаем героя для текущей страницы

            // Контент для каждого героя
            HeroCard(hero, navController)
        }
    }
}

@Composable
fun HeroCard(hero: Hero, navController: NavController) {
    // Карточка героя
    Card(
        modifier = Modifier
            .width(250.dp) // Ширина карточки
            .padding(8.dp)
            .clickable {
                // Навигация на экран с деталями героя
                navController.navigate("heroDetail/${hero.name}")
            },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Изображение героя
            Image(
                painter = painterResource(id = hero.imageRes),
                contentDescription = hero.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Информация о герое
            Text(
                text = hero.name,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = hero.description,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHeroesScreen() {
    val navController = rememberNavController()
    HeroesScreen(navController = navController)
}

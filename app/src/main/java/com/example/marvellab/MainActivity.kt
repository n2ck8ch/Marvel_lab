package com.example.marvellab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color // Импортируем Color для серого фона
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.painterResource // Для загрузки изображений
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marvellab.ui.theme.MarvelLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Используем стандартную тему MaterialTheme
            MaterialTheme {
                // Оборачиваем в Surface и изменяем фон на серый
                Surface(color = Color.Gray) { // Устанавливаем серый фон
                    // Настройка навигации
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "heroesList") {
                        composable("heroesList") {
                            HeroesScreen(navController) // Отображаем экран с героями
                        }
                        composable("heroDetail/{heroName}") { backStackEntry ->
                            val heroName = backStackEntry.arguments?.getString("heroName")
                            if (heroName != null) {
                                HeroDetailScreen(heroName = heroName, navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        HeroesScreen(navController = rememberNavController()) // Передаем корректный навигационный контроллер
    }
}

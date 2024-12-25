package com.example.marvellab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel // Импортируем viewModel
import com.example.marvellab.data.MarvelRepository
import com.example.marvellab.ui.MarvelViewModel
import com.example.marvellab.ui.HeroesScreen
import com.example.marvellab.ui.HeroDetailScreen
val marvelRepository = MarvelRepository(MarvelApiClient.getApiService())

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(color = Color.Gray) {
                    val navController = rememberNavController()
                    val marvelRepository = MarvelRepository(MarvelApiClient.getApiService())

                    // Используем фабрику для создания ViewModel
                    val viewModel: MarvelViewModel = viewModel(
                        factory = MarvelViewModel.Factory(marvelRepository)
                    )

                    NavHost(navController = navController, startDestination = "heroesList") {
                        composable("heroesList") {
                            // Передаем viewModel в HeroesScreen
                            HeroesScreen(
                                navController = navController,
                                viewModel = viewModel
                            )
                        }
                        composable("heroDetail/{heroName}") { backStackEntry ->
                            val heroName = backStackEntry.arguments?.getString("heroName") ?: ""
                            HeroDetailScreen(heroName = heroName, navController = navController)
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
    val navController = rememberNavController()
    val marvelRepository = MarvelRepository(MarvelApiClient.getApiService())

    // Используем фабрику для создания ViewModel
    val viewModel: MarvelViewModel = viewModel(
        factory = MarvelViewModel.Factory(marvelRepository)
    )

    HeroesScreen(navController = navController, viewModel = viewModel)
}

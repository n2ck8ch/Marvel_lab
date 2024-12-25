package com.example.marvellab

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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

data class HeroDetail(
    val name: String,
    val imageRes: Int,
    val description: String,
    val details: String
)

@Composable
fun HeroDetailScreen(heroName: String, navController: NavController) {
    // Список всех героев с описаниями на русском языке
    val heroDetails = listOf(
        HeroDetail(
            "Человек-паук",
            R.drawable.spiderman_image,
            "Супергерой с паучьими способностями.",
            "Человек-паук — вымышленный супергерой, появляющийся в американских комиксах, издаваемых Marvel Comics. Был создан писателем Станом Ли и художником Стивом Дитко, впервые появился в Amazing Fantasy #15."
        ),
        HeroDetail(
            "Железный человек",
            R.drawable.ironman_image,
            "Гениальный миллиардер и изобретатель.",
            "Железный человек — вымышленный супергерой, появляющийся в американских комиксах, издаваемых Marvel Comics. Был создан писателем Станом Ли, сценаристом Ларри Либером и художником Доном Хеком."
        ),
        HeroDetail(
            "Тор",
            R.drawable.thor_image,
            "Бог Грома.",
            "Тор — бог грома и молнии из скандинавской мифологии, который использует молот. Персонаж Тора является одним из самых известных и долговечных супергероев Marvel."
        ),
        HeroDetail(
            "Халк",
            R.drawable.hulk_image,
            "Сила, которой не остановить.",
            "Халк — персонаж Marvel Comics, обладающий невероятной силой, которая растет с его гневом. Создан Стэном Ли и Джеком Керби."
        ),
        HeroDetail(
            "Капитан Америка",
            R.drawable.captain_america_image,
            "Суперсолдат и лидер.",
            "Капитан Америка — супергерой Marvel, сражающийся за справедливость и свободу, используя свой щит и выдающиеся боевые навыки."
        )
    )

    // Ищем героя по имени
    val hero = heroDetails.find { it.name == heroName }

    if (hero != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Кнопка для возврата на предыдущий экран
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.Start)
            ) {
                Text(text = "Назад")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Изображение героя
            Image(
                painter = painterResource(id = hero.imageRes),
                contentDescription = hero.name,
                modifier = Modifier.size(200.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Имя героя
            Text(
                text = hero.name,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Описание героя
            Text(
                text = hero.description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Детали героя
            Text(
                text = hero.details,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )
        }
    } else {
        // Если герой не найден
        Text(text = "Hero not found")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHeroDetailScreen() {
    val navController = rememberNavController()
    HeroDetailScreen(heroName = "Человек-паук", navController = navController)
}

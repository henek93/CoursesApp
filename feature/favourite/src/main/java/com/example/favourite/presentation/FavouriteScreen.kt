package com.example.favourite.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.entity.Course
import com.example.ui.components.CourseCard
import com.example.utils.toDate

private val sampleCourses = listOf(
    Course(
        id = "100",
        title = "Java-разработчик с нуля",
        text = "Освойте backend-разработку и программирование на Java, фреймворки Spring и Maven, работу с базами данных и API. Создайте свой собственный проект, собрав портфолио и став востребованным специалистом для любой IT компании.",
        price = "999",
        rate = 4.9f,
        startDate = "2024-05-22".toDate(),
        hasLike = false,
        publishDate = "2024-02-02".toDate()
    ),
    Course(
        id = "101",
        title = "3D-дженералист",
        text = "Освой профессию 3D-дженералиста и стань универсальным специалистом, который умеет создавать 3D-модели, текстуры и анимации, а также может строить карьеру в геймдеве, кино, рекламе или дизайне.",
        price = "12 000",
        rate = 3.9f,
        startDate = "2024-09-10".toDate(),
        hasLike = false,
        publishDate = "2024-01-20".toDate()
    ),
    Course(
        id = "102",
        title = "Python Advanced. Для продвинутых",
        text = "Вы узнаете, как разрабатывать гибкие и высокопроизводительные серверные приложения на языке Kotlin. Преподаватели на вебинарах покажут пример того, как разрабатывается проект маркетплейса: от идеи и постановки задачи – до конечного решения",
        price = "1299",
        rate = 4.3f,
        startDate = "2024-10-12".toDate(),
        hasLike = true,
        publishDate = "2024-08-10".toDate()
    ),
    Course(
        id = "103",
        title = "Системный аналитик",
        text = "Освоите навыки системной аналитики с нуля за 9 месяцев. Будет очень много практики на реальных проектах, чтобы вы могли сразу стартовать в IT.",
        price = "1199",
        rate = 4.5f,
        startDate = "2024-04-15".toDate(),
        hasLike = false,
        publishDate = "2024-01-13".toDate()
    ),
    Course(
        id = "104",
        title = "Аналитик данных",
        text = "В этом уроке вы узнаете, кто такой аналитик данных и какие задачи он решает. А главное — мы расскажем, чему вы научитесь по завершении программы обучения профессии «Аналитик данных».",
        price = "899",
        rate = 4.7f,
        startDate = "2024-06-20".toDate(),
        hasLike = false,
        publishDate = "2024-03-12".toDate()
    )
)

@Composable
fun FavouriteScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {

        Text(
            "Избранное",
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onBackground
        )

        LazyColumn {
            items(count = sampleCourses.size) { index ->
                CourseCard(onCardClick = {}, course = sampleCourses[index])
            }
        }
    }
}
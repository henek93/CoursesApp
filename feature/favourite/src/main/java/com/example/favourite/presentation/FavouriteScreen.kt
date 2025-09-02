package com.example.favourite.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.entity.Course
import com.example.ui.components.CourseCard
import com.example.utils.toDate


@Composable
fun FavouriteScreen(
    viewModel: FavouriteViewModel = hiltViewModel<FavouriteViewModel>()
) {

    val screenState by viewModel.screenState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            "Избранное",
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onBackground
        )

        when (screenState) {
            is FavouriteScreenState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        (screenState as FavouriteScreenState.Error).message,
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            FavouriteScreenState.Initial -> {}
            FavouriteScreenState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is FavouriteScreenState.Success -> {
                FavouriteContent(list = (screenState as FavouriteScreenState.Success).data)
            }
        }
    }

}

@Composable
fun FavouriteContent(
    list: List<Course>
) {

    if (list.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                "Вы еще не добавили курсов в избранное",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    } else {
        LazyColumn {
            items(count = list.size) { index ->
                CourseCard(onCardClick = {}, course = list[index], onFavouriteClick = {})
            }
        }
    }

}

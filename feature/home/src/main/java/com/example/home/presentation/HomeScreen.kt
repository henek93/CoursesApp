package com.example.home.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.entity.Course
import com.example.home.presentation.components.HomeErrorScreen
import com.example.ui.components.AppIcon
import com.example.ui.components.AppIcons
import com.example.ui.components.CourseCard

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    navigateDetailCourse: () -> Unit,
    viewModel: HomViewModel = hiltViewModel<HomViewModel>()
) {
    val screenState = viewModel.screenState.collectAsState()
    val loadingIds by viewModel.loadingIds.collectAsState()
    when (screenState.value) {
        HomeScreenState.Initial -> {}
        HomeScreenState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is HomeScreenState.Succsed -> {
            HomeContent(
                list = (screenState.value as HomeScreenState.Succsed).list,
                onFavouriteClick = { viewModel.changeHasLike(it) },
                loadingIds = loadingIds,
                onSortClick = {
                    viewModel.sortByPublishDate()
                }
            )

        }

        is HomeScreenState.Error -> {
            HomeErrorScreen(
                errorText = (screenState.value as HomeScreenState.Error).message
            )
        }
    }
}

@Composable
fun HomeContent(
    list: List<Course>,
    onFavouriteClick: (Course) -> Unit,
    loadingIds: Set<String>,
    onSortClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = {
                        onSortClick()
                    }),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    "По дате добавления",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.width(4.dp))
                AppIcon(
                    icon = AppIcons.DoubleArrow,
                    color = MaterialTheme.colorScheme.primary,
                    size = 20.sp
                )
            }


        }
        items(count = list.size) { index ->
            CourseCard(
                onCardClick = {},
                course = list[index],
                onFavouriteClick = {
                    onFavouriteClick(list[index])
                },
                loadingIds = loadingIds
            )
        }
    }
}
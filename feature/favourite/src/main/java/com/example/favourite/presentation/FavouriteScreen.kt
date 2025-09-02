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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.entity.Course
import com.example.localization.R
import com.example.ui.components.CourseCard
import com.example.utils.toDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


@Composable
fun FavouriteScreen(
    viewModel: FavouriteViewModel = hiltViewModel<FavouriteViewModel>()
) {

    val screenState by viewModel.screenState.collectAsState()
    val loadingIds by viewModel.loadingIds.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            stringResource(R.string.favourite_title),
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
                FavouriteContent(
                    list = (screenState as FavouriteScreenState.Success).data,
                    loadingIds = loadingIds,
                    onFavouriteClick = { course ->
                        viewModel.changeHasLike(course)
                    })
            }
        }
    }

}

@Composable
fun FavouriteContent(
    list: List<Course>,
    loadingIds: Set<String>,
    onFavouriteClick: (Course) -> Unit
) {
    if (list.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                stringResource(R.string.favourite_empty_message),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    } else {
        LazyColumn {
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

}

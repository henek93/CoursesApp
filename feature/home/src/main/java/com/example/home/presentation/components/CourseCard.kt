package com.example.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.R
import com.example.ui.components.AppIcon
import com.example.ui.components.AppIcons

@Composable
fun CourseCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

            Image(
                painter = painterResource(R.drawable.im_java),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
                    .clip(CircleShape)
                    .background(
                        Color.Black.copy(alpha = 0.3f)
                    )
            ) {
                Icon(
                    Icons.Default.BookmarkBorder,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(36.dp)
                        .padding(6.dp)
                )
            }


            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            ) {

                RatingAndData()

                CourseDescription()
            }
        }

    }
}

@Composable
fun RatingAndData(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Rating()
        Text(text = "22 Мар 2024", fontSize = 16.sp, color = Color.White)
    }
}


@Composable
fun CourseDescription(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(15.dp)
    ) {

        Text(
            text = "Java-разработчик с нуля",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = "Освойте backend-разработку и программирование на Java, фреймворки...",
            fontSize = 16.sp,
            color = Color.White
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "999 ₽",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Подробнее ",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium
                )
                Icon(
                    Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(16.dp)
                )
            }

        }
    }
}


@Composable
fun Rating(rating: Float = 4.9f) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "Rating",
            tint = Color.Green,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = "$rating", fontSize = 15.sp, color = Color.White)
    }
}

package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.entity.Course
import com.example.localization.R as LocalizationR
import com.example.ui.R
import com.example.utils.formatToRussian

@Composable
fun CourseCard(
    onCardClick: () -> Unit,
    onFavouriteClick: () -> Unit,
    course: Course,
    loadingIds: Set<String>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .padding(vertical = 10.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

            Image(
                painter = painterResource(getCourseImage(course)),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(onClick = {
                        onCardClick()
                    })
            )

            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
                    .clip(CircleShape)
                    .background(Color.Black.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                if (loadingIds.contains(course.id)) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        strokeWidth = 2.dp,
                        modifier = Modifier
                            .size(34.dp)
                            .padding(6.dp)
                    )
                } else {
                    Icon(
                        if (course.hasLike) Icons.Filled.Bookmark else Icons.Default.BookmarkBorder,
                        contentDescription = null,
                        tint = if (course.hasLike) MaterialTheme.colorScheme.primary else Color.White,
                        modifier = Modifier
                            .size(36.dp)
                            .padding(6.dp)
                            .clickable(onClick = { onFavouriteClick() })
                    )
                }
            }


            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .clickable(onClick = {
                        onCardClick()
                    })
            ) {

                RatingAndData(
                    course = course
                )

                CourseDescription(
                    course = course
                )
            }
        }

    }
}

@Composable
fun RatingAndData(
    modifier: Modifier = Modifier,
    course: Course
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Rating(
            rating = course.rate
        )
        Box(
            modifier = Modifier.background(
                brush = getGradientGlassBlur(),
                shape = RoundedCornerShape(30.dp)
            )
        ) {
            Text(
                text = course.publishDate,
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier.padding(10.dp)
            )
        }


    }
}


fun getCourseImage(course: Course): Int {
    return if (course.title.contains("Java")) {
        R.drawable.im_java
    } else if (course.title.contains("3D")) {
        R.drawable.im_3d
    } else if (course.title.contains("Python")) {
        R.drawable.im_python
    } else {
        R.drawable.im_not
    }
}

@Composable
fun CourseDescription(
    modifier: Modifier = Modifier,
    course: Course
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(15.dp)
    ) {

        Text(
            text = course.title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = course.text,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${course.price} ₽",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    stringResource(LocalizationR.string.home_more_details),
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
        modifier = Modifier
            .background(
                brush = getGradientGlassBlur(),
                shape = RoundedCornerShape(30.dp)
            )
            .padding(8.dp)
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


fun getGradientGlassBlur() = Brush.radialGradient(
    colors = listOf(
        Color(0x7032333A),
        Color(0xFFF8F8FF).copy(alpha = 0.55f),
        Color(0xFFF0F0F0).copy(alpha = 0.4f),
        Color(0xFFE8E8E8).copy(alpha = 0.35f),
        Color(0xFFE8E8E8).copy(alpha = 0.25f),
    ),
    radius = 80f,
    center = Offset(0.3f, 0.3f)
)
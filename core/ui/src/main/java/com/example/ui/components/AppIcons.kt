package com.example.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.ui.R

object AppIcons {
    const val VK = "\uE900"
    const val House = "\uE901"
    const val Person = "\uE902"
    const val BookMark = "\uE903"
    const val Filter = "\uE904"
    const val ArrowBack = "\uE905"
    const val OK = "\uE906"
    const val DoubleArrow = "\uE907"
}

@Composable
fun AppIcon(
    icon: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    size: TextUnit = 24.sp
) {
    Text(
        text = icon,
        fontFamily = FontFamily(
            Font(R.font.app_icons)
        ),
        fontSize = size,
        color = color,
        modifier = modifier
    )
}
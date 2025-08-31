package com.example.authorization.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.AppIcon
import com.example.ui.components.AppIcons

@Composable
fun ButtonsSocialNetwork(
    modifier: Modifier = Modifier,
    onVkClick: () -> Unit,
    onOkClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            modifier = Modifier
                .weight(1f)
                .height(48.dp),
            onClick = onVkClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xff2683ED))
        ) {
            AppIcon(
                AppIcons.VK,
                color = Color.White
            )
        }

        Button(
            modifier = Modifier
                .weight(1f)
                .height(48.dp),
            onClick = onOkClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues(0.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFFF98509),
                                Color(0xFFF95D00)
                            )
                        ),
                        shape = MaterialTheme.shapes.small
                    ),
                contentAlignment = Alignment.Center
            ) {
                AppIcon(
                    AppIcons.OK,
                    color = Color.White,
                    size = 32.sp
                )
            }
        }
    }
}
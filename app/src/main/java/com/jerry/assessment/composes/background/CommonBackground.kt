package com.jerry.assessment.composes.background

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.jerry.assessment.R

@Composable
fun CommonBackground(
    content: @Composable () -> Unit,
) {
    val gradientColors = Brush.linearGradient(
        colors = listOf(
            Color(0XFFF0F0FE),
            Color(0XFFFFFFFF)
        )
    )

    Box(modifier = Modifier.background(gradientColors))
    {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.bg),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        content()
    }
}
package com.jerry.assessment.screen.community.components.giveGet

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CircleArrow(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    icon: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            //.offset(y = (-16.dp), x = (16.dp))
            .size(66.dp)
            .border(1.dp, color, CircleShape)
            .padding(4.dp)
            .clip(CircleShape)
            .border(1.dp, color, CircleShape)
            .padding(4.dp)
            .clip(CircleShape)
            .background(color)
        ,
        contentAlignment = Alignment.Center
    ) {
        icon()
    }
}

@Preview
@Composable
private fun CircleArrowPreview(){
    CircleArrow(
        icon = {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "To Give",
                tint = Color.White
            )
        }
    )
}
package com.jerry.assessment.screen.community.components.requestItem

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RequestItemTail(
    text: String = "12 Feb 2024",
    tickOnClick: () -> Unit = {},
    crossOnClick:() -> Unit = {}
) {
    val gradientColors = Brush.linearGradient(
        colors = listOf(
            Color(0XFFFDAE9D),
            Color(0XFFE374CA)
        )
    )
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = text,
            //fontFamily = FontFamily(Font(Res.font.Inter)),
            lineHeight = 15.sp,
            fontWeight = FontWeight(400),
            fontSize = 10.sp,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            FilledIconButton(
                modifier = Modifier.size(24.dp),
                colors = IconButtonDefaults.filledTonalIconButtonColors(
                    containerColor = Color.Black
                ),
                onClick = tickOnClick
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Yes",
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            FilledIconButton(
                modifier = Modifier
                    .background(gradientColors, shape = CircleShape)
                    .then(Modifier.size(24.dp)),
                colors = IconButtonDefaults.filledTonalIconButtonColors(
                    containerColor = Color.Transparent
                ),
                onClick = crossOnClick
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "No",
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

    }
}

@Preview
@Composable
private fun RequestItemTailPreview(){
    RequestItemTail()
}
package com.jerry.assessment.composes.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommonOutlineButton(
    modifier: Modifier = Modifier,
    text : String,
    onClick : () -> Unit = {},
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary,
        ),
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            //fontFamily = FontFamily(Font(Res.font.Poppins_Regular)),
            color = MaterialTheme.colorScheme.primary,
            lineHeight = 20.sp,
            fontWeight = FontWeight(400)
        )
    }
}
package com.jerry.assessment.composes.placeholder

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun PlaceHolderText(text: String) {
    Text(
        text = text,
        fontSize = 12.sp,
        //fontFamily = FontFamily(Font(Res.font.Inter)),
        lineHeight = 16.sp,
        fontWeight = FontWeight(400)
    )
}


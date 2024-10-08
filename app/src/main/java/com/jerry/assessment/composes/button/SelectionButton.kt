package com.jerry.assessment.composes.button

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.sp

@Composable
fun SelectionButton(
    onClick: () -> Unit = {},
    selected : Boolean,
    text: String
) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) Color(0xFFD4C2FC) else  Color(0xFFE8E8EA),
            contentColor =  if (selected) Color(0xFF6A34FD) else  Color(0xFF686675),
        )
    ) {
        Text(
            text= text,
            //fontFamily = FontFamily(Font(Res.font.Poppins)),
            lineHeight = 20.sp,
            fontWeight = FontWeight(400),
            fontSize = 13.sp,
        )
    }
}

@Preview
@Composable
private fun SelectionButtonSelectedPreview(){
    SelectionButton(
        text = "Selected",
        selected = true
    )
}

@Preview
@Composable
private fun SelectionButtonNonSelectedPreview(){
    SelectionButton(
        text = "Non Selected",
        selected = false
    )
}
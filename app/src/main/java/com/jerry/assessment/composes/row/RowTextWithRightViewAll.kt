package com.jerry.assessment.composes.row

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun RowTextWithRightViewAll(
    text: String,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Text(
            text = text,
            color = Color(0XFF14141F),
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight(400),
            //fontFamily = FontFamily(Font(Res.font.Poppins_Regular))
        )
        Spacer(modifier = Modifier.weight(1f))
        Row (
            modifier = Modifier.padding(vertical = 8.dp).clickable{
                onClick()
            },
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "View All",
                color = Color(0xFF69718A),
                fontSize = 12.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(400),
                //fontFamily = FontFamily(Font(Res.font.Inter))
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "View All",
                tint = Color(0xFF69718A)
            )
        }
    }
}


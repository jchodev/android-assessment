package com.jerry.assessment.composes.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DoubleBorderIconButton(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit = {},
    icon: @Composable () -> Unit = { },
    text: String = "Settle"
) {
    Box(
        modifier = modifier
            .border(1.dp, color, MaterialTheme.shapes.extraLarge)
            .padding(4.dp)
            .clip(MaterialTheme.shapes.extraLarge)
            .background(color)
            .clickable{
                onClick()
            }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center)
        {
            icon()
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 12.sp,
                //fontFamily =
                fontWeight = FontWeight(400),
                lineHeight = 16.sp,
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }

}

@Preview
@Composable
private fun DoubleBorderIconButtonPreview(){
    DoubleBorderIconButton(
        icon = {
            Icon(
                modifier = Modifier.size(12.dp),
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = Color.White,
            )
        }
    )
}

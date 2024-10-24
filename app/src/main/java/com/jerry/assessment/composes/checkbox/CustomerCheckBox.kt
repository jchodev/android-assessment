package com.jerry.assessment.composes.checkbox

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun CustomerCheckBox(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onCheckedChange: (() -> Unit)? = null,
    shape: Shape = CircleShape
) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .size(24.dp)
            //color = Color(0XFFF4EBFF),
            //box-shadow: 0px 0px 0px 4px #F4EBFF;
            .border(width = 3.dp, color = Color(0XFFF4EBFF), shape = shape)
            .clip(shape)
            .background(MaterialTheme.colorScheme.primary)
            .clickable {
                onCheckedChange?.invoke()
            }
    ) {
        if (!isChecked){
            Box(
                modifier = Modifier.fillMaxSize()
                    .border(width = 4.dp, color = MaterialTheme.colorScheme.primary, shape = shape)
                    .clip(shape)
                    .background(MaterialTheme.colorScheme.onPrimary)
            ) {

            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize().padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Preview
@Composable
private fun CustomerCheckBoxCircleSelectedPreview(){
    CustomerCheckBox(isChecked = true)
}

@Preview
@Composable
private fun CustomerCheckBoxCircleNonSelectedPreview(){
    CustomerCheckBox(isChecked = false)
}

@Preview
@Composable
private fun CustomerCheckBoxSquareSelectedPreview(){
    CustomerCheckBox(isChecked = true,
        shape = MaterialTheme.shapes.small
    )
}

@Preview
@Composable
private fun CustomerCheckBoxSquareNonSelectedPreview(){
    CustomerCheckBox(
        isChecked = false,
        shape = MaterialTheme.shapes.small
    )
}

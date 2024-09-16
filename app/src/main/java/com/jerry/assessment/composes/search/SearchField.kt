package com.jerry.assessment.composes.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.jerry.assessment.R
import com.jerry.assessment.ui.theme.AppTheme

@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    hint: String,
    onSearchParamChange: (String) -> Unit,
) {
    var searchParam by remember { mutableStateOf("") }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = Color(0xFFF8F8F8))
            .border(
                border = BorderStroke(2.dp, Color.White),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        TextField(
            value = searchParam,
            onValueChange = {
                searchParam = it
                onSearchParamChange(it)
            },
            colors =
            TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF8F8F8),
                unfocusedContainerColor = Color(0xFFF8F8F8)
            ),
            leadingIcon = {

                Image(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = "Search",
                    modifier = Modifier
                        .requiredSize(size = 16.dp)
                )
            },
            placeholder = {
                Text(
                    text = hint,
                    color = Color(0xff686675),
                    lineHeight = 1.33.em,
                    style = TextStyle(fontSize = 12.sp)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(16.dp))
                .background(color = Color.White)
        )
    }
}

@Preview
@Composable
private fun SearchFieldPreview() {
    AppTheme {
        SearchField(
            hint = "Search for Names or Groups"
        ) {}
    }
}
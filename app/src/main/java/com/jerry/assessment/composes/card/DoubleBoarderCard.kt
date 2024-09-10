package com.jerry.assessment.composes.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DoubleBoarderCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(2.dp, Color.White, MaterialTheme.shapes.large)
        ,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
        ),
        shape = MaterialTheme.shapes.large
    ) {
        content()
    }
}

@Preview
@Composable
private fun DoubleBoarderCardPreview(){
    DoubleBoarderCard(
        modifier = Modifier.width(200.dp),
        content = {
            Column(modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFFEBF4EF),
                                Color(0xFFF0F5F2),
                                Color(0xFFF6F6F6),
                            )
                        )
                    )
                .padding(16.dp)) {
                Text("this is text")
                Text("this is text")
            }
        }
    )
}
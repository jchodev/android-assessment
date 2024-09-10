package com.jerry.assessment.composes.icon

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jerry.assessment.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoundedCardIcon(
    onClick: () -> Unit = {},
    icon: @Composable () -> Unit = { }
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(48.dp),
        onClick = onClick,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
        ),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            icon()
        }
    }

}

@Preview
@Composable
private fun RoundedCardIconPreview(){
    RoundedCardIcon(
        icon = {
            Icon(
                painter = painterResource(R.drawable.ic_four_dots),
                contentDescription = null,
                modifier = Modifier.size(25.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    )
}
package com.jerry.assessment.composes.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TransactionCards() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TransactionCard(
            title = "To Give",
            amount = "$500.23",
            isGive = true,
            onViewClick = { /* Handle view click */ },
            onActionClick = { /* Handle settle click */ }
        )
        TransactionCard(
            title = "To Get",
            amount = "$500.23",
            isGive = false,
            onViewClick = { /* Handle view click */ },
            onActionClick = { /* Handle remind click */ }
        )
    }
}

@Composable
fun TransactionCard(
    title: String,
    amount: String,
    isGive: Boolean,
    onViewClick: () -> Unit,
    onActionClick: () -> Unit
) {
    Card(
//        modifier = Modifier
//            .weight(1f)
//            .height(180.dp),
        modifier = Modifier.height(180.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = title, fontWeight = FontWeight.Bold)
                Icon(
                    imageVector = if (isGive) Icons.Default.KeyboardArrowUp else Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = if (isGive) Color.Blue else Color.Black,
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color.LightGray, CircleShape)
                        .padding(4.dp)
                )
            }
            Text(
                text = amount,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Placeholder for profile images
                repeat(3) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(Color.Gray, CircleShape)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "View",
                    color = Color.Blue,
                    modifier = Modifier.clickable(onClick = onViewClick)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = onActionClick,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isGive) Color.Blue else Color.White
                )
            ) {
                Text(
                    text = if (isGive) "Settle" else "Remind",
                    color = if (isGive) Color.White else Color.Black
                )
            }
        }
    }
}

@Preview
@Composable
fun TransactionCardsPreview() {
    TransactionCards()
}
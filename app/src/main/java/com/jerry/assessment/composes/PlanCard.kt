package com.jerry.assessment.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jerry.assessment.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanCard(
    modifier: Modifier = Modifier,
    day: String,
    month: String,
    title: String,
    timePeriod: String,
) {
    Card(
        modifier =
            modifier
                .fillMaxWidth()
                .border(2.dp, Color.White, RoundedCornerShape(16.dp))

        ,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
        ),
        shape = RoundedCornerShape(16.dp)

    ) {
        Row(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFEBF4EF),
                            Color(0xFFF0F5F2),
                            Color(0xFFF6F6F6),
                        )
                    )
                )
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Card(
                onClick = { /*TODO*/ },
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp,
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier
                    .background(color = Color.White)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(day, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text(month, color = Color.Gray)
                }

            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(title, fontSize = 14.sp)

                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_clock),
                        contentDescription = "New",
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(timePeriod, color = Color.Gray)
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            AvatarGroup(
                listOf(
                    R.drawable.male1,
                    R.drawable.male1,
                    R.drawable.male1,
                    R.drawable.male1,
                    R.drawable.male1,
                )
            )
        }
    }
}

@Preview
@Composable
private fun PlanCard(){
    PlanCard(
        day = "23",
        month = "Jan",
        title = "Trip to London",
        timePeriod = "12:00 - 16:00"
    )
}
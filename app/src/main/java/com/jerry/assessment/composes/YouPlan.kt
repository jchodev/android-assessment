package com.jerry.assessment.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Clock
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun YouPlan() {

    val now = Clock.System.now()
    val tz = TimeZone.currentSystemDefault()
    val today = now.toLocalDateTime(tz).date

    var selectedDay by remember { mutableStateOf(today) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(16.dp)

    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            //row
            Row( modifier = Modifier.fillMaxWidth()){
                Text(Month(selectedDay.monthNumber).toString() + " " + selectedDay.year, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = { /* TODO */ },
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF1EBFE))
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "New",
                        tint = Color(0xFF7539F5)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("New", color = Color(0xFF7539F5))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            CalendarLazyRow(
                today = today,
                onScrolledDay = {
                    selectedDay = it
                }
            )
            //upcoming plan
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()){
                Text("Upcoming Plan",fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.weight(1f))
                Row (
                    modifier = Modifier.padding(vertical = 8.dp)
                        .clickable{},
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text("View All", color = Color(0xFF69718A))
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "View All",
                        tint = Color(0xFF69718A)
                    )
                }

            }
            //upcoming plan card
            LazyRow {
                item {
                    PlanCard(
                        modifier = Modifier.width(320.dp),
                        day = "23",
                        month = "Jan",
                        title = "Trip to London",
                        timePeriod = "12:00 - 16:00"
                    )
                }
                item {
                    Spacer(modifier = Modifier.width(8.dp))
                }
                item {
                    PlanCard(
                        day = "23",
                        month = "Jan",
                        title = "Trip to London",
                        timePeriod = "12:00 - 16:00"
                    )
                }
            }



        }
    }
}

@Preview
@Composable
private fun YourPlanPreview(){
    Box(modifier = Modifier
        .background(Color.Gray)
        .padding(16.dp))
    {
        YouPlan()
    }
}
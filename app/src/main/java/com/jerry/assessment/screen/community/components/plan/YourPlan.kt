package com.jerry.assessment.screen.community.components.plan

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.unit.sp
import com.jerry.assessment.composes.CalendarLazyRow
import com.jerry.assessment.composes.card.CommonCardContainer
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun YourPlan(
    onSelectedDate: (LocalDate) -> Unit = {},
    planCardDatas: List<PlanCardData>,
) {

    val now = Clock.System.now()
    val tz = TimeZone.currentSystemDefault()
    val today = now.toLocalDateTime(tz).date

    var selectedDay by remember { mutableStateOf(today) }

   CommonCardContainer {
        Column(modifier = Modifier.padding(16.dp)) {
            //row
            Row( modifier = Modifier.fillMaxWidth()){
                //Text(Month(selectedDay.monthNumber).toString() + " " + selectedDay.year, fontWeight = FontWeight.Medium)
                Text(
                    text = "Your Plan",
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(400),
                    //fontFamily = Poppins
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = Month(selectedDay.monthNumber).toString() + " " + selectedDay.year,
                    fontSize = 12.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF686675)
                    //fontFamily = Inter
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            CalendarLazyRow(
                today = today,
                onScrolledDay = {
                    selectedDay = it
                    onSelectedDate.invoke(it)
                }
            )
            //upcoming plan
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()){
                Text("Upcoming Plan",fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.weight(1f))
                Row (
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .clickable {},
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
                itemsIndexed(items = planCardDatas) { index, item ->
                    PlanCard(
                        item = item,
                    )
                    if (index < planCardDatas.size - 1) {
                        Spacer(modifier = Modifier.width(8.dp))
                    }
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
        YourPlan(
            planCardDatas = listOf(
                PlanCardData(
                    day = "23",
                    month = "Jan",
                    title = "Trip to London",
                    timePeriod = "12:00 - 16:00",
                    avatars = listOf(
                        "https://dummyimage.com/100x100/6699cc/000",
                        "https://dummyimage.com/100x100/6699cc/000",
                        "https://dummyimage.com/100x100/6699cc/000",
                        "https://dummyimage.com/100x100/6699cc/000",
                        "https://dummyimage.com/100x100/6699cc/000",
                    )
                ),
                PlanCardData(
                    day = "23",
                    month = "Jan",
                    title = "Trip to London",
                    timePeriod = "12:00 - 16:00",
                    avatars = listOf(
                        "https://dummyimage.com/100x100/6699cc/000",
                        "https://dummyimage.com/100x100/6699cc/000",
                        "https://dummyimage.com/100x100/6699cc/000",
                        "https://dummyimage.com/100x100/6699cc/000",
                        "https://dummyimage.com/100x100/6699cc/000",
                    )
                )
            )
        )
    }
}
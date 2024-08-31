package com.jerry.assessment.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

@Composable
fun CalendarLazyRow(
    selectedDay: LocalDate,
    onSelectDay: (LocalDate) -> Unit,

    selectedBgColor: Color = Color(0xFF7539F5),
    unSelectedBgColor: Color = Color.Transparent,
    selectedTextColor: Color = Color.White,
    unSelectedTextColor: Color = Color(0xFF7539F5),
) {
    val calendarPagerState = rememberLazyListState()

    LaunchedEffect(key1 = Unit, block = {
        calendarPagerState.animateScrollToItem(
            index = calendarLocalDates().indexOf(selectedDay),
            scrollOffset = 0,
        )
    })

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        state = calendarPagerState,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(calendarLocalDates()) { date ->
            Box(
                modifier = Modifier
                    .width(44.dp)
                    .height(60.dp)
                    .clipToBounds()
                    .background(
                        color = if (date == selectedDay) selectedBgColor else unSelectedBgColor,
                        shape = RoundedCornerShape(16.dp),
                    )
                    .clickable {
                        onSelectDay(date)
                    },
                contentAlignment = Alignment.Center,
            ) {
                Column {
                    Text(
                        text = date.dayOfMonth.toString(),
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = if (date == selectedDay) selectedTextColor else unSelectedTextColor,
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                    )
                    Text(
                        text = date.dayOfWeek.name.substring(0, 3),
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = if (date == selectedDay) selectedTextColor else unSelectedTextColor,
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                    )
//                    Text(
//                        text = date.month.toString(),
//                        style = MaterialTheme.typography.labelMedium.copy(
//                            color = if (date == today) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
//                        ),
//                        modifier = Modifier.align(Alignment.CenterHorizontally),
//                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CalendarPreview() {
    val now = Clock.System.now()
    val tz = TimeZone.currentSystemDefault()
    val today = now.toLocalDateTime(tz).date

    var selectedDay by remember { mutableStateOf(today) }

    CalendarLazyRow(
        selectedDay = today,
        onSelectDay = {
            selectedDay = it
        }
    )
}

/**
 * LocalDates for List<LocalDate>
 * The last 1 year
 * The next 1 year
 */
private fun calendarLocalDates(): List<LocalDate> {
    val thisYear = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).year
    val lastYear = thisYear - 1
    val nextYear = thisYear + 1
    val dates = mutableListOf<LocalDate>()
    for (i in 0..365) {
        dates += LocalDate(thisYear, 1, 1).plus(i, DateTimeUnit.DAY)
    }
    for (i in 0..365) {
        dates += LocalDate(lastYear, 1, 1).plus(i, DateTimeUnit.DAY)
    }
    for (i in 0..365) {
        dates += LocalDate(nextYear, 1, 1).plus(i, DateTimeUnit.DAY)
    }
    return dates
}
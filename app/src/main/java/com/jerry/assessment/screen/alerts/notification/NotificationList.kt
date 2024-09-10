package com.jerry.assessment.screen.alerts.notification

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NotificationList(
    notificationItems: List<NotificationItemData> = listOf()
) {
    LazyColumn {
        itemsIndexed(items = notificationItems) { index, item ->
            NotificationItem(data = item)
            if (index < notificationItems.size - 1) {
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 40.dp, vertical = 4.dp),
                    thickness = 1.dp,
                    color = Color.LightGray
                )
            }
        }

    }
}
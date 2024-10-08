package com.jerry.assessment.screen.community.components.message

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jerry.assessment.composes.button.SelectionButton
import com.jerry.assessment.composes.card.CommonCardContainer
import com.jerry.assessment.composes.row.RowTextWithRightViewAll

@Composable
fun OverallMessageCard(
    onTypeSelected: (String) -> Unit = {},
    items: List<ChatroomItemData>,
) {
    CommonCardContainer(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            RowTextWithRightViewAll(
                text =  "Messages",
                onClick = {}
            )

            Spacer(modifier = Modifier.height(8.dp))
            LazyRow {
                item {
                    SelectionButton(text = "Add", onClick = {onTypeSelected.invoke("add")}, selected = true)
                    Spacer(modifier = Modifier.width(8.dp))
                }
                item {
                    SelectionButton(text = "Group", onClick = {onTypeSelected.invoke("group")}, selected = true)
                    Spacer(modifier = Modifier.width(8.dp))
                }
                item {
                    SelectionButton(text = "Direct", onClick = {onTypeSelected.invoke("direct")}, selected = true)
                    Spacer(modifier = Modifier.width(8.dp))
                }
                item {
                    SelectionButton(text = "Business", onClick = {onTypeSelected.invoke("business")}, selected = true)
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            items.forEach { item->
                ChatroomItem(data = item)

                VerticalDivider(modifier = Modifier
                    .height(1.dp)
                    .background(color = Color.LightGray))
            }

        }
    }
}

@Preview
@Composable
private fun OverallMessageCardPreview(
){
    OverallMessageCard(
        items = listOf(
            ChatroomItemData(
                avatars = listOf(
                    "https://dummyimage.com/100x100/6699cc/000",
                    "https://dummyimage.com/100x100/6699cc/000",
                    "https://dummyimage.com/100x100/6699cc/000",
                    "https://dummyimage.com/100x100/6699cc/000",
                    "https://dummyimage.com/100x100/6699cc/000",
                ),
                title = "this is title",
                subTitle = "this is subtitle",
                dateTime = "12:23",
                unseenCount = 1,
            ),
            ChatroomItemData(
                avatars = listOf(
                    "https://dummyimage.com/100x100/6699cc/000",
                    "https://dummyimage.com/100x100/6699cc/000",
                    "https://dummyimage.com/100x100/6699cc/000",
                    "https://dummyimage.com/100x100/6699cc/000",
                    "https://dummyimage.com/100x100/6699cc/000",
                ),
                title = "this is title",
                subTitle = "this is subtitle",
                dateTime = "12:23",
                unseenCount = 1,
            ),
            ChatroomItemData(
                avatars = listOf(
                    "https://dummyimage.com/100x100/6699cc/000",
                    "https://dummyimage.com/100x100/6699cc/000",
                    "https://dummyimage.com/100x100/6699cc/000",
                    "https://dummyimage.com/100x100/6699cc/000",
                    "https://dummyimage.com/100x100/6699cc/000",
                ),
                title = "this is title",
                subTitle = "this is subtitle",
                dateTime = "12:23",
                unseenCount = 1,
            )
        )
    )
}
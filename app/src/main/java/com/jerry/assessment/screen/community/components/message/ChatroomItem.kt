package com.jerry.assessment.screen.community.components.message

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.jerry.assessment.composes.ext.circleBackground

@Composable
fun ChatroomItem(
    modifier: Modifier = Modifier,
    data: ChatroomItemData,
){
    ListItem(
        modifier = modifier.fillMaxWidth(),
        colors  = ListItemDefaults.colors(
            containerColor = Color.White
        ),
        leadingContent = {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape),
                model = data.avatars[0],
                contentDescription = null,
                contentScale = ContentScale.Crop,
                loading = {
                    CircularProgressIndicator()
                },
            )
        },
        headlineContent = {
            Text(
                text = data.title,
                fontSize = 14.sp,
                //fontFamily = FontFamily(Font(Res.font.Poppins_Regular)),
                color = Color(0XFF69718A),
                lineHeight = 20.sp,
                fontWeight = FontWeight(400)
            )
        },
        supportingContent = {
            Text(
                text = data.subTitle ?: "",
                fontSize = 12.sp,
                //fontFamily = FontFamily(Font(Res.font.Poppins_Regular)),
                color = Color(0XFF8593A8),
                lineHeight = 16.sp,
                fontWeight = FontWeight(400)
            )
        },
        trailingContent = {
            Column (horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = data.dateTime,
                    fontSize = 12.sp,
                    //fontFamily = FontFamily(Font(Res.font.Poppins_Regular)),
                    color = Color(0XFF8593A8),
                    lineHeight = 16.sp,
                    fontWeight = FontWeight(400)
                )

                Text(
                    text = "1",
                    fontSize = 14.sp,
                    //fontFamily = FontFamily(Font(Res.font.Poppins_Regular)),
                    color = MaterialTheme.colorScheme.onPrimary,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight(400),
                    modifier = Modifier
                        .circleBackground(color = MaterialTheme.colorScheme.primary, padding = 4.dp)
                )
            }
        }
    )
}


data class ChatroomItemData(
    val avatars: List<String>,
    val title: String,
    val subTitle: String? = null,
    val dateTime: String,
    val unseenCount: Int,
)

@Preview
@Composable
private fun ChatroomItemPreview(){
    ChatroomItem(
        data = ChatroomItemData(
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
}
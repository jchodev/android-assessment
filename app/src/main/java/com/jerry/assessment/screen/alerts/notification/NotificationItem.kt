package com.jerry.assessment.screen.alerts.notification


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage


@Composable
fun normalStyle(): TextStyle  {
    return MaterialTheme.typography.titleMedium.copy(
        //fontFamily = FontFamily(Font(Res.font.Inter)),
        lineHeight = 16.sp,
        fontWeight = FontWeight(400),
        fontSize = 12.sp,
        color = Color.Black
    )
}

@Composable
fun keyWordStyle(): TextStyle  {
    return MaterialTheme.typography.titleMedium.copy(
        //fontFamily = FontFamily(Font(Res.font.Poppins_Regular)),
        lineHeight = 16.sp,
        fontWeight = FontWeight(400),
        fontSize = 20.sp,
        color = Color.Black,
    )
}

data class NotificationItemData(
    val avatarImageUrl: String,
    val text: AnnotatedString,
    val datetime: String,
)


@Composable
fun NotificationItem(
    modifier: Modifier = Modifier,
    data: NotificationItemData,
){
    ListItem(
        modifier = modifier.fillMaxWidth(),
        colors  = ListItemDefaults.colors(
            containerColor = Color.White
        ),
        leadingContent = {
            SubcomposeAsyncImage(
                modifier = Modifier.clip(CircleShape).size(50.dp),
                model = data.avatarImageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                loading = {
                    CircularProgressIndicator()
                },
            )
        },
        headlineContent = {
            Text(text = data.text)
        },
        trailingContent = {
            Text(text = data.datetime)
        }
    )
}

@Preview
@Composable
private fun NotificationItemPreview() {
    //AppTheme {
        NotificationItem(
            data = NotificationItemData(
                avatarImageUrl = "https://dummyimage.com/100x100/6699cc/000",
                text =  AnnotatedString("text only"),
                datetime = "11:20"
            )
        )
    //}
}